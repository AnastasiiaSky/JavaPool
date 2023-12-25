package edu.school21.chat.repositories.Exceptions;

public class NotSavedSubEntityException extends RuntimeException {
    @Override
    public String toString() {
        return ("NotSavedSubEntityException");
    }
}
