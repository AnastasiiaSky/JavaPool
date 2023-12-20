package Game.src.main.java;


import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;
import static com.diogonunes.jcolor.Attribute.RED_BACK;

public final class ObjectsData {
    public static final String EMPTY_LINE_TEMPLATE = "                                                  ";
    public static final String ERROR_ONE_LINE_TEMPLATE = "     Oop! Add enemies \uD83D\uDE08 before start the game!   \n " +
            "        Try again with other arguments!          ";
    public static final String ERROR_TWO_LINE_TEMPLATE = "    Oops! The game-board size must be bigger:     \n" +
            "       (We should place: goal, player,            \n" +
            "          and minimum one enemy.)                 \n" +
            "        Try again with other arguments!           ";

    public static final String ERROR_THREE_LINE_TEMPLATE = "    Oops! Too much enemies or obstacles:          \n" +
            "        Try again with other arguments!           ";

    public static final String NEGATIVE_ARGUMENT_LINE_TEMPLATE = "     Oops! \\uD83D\\uDE40 Arguments can't be negative:       \n" +
            "        Try again with other arguments!           ";

    public static final String GAME_OVER_LINE_TEMPLATE = "                    GAME OVER                    ";
    public static final String WIN_LINE_TEMPLATE = "                   \uD83E\uDD73 YOU WIN!!! \uD83E\uDD73                ";

    public static final String INCORRECT_STEP_LINE_TEMPLATE = "            OOOPS! DON'T BRAKE THE WALL! \uD83D\uDE48       ";

    public static final String ILLEGAL_ARGUMENT_LINE_TEMPLATE = "                Oops! Wrong action:               \n" +
            "                Actions you can do:               \n" + " write 9:     -     Leave the game.       =(      \n" +
            " write w:     -     Make step up.         ↑       \n" + " write d:     -     Make step right.      →       \n" +
            " write s:     -     Make step down.       ↓       \n" + " write a:     -     Make step left.       ←       \n" +
            " write 8:     -     Confirm monster step. ✔       ";
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

    public static final Boolean IS_PRODUCTION = Args.getArgumentProfile();

    public static boolean argumentsValidation() {
        boolean answer = true;
        if (ENEMIES_COUNT < 1) {
            System.out.println(colorize(EMPTY_LINE_TEMPLATE, BOLD(), MAGENTA_TEXT(), YELLOW_BACK()));
            System.out.println(colorize(ERROR_ONE_LINE_TEMPLATE, BOLD(), BLACK_TEXT(), MAGENTA_BACK()));
            System.out.println(colorize(EMPTY_LINE_TEMPLATE, BOLD(), MAGENTA_TEXT(), YELLOW_BACK()));
            answer = false;
        }
        if (SIZE <= 2) {
            System.out.println(colorize(EMPTY_LINE_TEMPLATE, BOLD(), MAGENTA_TEXT(), YELLOW_BACK()));
            System.out.println(colorize(ERROR_TWO_LINE_TEMPLATE, BOLD(), BLACK_TEXT(), MAGENTA_BACK()));
            System.out.println(colorize(EMPTY_LINE_TEMPLATE, BOLD(), MAGENTA_TEXT(), YELLOW_BACK()));
            answer = false;
        }
        if (ENEMIES_COUNT + WALLS_COUNT > SIZE * SIZE) {
            System.out.println(colorize(EMPTY_LINE_TEMPLATE, BOLD(), MAGENTA_TEXT(), YELLOW_BACK()));
            System.out.println(colorize(ERROR_THREE_LINE_TEMPLATE, BOLD(), BLACK_TEXT(), MAGENTA_BACK()));
            System.out.println(colorize(EMPTY_LINE_TEMPLATE, BOLD(), MAGENTA_TEXT(), YELLOW_BACK()));
            answer = false;
        }
        return answer;
    }

    public static void negativeArguments() {
        System.out.println(colorize(EMPTY_LINE_TEMPLATE, BOLD(), MAGENTA_TEXT(), YELLOW_BACK()));
        System.out.println(colorize(NEGATIVE_ARGUMENT_LINE_TEMPLATE, BOLD(), BLACK_TEXT(), MAGENTA_BACK()));
        System.out.println(colorize(EMPTY_LINE_TEMPLATE, BOLD(), MAGENTA_TEXT(), YELLOW_BACK()));
    }

    public static void printPlayerSimbl() {
        printElement(" " + PLAYER_SIMBL + " ", PLAYER_COLOR);
    }

    public static void printPlayerSimbl(Character simbl) {
        printElement(" " + simbl + " ", ENEMY_COLOR);
    }

    public static void printMonsterSimbl() {
        printElement(" " + MONSTER_SIMBL + " ", ENEMY_COLOR);
    }

    public static void printSimblWhenGameOver(Character simbl) {
        printElement(" " + simbl + " ", "WHITE");
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
            case "WHITE":
                System.out.print(colorize(element, BOLD(), BLACK_TEXT(), WHITE_BACK()));
                break;
        }
    }

    public static void printGameOver() {
        System.out.println();
        System.out.println(colorize(EMPTY_LINE_TEMPLATE, BOLD(), YELLOW_TEXT(), YELLOW_BACK()));
        System.out.println(colorize(GAME_OVER_LINE_TEMPLATE, BOLD(), BLACK_TEXT(), BLUE_BACK()));
        System.out.println(colorize(EMPTY_LINE_TEMPLATE, BOLD(), YELLOW_TEXT(), YELLOW_BACK()));

    }

    public static void printYouWin() {
        System.out.println();
        System.out.println(colorize(EMPTY_LINE_TEMPLATE, BOLD(), RED_TEXT(), YELLOW_BACK()));
        System.out.println(colorize(WIN_LINE_TEMPLATE, BOLD(), BLACK_TEXT(), RED_BACK()));
        System.out.println(colorize(EMPTY_LINE_TEMPLATE, BOLD(), RED_TEXT(), YELLOW_BACK()));
    }

    public static void incorrectStep() {
        System.out.println(colorize(EMPTY_LINE_TEMPLATE, BOLD(), MAGENTA_TEXT(), YELLOW_BACK()));
        System.out.println(colorize(INCORRECT_STEP_LINE_TEMPLATE, BOLD(), BLACK_TEXT(), MAGENTA_BACK()));
        System.out.println(colorize(EMPTY_LINE_TEMPLATE, BOLD(), MAGENTA_TEXT(), YELLOW_BACK()));
        System.out.println();
        System.out.println();
    }

    public static void printIllegalArgument() {
        System.out.println(colorize(EMPTY_LINE_TEMPLATE, BOLD(), MAGENTA_TEXT(), YELLOW_BACK()));
        System.out.println(colorize(ILLEGAL_ARGUMENT_LINE_TEMPLATE, BOLD(), BLACK_TEXT(), MAGENTA_BACK()));
        System.out.println(colorize(EMPTY_LINE_TEMPLATE, BOLD(), MAGENTA_TEXT(), YELLOW_BACK()));
    }
}


