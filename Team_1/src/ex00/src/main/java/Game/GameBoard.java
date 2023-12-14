package Game;

import java.util.ArrayList;
import static Game.ObjectsData.*;

public class GameBoard {
    private final int size;

    private ArrayList<ArrayList<Character>> matrix = null;

    public GameBoard(int size, int wall) {
        this.size = size;
        createMatrix(size);
        createWall(matrix, wall);
        createPlayer();
        createTarget();
    }

    public ArrayList<ArrayList<Character>> getMatrix(){
        return matrix;
    }

    public void movePlayer(int x, int y){
//        matrix.get(x).
    }


    private void createTarget() {
        Position position = searchEmptyIndex(matrix);
        matrix.get(position.getX()).set(position.getY(), ObjectsData.TARGET_SIMBL);
    }


    private void createPlayer() {
        Position position = searchEmptyIndex(matrix);
        matrix.get(position.getX()).set(position.getY(), ObjectsData.PLAYER_SIMBL);
    }

    private Position searchEmptyIndex(ArrayList<ArrayList<Character>> matrix) {
        while (true) {
            int x = (int) (Math.random() * matrix.size());
            int y = (int) (Math.random() * matrix.get(0).size());
            if (matrix.get(x).get(y).equals(ObjectsData.EMPTY_SIMBL)) {
                return new Position(x, y);
            }
        }
    }

    private void createWall(ArrayList<ArrayList<Character>> matrix, int wall) {
        int count = 0;
        while (count < wall) {
            int x = (int) (Math.random() * matrix.size());
            int y = (int) (Math.random() * matrix.get(0).size());
            int random = (int) (Math.random() * 500); // changing the density of the group of obstacles
            if (matrix.get(x).get(y).equals(ObjectsData.EMPTY_SIMBL)) {
                if (checkNeighbours(matrix, x, y) || random == 1) {
                    matrix.get(x).set(y, ObjectsData.OBSTACLE_SIMBL);
                    count++;
                }
            }
        }
    }

    boolean checkNeighbours(ArrayList<ArrayList<Character>> matrix, int x, int y) {
        if (x == 0 || x == ObjectsData.SIZE - 1 || y == 0 || y == ObjectsData.SIZE - 1) {
            return false;
        }
        if ((matrix.get(x - 1).get(y).equals(ObjectsData.OBSTACLE_SIMBL)
                || matrix.get(x + 1).get(y).equals(ObjectsData.OBSTACLE_SIMBL)) &&
                !(matrix.get(x).get(y - 1).equals(ObjectsData.OBSTACLE_SIMBL)
                        || matrix.get(x).get(y + 1).equals(ObjectsData.OBSTACLE_SIMBL))) {
            return true;
        } else if ((matrix.get(x).get(y - 1).equals(ObjectsData.OBSTACLE_SIMBL)
                || matrix.get(x).get(y + 1).equals(ObjectsData.OBSTACLE_SIMBL)) &&
                !(matrix.get(x - 1).get(y).equals(ObjectsData.OBSTACLE_SIMBL)
                        || matrix.get(x + 1).get(y).equals(ObjectsData.OBSTACLE_SIMBL))) {
            return true;
        }
        return false;
    }

    private void createMatrix(int size) {
        matrix = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            ArrayList<Character> row = new ArrayList<>();
            for (int j = 0; j < size; ++j) {
//                System.out.println(ObjectsData.OBSTACLE_SIMBL);
                row.add(ObjectsData.EMPTY_SIMBL);
            }
            matrix.add(row);
        }
    }

    public void printMatrix() {
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (matrix.get(i).get(j).equals(ObjectsData.EMPTY_SIMBL)) {
                    ObjectsData.printEmptySimbl();
                } else if (matrix.get(i).get(j).equals(ObjectsData.OBSTACLE_SIMBL)) {
                    ObjectsData.printObstacleSimbl();
                } else if (matrix.get(i).get(j).equals(ObjectsData.PLAYER_SIMBL)) {
                    ObjectsData.printPlayerSimbl();
                } else if (matrix.get(i).get(j).equals(ObjectsData.TARGET_SIMBL)) {
                    ObjectsData.printTargetSimbl();
                }
            }
            System.out.println();
        }
    }
}
