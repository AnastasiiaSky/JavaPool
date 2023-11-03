package ex03.exceptions;

public class TransactionNotFoundException extends RuntimeException {

    @Override
    public String toString() {
        return ("TransactionNotFoundException");
    }
}