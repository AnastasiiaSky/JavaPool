package Game.src.main.java;


import com.beust.jcommander.JCommander;

import java.util.Scanner;

import static Game.src.main.java.ObjectsData.*;
import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;

public class Program {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
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
            System.out.println(colorize("Invalid argument. Please enter arguments according to the template.", BOLD(), BLACK_TEXT(), MAGENTA_BACK()));
            System.out.println(colorize(" --enemiesCount=10 --wallsCount=10 --size=30 --profile=production", BOLD(), BLACK_TEXT(), MAGENTA_BACK()));
            System.exit(1);
        }
        if ((ObjectsData.SIZE * ObjectsData.SIZE - ObjectsData.WALLS_COUNT - ObjectsData.ENEMIES_COUNT < 2) ||
                ObjectsData.WALLS_COUNT < 0 || ObjectsData.ENEMIES_COUNT < 0) {
            negativeArguments();
            System.exit(1);
        }
        if (!argumentsValidation()) {
            System.exit(1);
        }
        executeGame();
    }

    public static void executeGame() {
        GameBoard gameBoard = new GameBoard();
        new Wall(gameBoard.getMatrix());
        new Player(gameBoard.getMatrix());
        new Target(gameBoard.getMatrix());
        Monsters monsters = new Monsters(gameBoard.getMatrix());
        do {
            gameBoard = new GameBoard();
            new Wall(gameBoard.getMatrix());
            monsters = new Monsters(gameBoard.getMatrix());
            new Player(gameBoard.getMatrix());
            new Target(gameBoard.getMatrix());
        } while (!isGoalCanBeReached(gameBoard));

        boolean isPlayerGo = true;
        MonstersTask monstersTask = new MonstersTask(gameBoard, monsters.getMonstersList());
        gameBoard.printMatrix();
        while (true) {
            if (isPlayerGo) {
                getPlayerAction(gameBoard);
                if (ObjectsData.IS_PRODUCTION) {
                    System.out.println();
                    gameBoard.printMatrix();
                }
                isPlayerGo = false;
            } else {
                if (monstersTask.executeMonsterTask(gameBoard, monsters.getMonstersList())) {
                    isPlayerGo = true;
                } else {
                    break;
                }
            }
        }
    }


    public static boolean isGoalCanBeReached(GameBoard gameBoard) {
        PlayerWayTask check = new PlayerWayTask(gameBoard);
        return check.isAnswer();
    }

    public static void getPlayerAction(GameBoard gameBoard) {
        String input = null;
        if (scanner.hasNextInt()) {
            input = scanner.next();
            if (Integer.parseInt(input) == 9) {
                ObjectsData.printGameOver();
                System.exit(1);
            } else {
                printIllegalArgument();
            }
        }

        Player movePlayer = null;

        input = scanner.next();
        switch (input) {
            case "a":
                movePlayer = new Player(gameBoard.getMatrix(), "a");
                break;
            case "w":
                movePlayer = new Player(gameBoard.getMatrix(), "w");
                break;
            case "d":
                movePlayer = new Player(gameBoard.getMatrix(), "d");
                break;
            case "s":
                movePlayer = new Player(gameBoard.getMatrix(), "s");
                break;
            default:
                printIllegalArgument();
                getPlayerAction(gameBoard);
        }

        if (movePlayer != null) {
            if (movePlayer.movePlayer()) {
            } else {
                getPlayerAction(gameBoard);
            }
        }
    }
}
