package ChaseLogic;

import java.util.ArrayList;

// TODO РАМКA ПОЛЯ

public class Program {
    public static final int SIZE = 10;
    public static void main(String[] args) {

        ArrayList<ArrayList<Character>> gameBoard = new ArrayList<ArrayList<Character>>();
        for(int it = 0; it < SIZE; ++it) {
            gameBoard.add(it, new ArrayList<Character>());
            for (int j = 0; j < SIZE; ++j) {
                if(it == 0 || it == SIZE - 1) {
                    gameBoard.get(it).add(j, '.');
                } else {
                    if(j == 0 || j == SIZE - 1) {
                        gameBoard.get(it).add(j, '.');
                    } else {
                        gameBoard.get(it).add(j, ' ');
                    }
                }
            }
        }

//        gameBoard.get(16).set(5, 'o');
//        gameBoard.get(10).set(14, 'O');
//        gameBoard.get(8).set(11, '#');
//        gameBoard.get(7).set(11, '#');
//        gameBoard.get(6).set(11, '#');
//        gameBoard.get(6).set(12, '#');
//        gameBoard.get(6).set(13, '#');
//        gameBoard.get(6).set(14, '#');
//        gameBoard.get(8).set(10, '#');
//        gameBoard.get(8).set(9, '#');
//        gameBoard.get(8).set(8, '#');
//        gameBoard.get(12).set(11, '#');
//        gameBoard.get(12).set(12, '#');
//        gameBoard.get(12).set(13, '#');
//        gameBoard.get(12).set(14, '#');
//
//        gameBoard.get(3).set(2, '#');
//        gameBoard.get(3).set(4, '#');
//        gameBoard.get(3).set(1, '#');
//        gameBoard.get(3).set(3, '#');

        gameBoard.get(3).set(5, 'o');
        gameBoard.get(9).set(1, 'O');
        gameBoard.get(8).set(8, '#');
        gameBoard.get(7).set(8, '#');
        gameBoard.get(6).set(8, '#');
        gameBoard.get(6).set(6, '#');
        gameBoard.get(6).set(7, '#');





        MonstersTask monsters = new MonstersTask(gameBoard, 1);
        for(int it = 0; it < gameBoard.size(); ++it) {
            for (int j = 0; j < gameBoard.get(it).size(); ++j) {
                System.out.printf("%3c", gameBoard.get(it).get(j));
            }
            System.out.println();
        }

            monsters.executeMonsterTask();


            for (int it = 0; it < gameBoard.size(); ++it) {
                for (int j = 0; j < gameBoard.get(it).size(); ++j) {
                    System.out.printf("%3c", gameBoard.get(it).get(j));
                }
                System.out.println();
            }

        monsters.executeMonsterTask();


        for (int it = 0; it < gameBoard.size(); ++it) {
            for (int j = 0; j < gameBoard.get(it).size(); ++j) {
                System.out.printf("%3c", gameBoard.get(it).get(j));
            }
            System.out.println();
        }

        monsters.executeMonsterTask();


        for (int it = 0; it < gameBoard.size(); ++it) {
            for (int j = 0; j < gameBoard.get(it).size(); ++j) {
                System.out.printf("%3c", gameBoard.get(it).get(j));
            }
            System.out.println();
        }
    }
}
