package ex04.exceptions;

public class UserNotFoundException extends RuntimeException {

    @Override
    public String toString() {
        return ("UserNotFoundException");
    }
}