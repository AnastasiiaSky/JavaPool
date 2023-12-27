package edu.school21.chat.app;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.DataSourceConfiguration;
import edu.school21.chat.repositories.Exceptions.NotUpdatedException;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.time.LocalDateTime;
import java.util.Optional;


public class Program {
    public static void main(String[] args) {
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(DataSourceConfiguration.dataSource);
        Optional<Message> messageOptional = messagesRepository.findById(9L);
        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            message.setText("Updated message from task 3");
            message.setTime(null);
            try {
                messagesRepository.update(message);
                System.out.println("Сообщение 1 обновлено!\n");
            } catch (NotUpdatedException notUpdatedException) {
                System.out.println("Сообщение 1 не было обновлено!\n");
            }
        }
        Optional<Message> messageOptional1 = messagesRepository.findById(5L);
        if (messageOptional1.isPresent()) {
            Message message1 = messageOptional1.get();
            message1.setText("Second update from task 3");
            message1.setTime(LocalDateTime.now());
            try {
                messagesRepository.update(message1);
                System.out.println("Сообщение 2 обновлено!\n");
            } catch (NotUpdatedException notUpdatedException) {
                System.out.println("Сообщение 2 не было обновлено!\n");
            }
        }
    }
}

