package Game;

import java.util.ArrayList;

import static Game.ObjectsData.*;


public abstract class GameElements {
    protected ArrayList<ArrayList<Character>> matrix = null;

    GameElements(ArrayList<ArrayList<Character>> matrix) {
        this.matrix = matrix;
        createElement();
    }

    protected GameElements() {
    }

    protected Position searchEmptyIndex(ArrayList<ArrayList<Character>> matrix) {
        while (true) {
            int x = (int) (Math.random() * matrix.size());
            int y = (int) (Math.random() * matrix.get(0).size());
            if (matrix.get(x).get(y).equals(EMPTY_SIMBL)) {
                return new Position(x, y);
            }
        }
    }

    protected abstract void createElement();

}
