package edu.school21.chat.repositories.Exceptions;

public class NotUpdatedException extends RuntimeException {
    @Override
    public String toString() {
        return "NotUpdatedException{Message wasn't updated}";
    }
}
