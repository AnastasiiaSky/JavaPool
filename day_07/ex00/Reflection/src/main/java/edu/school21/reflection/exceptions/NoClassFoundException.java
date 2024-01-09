package edu.school21.reflection.exceptions;

import java.util.StringJoiner;

public class NoClassFoundException extends Throwable {
    private String description = "";

    public NoClassFoundException() {
        this.description = "No any classes found in this package";
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", NoClassFoundException.class.getSimpleName() + "[", "]")
                .add("'" + description + "'")
                .toString();
    }
}