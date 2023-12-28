package edu.school21.numbers;

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
