package ChaseLogic;

import java.util.ArrayList;

import static ChaseLogic.ObjectsData.*;

public final class Cave {
    private ArrayList<ArrayList<Character>> gameBoard;
    private ArrayList<ArrayList<Integer>> gameBoardCave;

    public Cave(final ArrayList<ArrayList<Character>> gameBoard) {
        this.gameBoard = gameBoard;
        this.gameBoardCave = new ArrayList<>();
        createCaveMatrix();
    }

    public int getCaveElement(final int x, final int y) {

        return this.gameBoardCave.get(x).get(y);
    }

    private void createCaveMatrix() {
        int width = gameBoard.size(), length = gameBoard.get(0).size();
        ArrayList<ArrayList<Integer>> cave = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < width; ++i) {
            cave.add(i, new ArrayList<Integer>());
            for (int j = 0; j < length; ++j) {
                if(gameBoard.get(i).get(j) == OBSTACLE_SIMBL
                        || gameBoard.get(i).get(j) == MONSTER_SIMBL
                        || gameBoard.get(i).get(j) == TARGET_SIMBL ) {
                    cave.get(i).add(j, -1);
                } else {
                    cave.get(i).add(j, 0);
                }
            }
        }
        this.gameBoardCave = cave;
    }
}
