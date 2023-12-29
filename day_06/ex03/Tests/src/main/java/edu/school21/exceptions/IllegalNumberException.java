package edu.school21.exceptions;

public class IllegalNumberException extends Throwable {
    private final String description;

    public IllegalNumberException(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "IllegalNumberException: " + description;
    }
}
