package ChaseLogic;

import ChaseLogic.Position;

import java.util.ArrayList;

public class MonsterThread implements Runnable {
    private ArrayList<ArrayList<Character>> gameBoard;
    private Position currentMonsterPosition;
    private Position purposePosition;
//    private ArrayList<ArrayList<Integer>> gameBoardCave;
    private MonsterCave gameBoardCave;

    public MonsterThread(Position currentMonsterPosition, Position purposePosition,
                         ArrayList<ArrayList<Character>> gameBoard) {
        this.gameBoard = gameBoard;
        this.currentMonsterPosition = currentMonsterPosition;
        this.purposePosition = purposePosition;
        this.gameBoardCave = new MonsterCave(gameBoard, currentMonsterPosition);
//        gameBoardCave.printCave(); - пещера ок
    }

    @Override
    public void run() {
        while(!currentMonsterPosition.equals(purposePosition)) {

            // поиск кратчайшего пути
            // получение следующего шага
            // шаг в сторону purposePosition
            // измененик gameBoard в соответствии со сделанным шагом
        }
    }

    @Override
    public String toString() {
        return "MonsterThread{" +
                Thread.currentThread().getName()
                + " on position "
                + currentMonsterPosition;
    }
}
