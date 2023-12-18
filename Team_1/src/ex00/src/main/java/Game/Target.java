package Game;

import java.util.ArrayList;

import static Game.ObjectsData.*;


public class Target extends GameElements {

    Target(ArrayList<ArrayList<Character>> matrix) {
        super(matrix);
    }

    protected void createElement() {
        Position position = searchEmptyIndex(matrix);
        matrix.get(position.getX()).set(position.getY(), TARGET_SIMBL);
    }
}
