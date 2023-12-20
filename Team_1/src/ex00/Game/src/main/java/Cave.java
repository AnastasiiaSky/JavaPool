package Game.src.main.java;


import java.util.ArrayList;

import static Game.src.main.java.ObjectsData.*;

public final class Cave {
    private final ArrayList<ArrayList<Character>> gameBoard;
    private ArrayList<ArrayList<Integer>> gameBoardCave;

    public Cave(final ArrayList<ArrayList<Character>> gameBoard) {
        this.gameBoard = gameBoard;
        this.gameBoardCave = new ArrayList<>();
        createCaveMatrix();
    }

//    public ArrayList<ArrayList<Character>> getGameBoard() {
//        return gameBoard;
//    }

//    public int getCaveElement(final int x, final int y) {
//        return this.gameBoardCave.get(x).get(y);
//    }


    public ArrayList<ArrayList<Integer>> getGameBoardCave() {
        return gameBoardCave;
    }

    private void createCaveMatrix() {
        ArrayList<ArrayList<Integer>> cave = new ArrayList<>();
        for (int i = 0; i < SIZE; ++i) {
            cave.add(i, new ArrayList<>());
            for (int j = 0; j < SIZE; ++j) {
                if (gameBoard.get(i).get(j).equals(OBSTACLE_SIMBL)
                        || gameBoard.get(i).get(j).equals(MONSTER_SIMBL)
                        || gameBoard.get(i).get(j).equals(TARGET_SIMBL)) {
                    cave.get(i).add(j, -1);
                } else {
                    cave.get(i).add(j, 0);
                }
            }
        }
        this.gameBoardCave = cave;
    }
}
