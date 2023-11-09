package ex02.exceptions;

public class InputException extends RuntimeException {
    @Override
    public String toString() {
        return ("InputException:\nFor use the program use: " +
                "Program.java {--current-folder=[FOLDER_PATH]}.");
    }

}