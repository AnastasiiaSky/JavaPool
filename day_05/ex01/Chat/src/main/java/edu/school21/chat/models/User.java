package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public final class User {
    private final int id;
    private final String login;
    private final String password;
    private List<Chatroom> allChatRooms;
    private List<Chatroom> userChatRooms;

    public User(int id, String login, String password, List<Chatroom> allChatrooms, List<Chatroom> userChatrooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.allChatRooms = allChatrooms;
        this.userChatRooms = userChatrooms;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        User user = (User) o;
        if (this.hashCode() != o.hashCode()) return false;
        return id == user.id && Objects.equals(login, user.login)
                && Objects.equals(password, user.password)
                && Objects.equals(allChatRooms, user.allChatRooms)
                && Objects.equals(userChatRooms, user.userChatRooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(5 * id + 3 * login.length() +
                7 * password.length() + 99 * allChatRooms.size() +
                25 * userChatRooms.size());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\n' +
                ", allChatRooms:" + '\n' + getChatRoomsString(allChatRooms) +
                ", userChatRooms:" + '\n' + getChatRoomsString(userChatRooms) +
                '}';
    }

    private String getChatRoomsString(List<Chatroom> chatRooms) {
        String result = "";
        for (Chatroom chatroom : chatRooms) {
            result += chatroom + "\n";
        }
        return result;
    }
}
