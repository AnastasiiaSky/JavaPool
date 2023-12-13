package ChaseLogic;

import java.util.ArrayList;
import static ChaseLogic.ObjectsData.*;

/* Класс должен описывать всю логику создания, движения монстров итд */
/* ? Первая передача карты игры сюда, необходимо вызвать конструктор).
   ? Для получения текущего gameBoard - getGameBoard()
   ? для установки нового gameBoard - setGameBoard() */

public class MonstersTask {
    private final int MONSTER_COUNT;
    private ArrayList<ArrayList<Character>> gameBoard;
    private ArrayList<Position> monstersList; //!  создаем лист монстров где мы будем хранить координаты всех монстров
    private Position purposePosition;



    public MonstersTask(ArrayList<ArrayList<Character>> gameBoard, int monsters_count) {
        this.gameBoard = gameBoard;
        this.MONSTER_COUNT = monsters_count;
        this.monstersList = new ArrayList<>();
        this.purposePosition = new Position(1, 1);
        executeCreation();
    }

    private void executeCreation() {
        findPlayerPosition();
        createMonsters();
    }

    public void executeMonsterTask() {
        Cave currentCave = new Cave(this.gameBoard);
        for(int it = 0; it < monstersList.size(); ++it) {
            Wave currentWave = new Wave(monstersList.get(it), purposePosition, currentCave);
            Position newPosition = currentWave.getNextStep();
            this.gameBoard.get(monstersList.get(it).getX()).set(monstersList.get(it).getY(), EMPTY_SIMBL);
            this.gameBoard.get(newPosition.getX()).set(newPosition.getY(), MONSTER_SIMBL);
        }

    }

    public ArrayList<ArrayList<Character>> getGameBoard() {
        return this.gameBoard;
    }

    public void setGameBoard(ArrayList<ArrayList<Character>> gameBoard) {
        this.gameBoard = gameBoard;
    }

    private void findPlayerPosition() {
        for(int i = 0; i < SIZE; ++i)
            for(int j = 0; j < SIZE; ++j)
                if(gameBoard.get(i).get(j).equals(PLAYER_SIMBL)) {
                    this.purposePosition.setPosition(i, j);
                }
    }
    private void createMonsters() {
        for(int i = 0; i < MONSTER_COUNT; ++i) {
            MonstersCreator monster = new MonstersCreator(gameBoard, purposePosition);
            monstersList.add(monster.getCurrentPosition());
        }
    }

//    private void printMonstersList() {
//        System.out.println("MonstersList");
//        for (Position cur : monstersList) {
//            System.out.println(cur.toString());
//        }
//    }
}
