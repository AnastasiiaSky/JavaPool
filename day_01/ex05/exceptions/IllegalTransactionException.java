package ex05.exceptions;

public class IllegalTransactionException extends RuntimeException {

    @Override
    public String toString() {
        return ("IllegalTransactionException");
    }
}