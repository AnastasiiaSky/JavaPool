package Game.src.main.java;


import java.util.ArrayList;

import static Game.src.main.java.ObjectsData.*;

public class GameBoard {
    private ArrayList<ArrayList<Character>> matrix = null;

    public GameBoard() {
        createMatrix();
    }

    public GameBoard(ArrayList<ArrayList<Character>> matrix) {
        this.matrix = matrix;
    }


    public ArrayList<ArrayList<Character>> getMatrix() {
        return matrix;
    }

    private void createMatrix() {
        matrix = new ArrayList<>();
        for (int i = 0; i < SIZE; ++i) {
            ArrayList<Character> row = new ArrayList<>();
            for (int j = 0; j < SIZE; ++j) {
                row.add(EMPTY_SIMBL);
            }
            matrix.add(row);
        }
    }


    public void printMatrix() {
        for (int i = 0; i < SIZE; ++i) {
            for (int j = 0; j < SIZE; ++j) {
                if (matrix.get(i).get(j).equals(EMPTY_SIMBL)) {
                    printEmptySimbl();
                } else if (matrix.get(i).get(j).equals(OBSTACLE_SIMBL)) {
                    printObstacleSimbl();
                } else if (matrix.get(i).get(j).equals(PLAYER_SIMBL)) {
                    printPlayerSimbl();
                } else if (matrix.get(i).get(j).equals(TARGET_SIMBL)) {
                    printTargetSimbl();
                } else if (matrix.get(i).get(j).equals(MONSTER_SIMBL)) {
                    printMonsterSimbl();
                }
            }
            System.out.println();
        }
    }

    public void printGameOverMatrix() {
        for (int i = 0; i < SIZE; ++i) {
            for (int j = 0; j < SIZE; ++j) {
                if (matrix.get(i).get(j).equals(EMPTY_SIMBL)) {
                    printEmptySimbl();
                } else if (matrix.get(i).get(j).equals(OBSTACLE_SIMBL)) {
                    printSimblWhenGameOver(OBSTACLE_SIMBL);
                } else if (matrix.get(i).get(j).equals(PLAYER_SIMBL)) {
                    printPlayerSimbl(PLAYER_SIMBL);
                } else if (matrix.get(i).get(j).equals(TARGET_SIMBL)) {
                    printSimblWhenGameOver(TARGET_SIMBL);
                } else if (matrix.get(i).get(j).equals(MONSTER_SIMBL)) {
                    printSimblWhenGameOver(MONSTER_SIMBL);
                }
            }
            System.out.println();
        }
    }

    public void monsterActionMatrix(Position oldMonsterPosition) {
        for (int i = 0; i < SIZE; ++i) {
            for (int j = 0; j < SIZE; ++j) {
                if (matrix.get(i).get(j).equals(EMPTY_SIMBL)) {
                    if (i == oldMonsterPosition.getX() && j == oldMonsterPosition.getY()) {
                        printSimblWhenGameOver(MONSTER_SIMBL);
                    } else {
                        printEmptySimbl();
                    }
                } else if (matrix.get(i).get(j).equals(OBSTACLE_SIMBL)) {
                    printObstacleSimbl();
                } else if (matrix.get(i).get(j).equals(PLAYER_SIMBL)) {
                    printPlayerSimbl();
                } else if (matrix.get(i).get(j).equals(TARGET_SIMBL)) {
                    printTargetSimbl();
                } else if (matrix.get(i).get(j).equals(MONSTER_SIMBL)) {
                    printMonsterSimbl();
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
