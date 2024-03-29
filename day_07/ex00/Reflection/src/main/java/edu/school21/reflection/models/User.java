package edu.school21.reflection.models;

import java.util.StringJoiner;

public class User {
    private String firstName;
    private String lastName;
    private int height;

    public User() {
        this.firstName = "Ivan";
        this.lastName = "Ivanov";
        this.height = 50;
    }

    public User(String firstName, String lastName, int height) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.height = height;
    }

    public int grow(int value) {
        this.height += value;
        return height;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("height=" + height)
                .toString();
    }
}
