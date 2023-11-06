package ex05.exceptions;

public class UnknownArgumentException extends RuntimeException {
    @Override
    public String toString() {
        return ("UnknownMenuArgumentException");
    }
}