package Game.Exceptions;

public class IllegalPointPositionException extends RuntimeException {
    private static final String EXCEPTION_DESCRIPTION = "выход за границы игрового поля!";
    @Override
    public String toString() {
        return ("IllegalPointPositionException: " + EXCEPTION_DESCRIPTION);
    }
}
