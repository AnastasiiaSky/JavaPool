package edu.school21.exceptions;

public class EntityNotFoundException extends Throwable {
    private String description = "";

    public EntityNotFoundException(String description) {
        this.description = description;
    }

    public EntityNotFoundException() {
        this.description = "";
    }

    @Override
    public String toString() {
        return "EntityNotFoundException: " + description;
    }
}
