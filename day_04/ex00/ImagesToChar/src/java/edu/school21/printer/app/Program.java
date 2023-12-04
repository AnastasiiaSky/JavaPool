package ImagesToChar.src.java.edu.school21.printer.app;

import ImagesToChar.src.java.edu.school21.printer.logic.Converter;

public class Program {
    public static void main(String[] args) {
        if (args.length == 3 && args[0].length() == 1 && args[1].length() == 1) {
            char[] white = args[0].trim().toCharArray();
            char[] black = args[1].trim().toCharArray();
            String path = args[2].trim();
            new Converter(white[0], black[0], path);
        } else {
            System.out.println("В качестве аргументов программы должны поступать два символа," +
                    " введенные через пробел и путь к файлу");
        }
    }
}




