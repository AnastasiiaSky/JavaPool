package Game;

import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;

//char layer_occupied_the_square

public class GameBoard {
    private int width;
    private int length;
    private int monstr;


    private ArrayList<ArrayList <Character>> matrix = null;

    public GameBoard(int width, int length, int monstr) {
        this.width = width;
        this.length = length;
        this.monstr = monstr;
        createMatrix(width, length);
        createMonster(matrix, monstr);
        createPlayer(matrix);
        printMatrix();
    }

    private void createPlayer(ArrayList<ArrayList<Character>> matrix){
        int count = 0;
        while (count < 1){
            int x = (int) (Math.random() * matrix.size());
            int y = (int) (Math.random() * matrix.get(0).size());
            if(matrix.get(x).get(y).equals(' ') && !matrix.get(x).get(y).equals('#')){
                matrix.get(x).set(y, 'X');
                count ++;
            }
        }
    }

    private void createMonster(ArrayList<ArrayList<Character>> matrix, int monstr){
        int count = 0;
        while (count < monstr){
            int x = (int) (Math.random() * matrix.size());
            int y = (int) (Math.random() * matrix.get(0).size());
            if(matrix.get(x).get(y).equals(' ')){
                matrix.get(x).set(y, '#');
                count ++;
            }
        }
    }

    private void createMatrix(int width, int length){
        matrix = new ArrayList<>();
        for (int i = 0; i < width; ++i) {
            ArrayList<Character> row = new ArrayList<>();
            for (int j = 0; j < length; ++j) {
                row.add(' ');
            }
            matrix.add(row);
        }
    }
    public void printMatrix(){
        for (int i = 0; i < width; ++i){
            for (int j = 0; j < length; ++j){
                if(matrix.get(i).get(j).equals(' ')){
                    System.out.print(colorize("   ", RED_BACK()));
                } else if(matrix.get(i).get(j).equals('#')) {
                    System.out.print(colorize("   ", GREEN_BACK()));
                } else if(matrix.get(i).get(j).equals('X')){
                    System.out.print(colorize("   ", BLUE_BACK()));
                }
            }
            System.out.println();
        }
    }
}
