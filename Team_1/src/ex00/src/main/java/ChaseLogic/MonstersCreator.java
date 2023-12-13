package ChaseLogic;

import java.util.ArrayList;

import static ChaseLogic.ObjectsData.*;

/* Класс создает монстров на карте игры */

public final class MonstersCreator {
    private ArrayList<ArrayList<Character>> gameBoard;
    private Position currentPosition;


    public MonstersCreator(ArrayList<ArrayList<Character>> gameBoard, final Position purposePosition) {
        this.gameBoard = gameBoard;
        this.currentPosition = new Position(randomIntCreation(), randomIntCreation());
        createMonster();
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    private int randomIntCreation() {
        return (int) (Math.random() * SIZE);
    }
    private void createMonster() {
        while(checkMonsterCreation()) {
           this.currentPosition.setPosition(randomIntCreation(), randomIntCreation());
        }
        gameBoard.get(this.currentPosition.getX()).set(this.currentPosition.getY(), MONSTER_SIMBL);
    }

    private boolean checkMonsterCreation() {
        int x = this.currentPosition.getX(), y = this.currentPosition.getY();
        return gameBoard.get(x).get(y) != EMPTY_SIMBL
                && ((x + 1 < SIZE && gameBoard.get(x + 1).get(y) == PLAYER_SIMBL)
                    || (x - 1 > 0 && gameBoard.get(x - 1).get(y) == PLAYER_SIMBL)
                    || (y + 1 < SIZE && gameBoard.get(x).get(y + 1) == PLAYER_SIMBL)
                    || (y - 1 > 0 && gameBoard.get(x).get(y - 1) == PLAYER_SIMBL));
    }


    @Override
    public String toString() {
        return "{Monster was created on position " + currentPosition.toString();
    }
}
