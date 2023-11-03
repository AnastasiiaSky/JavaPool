package ex03.exceptions;

public class UserNotFoundException extends RuntimeException {

    @Override
    public String toString() {
        return ("User not found in ArrayList!");
    }
}