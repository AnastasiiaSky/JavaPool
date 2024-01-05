package edu.school21.exceptions;

public class AlreadyAuthenticatedException extends Throwable {
    private String description = "";

    public AlreadyAuthenticatedException(String description) {
        this.description = description;
    }
    public AlreadyAuthenticatedException() {
        this.description = "";
    }

    @Override
    public String toString() {
        return "AlreadyAuthenticatedException: " + description;
    }
}
