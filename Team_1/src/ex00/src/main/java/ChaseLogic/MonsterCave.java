package ChaseLogic;

import java.util.ArrayList;

import static ChaseLogic.ObjectsData.*;

/* Создаем пещеру для монстра */

public class MonsterCave {
    private ArrayList<ArrayList<Character>> gameBoard;
    private Position currentMonsterPosition;
    private ArrayList<ArrayList<Integer>> gameBoardCave;

    public MonsterCave(final ArrayList<ArrayList<Character>> gameBoard,
                       Position currentPosition) {
        this.gameBoard = gameBoard;
        this.currentMonsterPosition = currentPosition;
        this.gameBoardCave = createCaveMatrix();
//        printCave();
    }

    public ArrayList<ArrayList<Integer>> getGameBoardCave() {
        return gameBoardCave;
    }

    public void setGameBoardCave(ArrayList<ArrayList<Character>> gameBoard) { // изменение пещеры
        this.gameBoard = gameBoard;
        this.gameBoardCave = createCaveMatrix();
    }

    private ArrayList<ArrayList<Integer>> createCaveMatrix() {
        int width = gameBoard.size(), length = gameBoard.get(0).size();
        ArrayList<ArrayList<Integer>> cave = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < width; ++i) {
            cave.add(i, new ArrayList<Integer>());
            for (int j = 0; j < length; ++j) {
                if(gameBoard.get(i).get(j) == OBSTACLE_SIMBL
                        || gameBoard.get(i).get(j) == MONSTER_SIMBL
                        || gameBoard.get(i).get(j) == TARGET_SIMBL) {
                    cave.get(i).add(j, 1);
                } else {
                    cave.get(i).add(j, 0);
                }
            }
        }
        cave.get(currentMonsterPosition.getX()).set(currentMonsterPosition.getY(), 0);
        return cave;
    }

//    private void printCave() {
//        for(int it = 0; it < gameBoardCave.size(); ++it) {
//            for (int j = 0; j < gameBoardCave.get(it).size(); ++j) {
//                System.out.print(gameBoardCave.get(it).get(j));
//            }
//            System.out.println();
//        }
//    }
}
