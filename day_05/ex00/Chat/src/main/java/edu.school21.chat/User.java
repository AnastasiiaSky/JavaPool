package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public final class User {
    private final Long id;
    private final String login;
    private final String password;
    private List<Chatroom> usersChatrooms;
    private List<Chatroom> usersChatingChatrooms;

    public User(Long id, String login, String password, List<Chatroom> usersChatrooms, List<Chatroom> userChatrooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.usersChatrooms = usersChatrooms;
        this.usersChatingChatrooms = usersChatingChatrooms;
    }

    public Long getId() {
        return id;
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
                && Objects.equals(usersChatrooms, user.usersChatrooms)
                && Objects.equals(usersChatingChatrooms, user.usersChatingChatrooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(5 * id + 3 * login.length() +
                7 * password.length() + 99 * usersChatrooms.size() +
                25 * usersChatingChatrooms.size());
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", login=\"" + login + '\"' +
                ", password=\"" + password + '\"' +
                ", usersChatrooms = null" +
                ", usersChatingChatrooms = null" +
                '}';
    }
}
