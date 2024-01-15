package edu.school21.ORM.exceptions;

import java.util.StringJoiner;

public class WrongTypeException extends RuntimeException{
    String description = "";

    public WrongTypeException(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", WrongTypeException.class.getSimpleName() + "[", "]")
                .add("description='" + description + "'")
                .toString();
    }
}
