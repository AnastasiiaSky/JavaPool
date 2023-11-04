package ex05.exceptions;

public class UserNotFoundException extends RuntimeException {

    @Override
    public String toString() {
        return ("UserNotFoundException");
    }
}