package edu.school21.chat.reposotories;

import edu.school21.chat.models.Message;

import java.sql.*;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private static final String DB_URL_TEMPLATE = "jdbc:postgresql://localhost:5432/Java_day5";
    private static final String USER_TEMPLATE = "qylenett";
    private static final String PASSWORD_TEMPLATE = "1";
    private static final String QUERY_TEMPLATE = "SELECT %l FROM Message";

    private Connection connection;

    public MessagesRepositoryJdbcImpl() {
        connection = null;
    }

    @Override
    public Optional<Message> findById(Long id) {
        executeConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(QUERY_TEMPLATE, id));
            while(resultSet.next()) {
                String author = resultSet.getString(2);
                String chat = resultSet.getString(3);
                String text = resultSet.getString(4);
                Timestamp time = resultSet.getTimestamp(5);
                Message queryResult = new Message(id, author, chat, text, time);
                Optional<Message> message = Optional.ofNullable(queryResult);
                return message;
            }
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    private void executeConnection() {
        try {
            this.connection = DriverManager.getConnection(DB_URL_TEMPLATE, USER_TEMPLATE, PASSWORD_TEMPLATE);
        } catch (SQLException sqlException) {
            System.out.println("Connection failed");
            sqlException.printStackTrace();
        }

    }
}
