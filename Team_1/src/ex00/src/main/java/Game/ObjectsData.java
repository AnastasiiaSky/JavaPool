package Game;

/* Класс с данными о символьном представлении всех действующих лиц игры */

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;

import Game.Exceptions.IncorrectApplicationProductionData;

import java.io.*;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;

public final class ObjectsData {
    public static final Character MONSTER_SIMBL = Parser.getDataFromFile().get(0).charAt(0);
    public static final Character PLAYER_SIMBL = Parser.getDataFromFile().get(1).charAt(0);

    public static final Character OBSTACLE_SIMBL = Parser.getDataFromFile().get(2).charAt(0);
    public static final Character TARGET_SIMBL = Parser.getDataFromFile().get(3).charAt(0);
    public static final Character EMPTY_SIMBL = Parser.getDataFromFile().get(4).charAt(0);


    public static final String ENEMY_COLOR = Parser.getDataFromFile().get(5);
    public static final String PLAYER_COLOR = Parser.getDataFromFile().get(6);
    public static final String WALL_COLOR = Parser.getDataFromFile().get(7);
    public static final String GOAL_COLOR = Parser.getDataFromFile().get(8);
    public static final String EMPTY_COLOR = Parser.getDataFromFile().get(9);


    public static final Integer SIZE = Args.getArgumentSize();
    public static final Integer ENEMIES_COUNT = Args.getArgumentEnemiesCount();
    public static final Integer WALLS_COUNT = Args.getArgumentWallsCount();

    public static void printPlayerSimbl() {

        printElement(" " + PLAYER_SIMBL + " ", PLAYER_COLOR);

    }

    public static void printMonsterSimbl() {
        printElement(" " + MONSTER_SIMBL + " ", ENEMY_COLOR);
    }

    public static void printEmptySimbl() {
        printElement(" " + EMPTY_SIMBL + " ", EMPTY_COLOR);

    }

    public static void printObstacleSimbl() {
        printElement(" " + OBSTACLE_SIMBL + " ", WALL_COLOR);


    }

    public static void printTargetSimbl() {
        printElement(" " + TARGET_SIMBL + " ", GOAL_COLOR);

    }

    public static void printElement(String element, String color) {
        switch (color) {
            case "RED":
                System.out.print(colorize(element, BOLD(), BLACK_TEXT(), RED_BACK()));
                break;
            case "GREEN":
                System.out.print(colorize(element, BOLD(), BLACK_TEXT(), GREEN_BACK()));
                break;
            case "MAGENTA":
                System.out.print(colorize(element, BOLD(), BLACK_TEXT(), MAGENTA_BACK()));
                break;
            case "BLUE":
                System.out.print(colorize(element, BOLD(), BLACK_TEXT(), BLUE_BACK()));
                break;
            case "YELLOW":
                System.out.print(colorize(element, BOLD(), BLACK_TEXT(), YELLOW_BACK()));
                break;
        }
    }
}



