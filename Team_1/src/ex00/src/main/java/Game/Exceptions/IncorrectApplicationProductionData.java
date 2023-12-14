package Game.Exceptions;

public class IncorrectApplicationProductionData extends RuntimeException {
    private static final String EXCEPTION_DESCRIPTION = "в файле application-production.properties некорректные данные!";
    @Override
    public String toString() {
        return ("IncorrectApplicationProductionData: " + EXCEPTION_DESCRIPTION);
    }
}
