package ex02.ImagesToChar.src.java.edu.school21.printer.app;

import ex02.ImagesToChar.src.java.edu.school21.printer.logic.Converter;
import com.beust.jcommander.Parameter;




public class Program {
    @Parameter(names={"--background", "-bgc"}, description = "Background color of the picture.")
    static String colorOne;

    @Parameter(names={"--main", "-mc"}, description = "Main color of the picture.")
    static String colorTwo;

    public static void main(String ... argv) {
        Program program = new Program();
        JCommander.newBuilder().addObject(program).build().parse(argv);
        new Converter(colorOne, colorTwo);
    }
}




