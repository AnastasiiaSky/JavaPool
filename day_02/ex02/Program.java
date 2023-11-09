package ex02;

import ex02.exceptions.*;

public class Program {
    public static void main(String[] args) {
        if (args.length != 1) throw new InputException();
        String path = parceInput(args[0]);
        if (path != null) {
            new FileManager(path).run();
        } else throw new InputException();
        System.out.println("path " + path);

    }

    public static String parceInput(String arg) {
        try {
            int divPosition = arg.indexOf("=");
            String argument = arg.substring(0, divPosition);
            String path = arg.substring(divPosition + 1);
            if (!argument.equals("--current-folder")) throw new InputException();
            return path;
        } catch (StringIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return null;
    }
}