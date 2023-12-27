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
import java.time.LocalDateTime;
import java.util.Optional;


public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private HikariDataSource dataSource;
    private static final String MESSAGE_QUERY_TEMPLATE = "SELECT \"Message\".message_author,\n" +
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

    public MessagesRepositoryJdbcImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public Optional<Message> findById(Long id) {
        Optional<Message> message = Optional.empty();
        try (Connection connection = dataSource.getConnection();) {
            Statement statement = connection.createStatement();
            ResultSet messageResultSet = statement.executeQuery(String.format(MESSAGE_QUERY_TEMPLATE, id));
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
}
