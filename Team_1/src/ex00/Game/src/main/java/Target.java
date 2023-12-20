package Game.src.main.java;

import java.util.ArrayList;

import static Game.src.main.java.ObjectsData.*;


public class Target extends GameElements {

    Target(ArrayList<ArrayList<Character>> matrix) {
        super(matrix);
    }

    protected void createElement() {
        Position position = searchEmptyIndex(matrix);
        matrix.get(position.getX()).set(position.getY(), TARGET_SIMBL);
    }
}
