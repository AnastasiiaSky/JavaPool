package edu.school21.chat.app;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.Exceptions.NotSavedSubEntityException;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import edu.school21.chat.reposotories.DataSourceConfiguration;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class Program {
    private static final String NEW_MESSAGE_TEMPLATE = "This is a new message from ex02";

    public static void main(String[] args) {
        System.out.println("Добавление сообщения от пользователя существующего в БД в чат существующий в БД");
        User creator = new User(1L, "qylenett", "1111", new ArrayList<>(), new ArrayList<>());
        Chatroom room = new Chatroom(1L, "qylenett_chat", creator, new ArrayList<>());
        Message message = new Message(null, creator, room, NEW_MESSAGE_TEMPLATE, LocalDateTime.now());
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(DataSourceConfiguration.dataSource);
        try {
            messagesRepository.save(message);
            System.out.println(message.getId());
        } catch (NotSavedSubEntityException | SQLException e) {
            System.out.println("Новая запись не была добавлена!");
        }


        System.out.println("Добавление сообщения от не существующего пользователя в БД в чат существующий в БД");
        User creator1 = new User(1L, "ujhnfjws", "11555", new ArrayList<>(), new ArrayList<>());
        Message message1 = new Message(null, creator1, room, NEW_MESSAGE_TEMPLATE, LocalDateTime.now());
        try {
            messagesRepository.save(message1);
        } catch (NotSavedSubEntityException | SQLException e) {
            System.out.println("Новая запись не была добавлена! Нет такого пользователя!\n");
        }

        System.out.println("Добавление сообщения от существующего пользователя в БД в чат несуществующий в БД");
        Chatroom room1 = new Chatroom(1L, "gubvjapokpoak", creator, new ArrayList<>());
        Message message2 = new Message(null, creator, room1, NEW_MESSAGE_TEMPLATE, LocalDateTime.now());
        try {
            messagesRepository.save(message2);
        } catch (NotSavedSubEntityException | SQLException e) {
            System.out.println("Новая запись не была добавлена! Нет такого чата!\n");
        }


    }
}


