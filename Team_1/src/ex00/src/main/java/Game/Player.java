package Game;

import java.util.ArrayList;

import static Game.ObjectsData.*;

public class Player extends GameElements {

    private String move = null;
    private static Position position;

    private boolean moveCompleted = false;

    Player(ArrayList<ArrayList<Character>> matrix) {
        super(matrix);
    }

    Player(ArrayList<ArrayList<Character>> matrix, String move) {
        this.matrix = matrix;
        this.move = move;
    }
    @Override
    protected void createElement() {
        Position position = searchEmptyIndex(matrix);
        matrix.get(position.getX()).set(position.getY(), PLAYER_SIMBL);
    }

    public boolean movePlayer() {
        for (int y = 0; y < SIZE; ++y) {
            for (int x = 0; x < SIZE; ++x) {
                if (matrix.get(x).get(y).equals(PLAYER_SIMBL)) {
                    position = new Position(x, y);
                    break;
                }
            }
        }
        switch (move) {
            case "a":
                if (checkPosition(position.getX(), position.getY() - 1)) {
                        matrix.get(position.getX()).set(position.getY(), EMPTY_SIMBL);
                    matrix.get(position.getX()).set(position.getY() - 1, PLAYER_SIMBL);
                    moveCompleted = true;
                } else {
                    incorrectStep();
                    return false;
                }
                break;
            case "w":
                if (checkPosition(position.getX() - 1, position.getY())) {
                        matrix.get(position.getX()).set(position.getY(), EMPTY_SIMBL);
                    matrix.get(position.getX() - 1).set(position.getY(), PLAYER_SIMBL);
                    moveCompleted = true;
                } else {
                    incorrectStep();
                    return false;
                }
                break;
            case "d":
                if (checkPosition(position.getX(), position.getY() + 1)) {
                        matrix.get(position.getX()).set(position.getY(), EMPTY_SIMBL);
                    matrix.get(position.getX()).set(position.getY() + 1, PLAYER_SIMBL);
                    moveCompleted = true;
                } else {
                    incorrectStep();
                    return false;
                }
                break;
            case "s":
                if (checkPosition(position.getX() + 1, position.getY())) {
                        matrix.get(position.getX()).set(position.getY(), EMPTY_SIMBL);
                    matrix.get(position.getX() + 1).set(position.getY(), PLAYER_SIMBL);
                    moveCompleted = true;
                } else {
                    incorrectStep();
                    return false;
                }
                break;
        }
        return true;
    }

    private boolean checkPosition(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            return false;
        }
        if (matrix.get(x).get(y).equals(TARGET_SIMBL)) {
            printYouWin();
            System.exit(0);
        }
        if(matrix.get(x).get(y).equals(MONSTER_SIMBL)){
            GameBoard gameOver = new GameBoard(super.matrix);
            gameOver.printGameOverMatrix();
            printGameOver();
            System.exit(0);
        }
        return matrix.get(x).get(y).equals(EMPTY_SIMBL);
    }
}
