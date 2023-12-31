package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import edu.school21.chat.models.User;
import edu.school21.chat.repositories.Exceptions.NotSavedSubEntityException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;


public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private final HikariDataSource dataSource;


    private static final String GET_MESSAGE_QUERY_TEMPLATE = "SELECT \"Message\".message_author,\n" +
            "\"User\".user_login,\n" +
            "\"User\".user_password,\n" +
            "\"Message\".message_room,\n" +
            "chatroom.chatroom_name,\n" +
            "\"Message\".message_text,\n" +
            "\"Message\".message_date_and_time\n" +
            "FROM \"Message\"\n" +
            "JOIN \"User\" ON \"Message\".message_author = \"User\".id\n" +
            "JOIN chatroom ON \"Message\".message_room = chatroom.id\n" +
            "WHERE \"Message\".id = %d;";


    private static final String GET_USER_WITH_ID_TEMPLATE = "SELECT 2, 3\n FROM \"User\"\nWHERE \"User\".id = %d;";

    private static final String GET_CHATROOM_WITH_ID_TEMPLATE = "SELECT 2, 3\nFROM chatroom\nWHERE id = %d;";

    private static final String ADD_MESSAGE_QUERY_TEMPLATE = "INSERT INTO \"Message\" (message_author, message_room, message_text) VALUES (\n" +
            "%d, %d,\'%s\');";
    private static final String GET_MAX_MESSAGE_ID = "SELECT MAX(id) FROM \"Message\";";

    public MessagesRepositoryJdbcImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) {
        Optional<Message> message = Optional.empty();
        try (Connection connection = dataSource.getConnection();) {
            Statement statement = connection.createStatement();
            ResultSet messageResultSet = statement.executeQuery(String.format(GET_MESSAGE_QUERY_TEMPLATE, id));
            while (messageResultSet.next()) {
                User author = new User(messageResultSet.getLong(1),
                        messageResultSet.getString(2),
                        messageResultSet.getString(3),
                        null, null);

                Chatroom chatroom = new Chatroom(messageResultSet.getLong(4),
                        messageResultSet.getString(5),
                        null, null);

                Message queryResult = new Message(id, author, chatroom,
                        messageResultSet.getString(6),
                        (messageResultSet.getTimestamp(7)).toLocalDateTime());

                message = Optional.ofNullable(queryResult);

            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return message;
    }

    @Override
    public void save(Message message) throws NotSavedSubEntityException {
        User author = message.getAuthor();
        Chatroom chatroom = message.getChat();
        String text = message.getText();
        try {
            if (!checkUser(author) || !checkChat(chatroom)) throw new NotSavedSubEntityException();
            try (Connection connection = dataSource.getConnection();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(String.format(ADD_MESSAGE_QUERY_TEMPLATE, author.getId(), chatroom.getId(), text));
            } catch (SQLException sqlException) {
                throw new NotSavedSubEntityException();
            }
            updateMessageId(message);
        } catch (SQLException sqlException) {
            throw new NotSavedSubEntityException();
        }
    }

    private void updateMessageId(Message message) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet maxMessage = statement.executeQuery(GET_MAX_MESSAGE_ID);
            while (maxMessage.next()) {
                message.setId(maxMessage.getLong(1));
            }
        }
    }

    private boolean checkUser(User author) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet checkUserResultSet = statement.executeQuery(String.format(GET_USER_WITH_ID_TEMPLATE, author.getId()));
            while (checkUserResultSet.next()) {
                User tableUser = new User(author.getId(), checkUserResultSet.getString(1),
                        checkUserResultSet.getString(2), new ArrayList<>(),
                        new ArrayList<>());
                if (author.equals(tableUser)) return true;
            }
        }
        return false;
    }

    private boolean checkChat(Chatroom chatroom) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet checkChatroomResultSet = statement.executeQuery(String.format(GET_CHATROOM_WITH_ID_TEMPLATE, chatroom.getId()));
            while (checkChatroomResultSet.next()) {
                String chatroomName = checkChatroomResultSet.getString(1);
                if (!checkUser(chatroom.getOwner())) {
                    System.out.println("Incorrect user");
                    throw new NotSavedSubEntityException();
                }
                Chatroom tableChatroom = new Chatroom(chatroom.getId(), chatroomName, chatroom.getOwner(), new ArrayList<>());
                if (tableChatroom.equals(chatroom)) return true;
            }
            return false;
        }
    }
}
