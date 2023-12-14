package Game;

import com.beust.jcommander.JCommander;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class Program {
    public static int SIZE = 0;
    public static void main(String[] args) {
//        Parser.getDataFromFile();
    if (args.length != 4) {
        System.err.print("Please enter 4 parameters");
        System.exit(1);
    }
    Args arguments = new Args();

    try {
        JCommander.newBuilder()
                .addObject(arguments)
                .build()
                .parse(args);
    } catch (Exception e) {
        System.err.println("Invalid argument. Please enter arguments according to the template");
        System.exit(1);
    }
        SIZE = arguments.getArgumentSize();
        GameBoard gameBoard = new GameBoard(SIZE, arguments.getArgumentWallsCount());
        gameBoard.printMatrix();
        Scanner scanner = new Scanner(System.in);
        while(true){
                String input = null;
            if(scanner.hasNextInt()){
                input = scanner.next();
                if(Integer.parseInt(input) == 9) {
                    System.out.println("Game Over");
                    System.exit(1);
                }
            }

            input = scanner.next();
            switch (input){
                case "a" :
                    System.out.println("w");
                    gameBoard.printMatrix();
                    break;
                case "w" :
                    System.out.println("w");
                    gameBoard.printMatrix();
                    break;
                case "d" :
                    System.out.println("w");
                    gameBoard.printMatrix();
                    break;
                case "s" :
                    System.out.println("w");
                    gameBoard.printMatrix();
                    break;
                default:
                    System.out.println("Illegal argument");
                    continue;
            }
//            if( !input.equals("a") || !input.equals("w") || !input.equals("d") || !input.equals("s")){
//                System.err.println("Invalid input");
//                continue;
//            }

        }
    }
}
