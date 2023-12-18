package ChaseLogic;


import Game.Position;
import java.util.ArrayList;
import java.util.Scanner;
import static Game.ObjectsData.*;
import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;


import Game.GameBoard;


public class MonstersTask {
    private GameBoard gameBoard;
    private ArrayList<Position> monstersList;
    private Position purposePosition;


    public MonstersTask(GameBoard gameBoard, ArrayList<Position> monstersList) {
        this.gameBoard = gameBoard;
        this.monstersList = monstersList;
        this.purposePosition = findPlayerPosition();
    }


    public boolean executeMonsterTask(GameBoard gameBoard, ArrayList<Position> monstersList) {
        setCurrentData(gameBoard, monstersList);
        Cave currentCave = new Cave(this.gameBoard.getMatrix());
        boolean result = true;
        for (int it = 0; it < ENEMIES_COUNT; ++it) {
            Wave currentWave = new Wave(monstersList.get(it), purposePosition, currentCave);
            currentWave.createRealWave();
            Position[] newAndOldPosition = currentWave.getNextStep();
            Position oldPosition = new Position(0, 0);
            if (newAndOldPosition != null && newAndOldPosition[0] != null && newAndOldPosition[1] != null) {
                oldPosition = newAndOldPosition[1];
                Position newPosition = newAndOldPosition[0];

                result = ifNotProduction(oldPosition, newPosition, it);
            }

            if (IS_PRODUCTION) {
                gameBoard.monsterActionMatrix(oldPosition);
                System.out.println();

                Scanner sc = new Scanner(System.in);
                int in = 0;
                do {
                    System.out.print(colorize("Для подтверждения хода монстра " + (it + 1) + " из " + ENEMIES_COUNT + " введите 8: ", BOLD(), BLACK_TEXT(), RED_BACK()));
                    if (!sc.hasNextInt()) {
                        System.out.println(colorize("Не " + sc.next() + ", a '8', пожалуйста.", BOLD(), BLACK_TEXT(), RED_BACK()));
                        continue;
                    }
                    in = sc.nextInt();
                } while (in != 8);


                gameBoard.printMatrix();
                System.out.println();
                if (it + 1 == ENEMIES_COUNT) {
                    System.out.println(colorize("Ход монстров окончен. Теперь очередь игрока.", BOLD(), BLACK_TEXT(), GREEN_BACK()));
                    System.out.println();
                }
            }
            if (!result) break;

        }
        if (!IS_PRODUCTION) {
            gameBoard.printMatrix();
        }

        return result;
    }

    private boolean ifNotProduction(Position oldPosition, Position newPosition, int it) {
        this.gameBoard.getMatrix().get(oldPosition.getX()).set(oldPosition.getY(), EMPTY_SIMBL);
        this.gameBoard.getMatrix().get(newPosition.getX()).set(newPosition.getY(), MONSTER_SIMBL);
        this.monstersList.get(it).setPosition(newPosition.getX(), newPosition.getY());
        return true;
    }

    private void setCurrentData(GameBoard gameBoard, ArrayList<Position> monstersList) {
        this.monstersList = monstersList;
        this.gameBoard = gameBoard;
        this.purposePosition = findPlayerPosition();
    }

    private Position findPlayerPosition() {
        for (int i = 0; i < SIZE; ++i)
            for (int j = 0; j < SIZE; ++j)
                if (gameBoard.getMatrix().get(i).get(j).equals(PLAYER_SIMBL)) {
                    return new Position(i, j);
                }
        return null;
    }
}
