package edu.school21.chat.app;

import edu.school21.chat.models.Message;
import edu.school21.chat.reposotories.MessagesRepositoryJdbcImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class Program {
    public static void main(String[] args) {
        System.out.println("Enter a message ID");
        Long messageId;
        try (InputStreamReader reader = new InputStreamReader(System.in);
                BufferedReader scanner = new BufferedReader(reader)) {
            messageId = Long.parseLong(scanner.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MessagesRepositoryJdbcImpl task = new MessagesRepositoryJdbcImpl();
        Optional<Message> message = task.findById(messageId);
        message.ifPresent(value -> {
            System.out.println(value.toString());
        });
    }
}
