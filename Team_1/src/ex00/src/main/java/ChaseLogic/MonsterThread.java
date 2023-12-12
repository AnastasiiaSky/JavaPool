package ChaseLogic;

import ChaseLogic.Position;

import java.util.ArrayList;

public class MonsterThread implements Runnable {
    private ArrayList<ArrayList<Character>> gameBoard;
    private Position currentMonsterPosition;
    private Position purposePosition;

    private ArrayList<ArrayList<Integer>> gameBoardCave;

    public MonsterThread(Position currentMonsterPosition, Position purposePosition,
                         ArrayList<ArrayList<Character>> gameBoard) {
        this.gameBoard = gameBoard;
        this.currentMonsterPosition = currentMonsterPosition;
        this.purposePosition = purposePosition;
        this.gameBoardCave = new MonsterCave(gameBoard, currentMonsterPosition).getGameBoardCave();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Я запущен");
    }
}
