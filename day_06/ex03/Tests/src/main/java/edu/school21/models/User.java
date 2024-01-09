package edu.school21.models;

import java.util.Objects;
import java.util.StringJoiner;

public class User {
    private Long id;
    private String login;
    private String password;
    private Boolean isAuthenticated;

    public User(Long id, String login, String password, Boolean isAuthenticated) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.isAuthenticated = isAuthenticated;
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

    public Boolean getAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        isAuthenticated = authenticated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        if (this.hashCode() != user.hashCode()) return false;
        return Objects.equals(id, user.id) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(isAuthenticated, user.isAuthenticated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(33 * id + 44 * (long) login.length() +
                25 * (long) password.length() + 22 * (long) isAuthenticated.hashCode());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "{", "}")
                .add("id=" + id)
                .add("login='" + login + "'")
                .add("password='" + password + "'")
                .add("isAuthenticated=" + isAuthenticated)
                .toString();
    }
}
