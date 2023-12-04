package ex02.ImagesToChar.src.java.edu.school21.printer.app;

import ex02.ImagesToChar.src.java.edu.school21.printer.logic.Converter;


import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import java.io.IOException;
import javax.management.ConstructorParameters;


public class Program {



    public static void main(String args[]) {
        Args colors = new Args();
        JCommander jCommander = JCommander.newBuilder().addObject(colors).build();
        jCommander.parse(args);
        new Converter(colors.getBackground(), colors.getMain());
    }

    @Parameters(separators = "=")
    public static class Args {
        @Parameter(names={"--background", "-bgc"}, description = "Background color of the picture.")
        private String background;

        @Parameter(names={"--main", "-mc"}, description = "Main color of the picture.")
        private String main;

        public String getBackground() {
            return background;
        }

        public String getMain() {
            return main;
        }
    }
}




