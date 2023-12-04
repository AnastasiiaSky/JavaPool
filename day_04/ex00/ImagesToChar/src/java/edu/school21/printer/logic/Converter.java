package ImagesToChar.src.java.edu.school21.printer.logic;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Converter {
    private final char black;
    private final char white;
    private final String picture;

    public Converter(char black, char white, String path) {
        this.black = black;
        this.white = white;
        this.picture = path;
        convert();
    }

    private void convert() {
        try {
            BufferedImage image = ImageIO.read(new File(picture));
            printPicture(image);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void printPicture(BufferedImage image) {
        if (image != null && image.getHeight() > 0 && image.getWidth() > 0) {
            for (int i = 0; i < image.getWidth(); ++i) {
                for (int j = 0; j < image.getHeight(); ++j) {
                    Color color = new Color(image.getRGB(j, i));
                    if (color.equals(Color.BLACK)) {
                        System.out.printf("%c", black);
                    } else if (color.equals(Color.WHITE)) {
                        System.out.printf("%c", white);
                    }
                }
                System.out.println();
            }
        }
    }
}
