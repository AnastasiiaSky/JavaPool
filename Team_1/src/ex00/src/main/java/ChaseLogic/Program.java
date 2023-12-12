package ChaseLogic;

import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        final int SIZE = 18;
        ArrayList<ArrayList<Character>> gameBoard = new ArrayList<ArrayList<Character>>();
        for(int it = 0; it < SIZE; ++it) {
            gameBoard.add(it, new ArrayList<Character>());
            for (int j = 0; j < SIZE; ++j) {
                if(it == 0 || it == SIZE - 1) {
                    gameBoard.get(it).add(j, '_');
                } else {
                    if(j == 0 || j == SIZE - 1) {
                        gameBoard.get(it).add(j, '|');
                    } else {
                        gameBoard.get(it).add(j, ' ');
                    }
                }
            }
        }

        gameBoard.get(16).set(5, 'o');
        gameBoard.get(10).set(14, 'O');
        gameBoard.get(8).set(11, '#');
        gameBoard.get(7).set(11, '#');
        gameBoard.get(6).set(11, '#');
        gameBoard.get(6).set(12, '#');
        gameBoard.get(6).set(13, '#');
        gameBoard.get(6).set(14, '#');
        gameBoard.get(8).set(10, '#');
        gameBoard.get(8).set(9, '#');
        gameBoard.get(8).set(8, '#');
        gameBoard.get(12).set(11, '#');
        gameBoard.get(12).set(12, '#');
        gameBoard.get(12).set(13, '#');
        gameBoard.get(12).set(14, '#');

        gameBoard.get(3).set(2, '#');
        gameBoard.get(3).set(4, '#');
        gameBoard.get(3).set(1, '#');
        gameBoard.get(3).set(3, '#');



        MonstersTask monsters = new MonstersTask(gameBoard, 2);



        for(int it = 0; it < gameBoard.size(); ++it) {
            for (int j = 0; j < gameBoard.get(it).size(); ++j) {
                System.out.print(gameBoard.get(it).get(j));
            }
            System.out.println();
        }
    }
}
