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
    private ArrayList<Thread> monsterThreads; //!  создаем список потоков монстров



    public MonstersTask(ArrayList<ArrayList<Character>> gameBoard, int monsters_count) {
        int width = gameBoard.size(), length = gameBoard.get(0).size();
        this.gameBoard = gameBoard;
        this.MONSTER_COUNT = monsters_count;
        this.monstersList = new ArrayList<>();
        this.purposePosition = new Position(0, 0, width, length);
        this.monsterThreads = new ArrayList<>();
        executeCreation();
    }

    private void executeCreation() {
        findPlayerPosition();
        createMonsters();
        createMonsterThreads();
//        printMonstersList();
    }

    private void createMonsterThreads() {
        for(int it = 0; it < monstersList.size(); ++it) {
            Thread monster = new Thread(new MonsterThread(this.monstersList.get(it), this.purposePosition, gameBoard));
            System.out.println(monster.getName());
            monsterThreads.add(monster);
        }
    }

    public ArrayList<ArrayList<Character>> getGameBoard() {
        return this.gameBoard;
    }

    public void setGameBoard(ArrayList<ArrayList<Character>> gameBoard) {
        this.gameBoard = gameBoard;
    }

    private void findPlayerPosition() {
        for(int i = 0; i < gameBoard.size(); ++i)
            for(int j = 0; j < gameBoard.get(i).size(); ++j)
                if(gameBoard.get(i).get(j).equals(PLAYER_SIMBL))
                    this.purposePosition.setPosition(i, j);
    }
    private void createMonsters() {
        for(int i = 0; i < MONSTER_COUNT; ++i) {
            MonstersCreator monster = new MonstersCreator(gameBoard, purposePosition);
            monstersList.add(monster.getCurrentPosition());
//            System.out.println(monster.toString());
        }
    }

//    private void printMonstersList() {
//        System.out.println("MonstersList");
//        for (Position cur : monstersList) {
//            System.out.println(cur.toString());
//        }
//    }
}
