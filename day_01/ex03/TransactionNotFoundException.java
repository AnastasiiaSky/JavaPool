package exception;

public class TransactionNotFoundException extends RuntimeException {

    @Override
    public String toString() {
        return ("Transaction not found exception!");
    }
}