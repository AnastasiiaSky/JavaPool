package Game;

import java.util.ArrayList;

import static Game.ObjectsData.*;

public class Wall extends GameElements {
    public Wall(ArrayList<ArrayList<Character>> matrix) {
        super(matrix);
    }

    @Override
    protected void createElement() {
        int count = 0;
        if (WALLS_COUNT >= SIZE * SIZE - 2 - ENEMIES_COUNT) {
            while (count < WALLS_COUNT) {
                int x = (int) (Math.random() * SIZE);
                int y = (int) (Math.random() * SIZE);
                int random = (int) (Math.random() * 10); // changing the density of the group of obstacles
                if (matrix.get(x).get(y).equals(EMPTY_SIMBL)) {
                    matrix.get(x).set(y, OBSTACLE_SIMBL);
                    count++;
                }
            }

        } else {
            while (count < WALLS_COUNT) {
                int x = (int) (Math.random() * SIZE);
                int y = (int) (Math.random() * SIZE);
                int random = (int) (Math.random() * 10); // changing the density of the group of obstacles
                if (matrix.get(x).get(y).equals(EMPTY_SIMBL)
                        && checkNeighbours(matrix, x, y) || random == 1) {
                    matrix.get(x).set(y, OBSTACLE_SIMBL);
                    count++;
                }
            }
        }
    }

    private boolean checkNeighbours(ArrayList<ArrayList<Character>> matrix, int x, int y) {
        if (x == 0 || x == SIZE - 1 || y == 0 || y == SIZE - 1) {
            return false;
        }
        if ((matrix.get(x - 1).get(y).equals(OBSTACLE_SIMBL)
                || matrix.get(x + 1).get(y).equals(OBSTACLE_SIMBL)) &&
                !(matrix.get(x).get(y - 1).equals(OBSTACLE_SIMBL)
                        || matrix.get(x).get(y + 1).equals(OBSTACLE_SIMBL))) {
            return true;
        } else if ((matrix.get(x).get(y - 1).equals(OBSTACLE_SIMBL)
                || matrix.get(x).get(y + 1).equals(OBSTACLE_SIMBL)) &&
                !(matrix.get(x - 1).get(y).equals(OBSTACLE_SIMBL)
                        || matrix.get(x + 1).get(y).equals(OBSTACLE_SIMBL))) {
            return true;
        }
        return false;
    }
}
