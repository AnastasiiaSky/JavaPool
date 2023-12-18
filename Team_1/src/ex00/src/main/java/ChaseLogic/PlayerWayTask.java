package ChaseLogic;

import Game.GameBoard;
import Game.Position;

import java.util.ArrayDeque;
import java.util.ArrayList;

import static Game.ObjectsData.*;
import static Game.ObjectsData.SIZE;

public class PlayerWayTask {
    private final GameBoard gameBoard;
    private final Position player;
    private final Position goal;
    private ArrayList<ArrayList<Integer>> cave;
    private ArrayList<ArrayList<Integer>> wave;

    private ArrayList<ArrayList<Boolean>> visibility;
    private boolean answer;

    public PlayerWayTask(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.player = findElement(PLAYER_SIMBL);
        this.goal = findElement(TARGET_SIMBL);
        this.answer = false;
        createCave();
        createVisibility();
        startPlayerWave();
    }

    public boolean isAnswer() {
        return answer;
    }

    private Position findElement(Character simbl) {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++) {
                if(gameBoard.getMatrix().get(i).get(j).equals(simbl))
                    return new Position(i, j);
            }
        return null;
    }

    private void createCave() {
        ArrayList<ArrayList<Integer>> tmp = new ArrayList<>();
        ArrayList<ArrayList<Integer>> tmp1 = new ArrayList<>();

        for (int i = 0; i < SIZE; i++) {
            tmp.add(i, new ArrayList<>());
            tmp1.add(i, new ArrayList<>());

            for (int j = 0; j < SIZE; j++) {
                if(this.gameBoard.getMatrix().get(i).get(j).equals(MONSTER_SIMBL)
                        || this.gameBoard.getMatrix().get(i).get(j).equals(OBSTACLE_SIMBL)) {
                    tmp.get(i).add(j, -1);
                } else {
                    tmp.get(i).add(j, 0);
                }
                tmp1.get(i).add(0);
            }
        }
        this.cave = tmp;
        this.wave = tmp1;
    }

    private void createVisibility() {
        ArrayList<ArrayList<Boolean>> tmp = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            tmp.add(i, new ArrayList<>());
            for (int j = 0; j < SIZE; j++) {
                tmp.get(i).add(j, false);
            }
        }
        this.visibility = tmp;
    }

    private void startPlayerWave() {
        Position position = this.player;
        ArrayDeque<Position> points = new ArrayDeque<>();
        points.add(position);
        while (!points.isEmpty()) {
            position = points.poll();
            int x = position.getX();
            int y = position.getY();
            if (position.equals(this.goal)) {
                this.answer = true;
            }
            if (this.visibility.get(x).get(y)) continue;
            getNextPositions(x, y, points);
            this.visibility.get(x).set(y, true);
        }
    }


    private void getNextPositions(final int x, final int y, ArrayDeque<Position> points) {
        int xRight = x + 1, xLeft = x - 1, yUp = y + 1, yDown = y - 1;
        if (xRight < SIZE && getCaveElement(xRight, y) == 0
                && !this.visibility.get(xRight).get(y)) {
            points.add(new Position(xRight, y));
            this.wave.get(xRight).set(y, this.wave.get(x).get(y) + 1);
        }

        if (xLeft >= 0 && getCaveElement(xLeft, y) == 0
                && !this.visibility.get(xLeft).get(y)) {
            points.add(new Position(xLeft, y));
            this.wave.get(xLeft).set(y, this.wave.get(x).get(y) + 1);
        }

        if (yUp < SIZE && getCaveElement(x, yUp) == 0
                && !this.visibility.get(x).get(yUp)) {
            points.add(new Position(x, yUp));
            this.wave.get(x).set(yUp, this.wave.get(x).get(y) + 1);
        }

        if (yDown >= 0 && getCaveElement(x, yDown) == 0
                && !this.visibility.get(x).get(yDown)) {
            points.add(new Position(x, yDown));
            this.wave.get(x).set(yDown, this.wave.get(x).get(y) + 1);
        }
    }

    private Integer getCaveElement(int x, int y) {
        return this.cave.get(x).get(y);
    }
}
