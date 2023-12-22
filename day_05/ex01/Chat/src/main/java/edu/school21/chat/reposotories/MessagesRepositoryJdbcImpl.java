package edu.school21.chat.reposotories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import edu.school21.chat.models.User;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;


public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private static HikariDataSource dataSource;
    private static HikariConfig config = new HikariConfig();
    private static final String MESSAGE_QUERY_TEMPLATE = "SELECT * FROM \"Message\" WHERE id =";
    private static final String USER_QUERY_TEMPLATE = "SELECT * FROM \"User\" WHERE id =";
    private static final String CHATROOM_QUERY_TEMPLATE = "SELECT * FROM \"chatroom\" WHERE id =";


    static {
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        config.setUsername("postgres");
        config.setPassword("");
        config.setConnectionTimeout(5000);
        dataSource = new HikariDataSource(config);
    }


    @Override
    public Optional<Message> findById(Long id) {
        Optional<Message> message = Optional.empty();
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet messageResultSet = statement.executeQuery(MESSAGE_QUERY_TEMPLATE + id);
            while (messageResultSet.next()) {
                Long authorId = messageResultSet.getLong(2);
                Long chatId = messageResultSet.getLong(3);
                String text = messageResultSet.getString(4);
                Timestamp time = messageResultSet.getTimestamp(5);
                Optional authorOptional = findUserById(authorId, connection);
                Optional chatOptional = findChatRoomById(chatId, connection);
                if (authorOptional.isPresent() && chatOptional.isPresent()) {
                    User author = (User) authorOptional.get();
                    Chatroom chatroom = (Chatroom) chatOptional.get();
                    Message queryResult = new Message(id, author, chatroom, text, time);
                    message = Optional.ofNullable(queryResult);
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return message;
    }

    private Optional<User> findUserById(Long id, Connection connection) throws SQLException {
        Optional<User> user = Optional.empty();
        Statement statement = connection.createStatement();
        ResultSet authorResultSet = statement.executeQuery(USER_QUERY_TEMPLATE + id);
        while (authorResultSet.next()) {
            String login = authorResultSet.getString(2);
            String password = authorResultSet.getString(3);
            User queryResult = new User(id, login, password, new ArrayList<Chatroom>(), new ArrayList<Chatroom>());
            user = Optional.ofNullable(queryResult);
        }
        return user;
    }

    private Optional<Chatroom> findChatRoomById(Long id, Connection connection) throws SQLException {
        Optional<Chatroom> chatroom = Optional.empty();
        Statement statement = connection.createStatement();
        ResultSet authorResultSet = statement.executeQuery(CHATROOM_QUERY_TEMPLATE + id);
        while (authorResultSet.next()) {
            String chatRoomName = authorResultSet.getString(2);
            Long userId = authorResultSet.getLong(3);
            Optional userOptional = findUserById(userId, connection);
            if (userOptional.isPresent()) {
                User user = (User) userOptional.get();
                Chatroom queryResult = new Chatroom(id, chatRoomName, user, new ArrayList<Message>());
                chatroom = Optional.ofNullable(queryResult);
            }
        }
        return chatroom;
    }
}
