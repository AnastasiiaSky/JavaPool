package edu.school21.reflection.exceptions;

import java.util.StringJoiner;

public class NoSuchClassException extends Throwable {
    private String description = "";

    public NoSuchClassException(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", NoClassFoundException.class.getSimpleName() + "[", "]")
                .add("'" + description + "'")
                .toString();
    }
}
