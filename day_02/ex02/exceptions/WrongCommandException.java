package ex02.exceptions;


public class WrongCommandException extends RuntimeException {
    @Override
    public String toString() {
        return ("WrongCommandException:\n" +
                "Chose cd, ls, mv, or exit");
    }
}