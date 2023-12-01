package ex02.ImagesToChar.src.java.edu.school21.printer.logic;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import com.diogonunes.jcolor.AnsiFormat;
import com.diogonunes.jcolor.Attribute;
import org.junit.jupiter.api.Disabled;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;
import static com.diogonunes.jcolor.tests.unit.DataGenerator.randomInt;

public class Converter {
    private final String background;
    private final String picture;
    private final String picturePath;

    public Converter(String background, String picture) {
        this.background = background;
        this.picture = picture;
        File file = new File(".");
        String absolut = file.getAbsolutePath();
        this.picturePath = absolut.substring(0, absolut.length() - 1) + "/ImagesToChar/resources/it.bmp";
        convert();
    }

    private void convert() {
        try {
            BufferedImage image =  ImageIO.read(new File(picturePath));
            printPicture(image);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void printPicture(BufferedImage image) {
        if(image != null && image.getHeight() > 0 && image.getWidth() > 0) {
            for(int i = 0; i < image.getWidth(); ++i) {
                for (int j = 0; j < image.getHeight(); ++j) {
                    Color color = new Color(image.getRGB(j, i));
//                    System.out.println(color);
                    if (color.equals(Color.BLACK)) {
                        getColor(picture);
//                        System.out.printf("%c", black);
                    } else if (color.equals(Color.WHITE)) {
                        getColor(background);
//                        System.out.printf("%c", white);
                    }
                }
                System.out.println();
            }
        }
    }

    private void getColor(String color) {
        if (color.equalsIgnoreCase("YELLOW")) {
            System.out.print(colorize(" "), YELLOW_TEXT(), YELLOW_BACK());
        } else if (color.equalsIgnoreCase("RED")) {
            System.out.print(colorize(" "), RED_TEXT(), RED_BACK());
        } else if (color.equalsIgnoreCase("PINK")) {
            System.out.print(colorize(" "), MAGENTA_TEXT(), MAGENTA_BACK());
        } else if (color.equalsIgnoreCase("BLUE")) {
            System.out.print(colorize(" "), BLUE_TEXT(), BLUE_BACK());
        } else if (color.equalsIgnoreCase("GREEN")) {
            System.out.print(colorize(" "), GREEN_TEXT(), GREEN_BACK());
        } else if (color.equalsIgnoreCase("MAGENTA")) {
            System.out.print(colorize(" "), MAGENTA_TEXT(), MAGENTA_BACK());
        } else if (color.equalsIgnoreCase("CYAN")) {
            System.out.print(colorize(" "), CYAN_TEXT(), CYAN_BACK());
        } else if (color.equalsIgnoreCase("WHITE")) {
            System.out.print(colorize(" "), WHITE_TEXT(), WHITE_BACK());
        } else if (color.equalsIgnoreCase("BLACK")) {
            System.out.print(colorize(" "), BLACK_TEXT(), BLACK_BACK());
        }
    }
}

