package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public final class User {
    private final Long id;
    private String login;
    private String password;
    private List<Chatroom> userChatRooms;
    private List<Chatroom> userChatingChatRooms;

    public User(Long id, String login, String password, List<Chatroom> userChatRooms, List<Chatroom> userChatingChatRooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.userChatRooms = userChatRooms;
        this.userChatingChatRooms = userChatingChatRooms;
    }

    public Long getId() {
        return id;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public void setPassword(final String password) {
        this.password = password;
    }


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        User user = (User) o;
        if (this.hashCode() != o.hashCode()) return false;
        return id == user.id && Objects.equals(login, user.login)
                && Objects.equals(password, user.password)
                && Objects.equals(userChatRooms, user.userChatRooms)
                && Objects.equals(userChatingChatRooms, user.userChatingChatRooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(5 * id + 3 * login.length() +
                7 * password.length() + 99 * userChatRooms.size() +
                25 * userChatingChatRooms.size());
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", login=\"" + login + '\"' +
                ", password=\"" + password + '\"' +
                ", userChatRooms = null" +
                ", userChatingChatRooms = null" +
                '}';
    }
}
