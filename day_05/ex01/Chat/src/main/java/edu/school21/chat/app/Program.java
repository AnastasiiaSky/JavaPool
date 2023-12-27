package edu.school21.chat.app;

import edu.school21.chat.models.Message;
import edu.school21.chat.reposotories.DataSourceConfiguration;
import edu.school21.chat.reposotories.MessagesRepositoryJdbcImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class Program {
    private static final String NULL_MESSAGE_TEMPLATE = "This DataBase does not have a message with ID = ";
    private static final String START_MESSAGE_TEMPLATE = "Enter a message ID you want to find\n";
    private static final String WRONG_ACTION_TEMPLATE = "It must be number try again!\n";


    public static void main(String[] args) {
        System.out.println(START_MESSAGE_TEMPLATE);
        Long messageId;
        try (InputStreamReader reader = new InputStreamReader(System.in);
             BufferedReader scanner = new BufferedReader(reader)) {
            while (true) {
                try {
                    messageId = Long.parseLong(scanner.readLine());
                    MessagesRepositoryJdbcImpl task = new MessagesRepositoryJdbcImpl(DataSourceConfiguration.dataSource);
                    Optional<Message> message = task.findById(messageId);
                    if (message.isPresent()) {
                        message.ifPresent(value -> {
                            System.out.println(value.toString());
                        });
                        break;
                    }
                    System.out.println(NULL_MESSAGE_TEMPLATE + messageId);
                } catch (NumberFormatException numberFormatException) {
                    System.out.println(numberFormatException + "\n" + WRONG_ACTION_TEMPLATE);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
