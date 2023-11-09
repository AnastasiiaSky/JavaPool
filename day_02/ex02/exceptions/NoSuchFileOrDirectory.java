package ex02.exceptions;


public class NoSuchFileOrDirectory extends RuntimeException {
    @Override
    public String toString() {
        return ("NoSuchFileOrDirectory:\n" +
                "File or directory doesn't exists");
    }

}