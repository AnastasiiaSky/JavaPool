package ChaseLogic;

import java.util.*;

import Game.GameBoard;
import Game.Position;

import static Game.ObjectsData.*;

public final class Wave {
    private final Position currentPosition;
    private final Position purposePosition;

    private final Cave cave;
    private ArrayList<ArrayList<Integer>> wave;
    private ArrayList<ArrayList<Boolean>> visibility;
    private Position[] nextStep;


    public Wave(Position currentPosition, Position purposePosition, Cave cave) {
        this.currentPosition = currentPosition;
        this.purposePosition = purposePosition;
        this.cave = cave;
        checkGameOver();
        createVisibility();
        createEmptyWave();
        this.nextStep = createRealWave();
    }

    public Position[] getNextStep() {
        return nextStep;
    }

    private void createEmptyWave() {
        ArrayList<ArrayList<Integer>> emptyWave = new ArrayList<>(SIZE);
        for (int i = 0; i < SIZE; ++i) {
            emptyWave.add(i, new ArrayList<>(SIZE));
            for (int j = 0; j < SIZE; ++j)
                emptyWave.get(i).add(j, -1);
        }
        this.wave = emptyWave;
    }

    private void createVisibility() {
        ArrayList<ArrayList<Boolean>> data = new ArrayList<>(SIZE);
        for (int i = 0; i < SIZE; ++i) {
            data.add(i, new ArrayList<>(SIZE));
            for (int j = 0; j < SIZE; ++j)
                data.get(i).add(j, false);
        }
        this.visibility = data;
    }

    public Position[] createRealWave() {
        Position position = this.currentPosition;
        ArrayDeque<Position> points = new ArrayDeque<>();
        points.add(position);
        this.wave.get(position.getX()).set(position.getY(), 0);
        while (!points.isEmpty()) {
            position = points.poll();
            int x = position.getX(), y = position.getY();
            if (position.equals(this.purposePosition)) {
                return RestoreWayAndReturnNextMonsterStep(purposePosition.getX(), purposePosition.getY());
            }
            if (this.visibility.get(x).get(y)) continue;
            getNextPositions(x, y, points);
            this.visibility.get(x).set(y, true);
        }
        return null;
    }



    private void getNextPositions(final int x, final int y, ArrayDeque<Position> points) {
        int xRight = x + 1, xLeft = x - 1, yUp = y + 1, yDown = y - 1;
        if (x + 1 < SIZE && cave.getCaveElement(xRight, y) != -1
                && !this.visibility.get(xRight).get(y)) {
            points.add(new Position(xRight, y));
            this.wave.get(xRight).set(y, this.wave.get(x).get(y) + 1);
        }

        if (x - 1 >= 0 && cave.getCaveElement(xLeft, y) != -1
                && !this.visibility.get(xLeft).get(y)) {
            points.add(new Position(xLeft, y));
            this.wave.get(xLeft).set(y, this.wave.get(x).get(y) + 1);
        }

        if (y + 1 < SIZE && cave.getCaveElement(x, yUp) != -1
                && !this.visibility.get(x).get(yUp)) {
            points.add(new Position(x, yUp));
            this.wave.get(x).set(yUp, this.wave.get(x).get(y) + 1);
        }

        if (y - 1 >= 0 && cave.getCaveElement(x, yDown) != -1
                && !this.visibility.get(x).get(yDown)) {
            points.add(new Position(x, yDown));
            this.wave.get(x).set(yDown, this.wave.get(x).get(y) + 1);
        }
    }

    private Position[] RestoreWayAndReturnNextMonsterStep(int xpos, int ypos) {
        Position[] oldAndNewPosition = new Position[2];
        Position current = new Position(xpos, ypos);
        oldAndNewPosition[0] = new Position(0, 0);
        oldAndNewPosition[1] = new Position(0, 1);

        while (true) {
            int x = current.getX(), y = current.getY();
            if (x - 1 >= 0 && wave.get(x).get(y) - 1 == wave.get(x - 1).get(y)) {
                current.setPosition(x - 1, y);
            } else if (x + 1 < SIZE && wave.get(x).get(y) - 1 == wave.get(x + 1).get(y)) {
                current.setPosition(x + 1, y);
            } else if (y + 1 < SIZE && wave.get(x).get(y) - 1 == wave.get(x).get(y + 1)) {
                current.setPosition(x, y + 1); // old position
            } else if (y - 1 >= 0 && wave.get(x).get(y) - 1 == wave.get(x).get(y - 1)) {
                current.setPosition(x, y - 1); // old position
            }
            if (wave.get((current.getX())).get(current.getY()) == 1)
                oldAndNewPosition[0].setPosition(current.getX(), current.getY());
            if (wave.get((current.getX())).get(current.getY()) == 0) {
                oldAndNewPosition[1].setPosition(current.getX(), current.getY());
                break;
            }
        }
        return oldAndNewPosition;
    }

    private void checkGameOver() {
        if ((Math.abs(purposePosition.getX() - currentPosition.getX()) == 0 && Math.abs(purposePosition.getY() - currentPosition.getY()) == 1) ||
                (Math.abs(purposePosition.getX() - currentPosition.getX()) == 1 && Math.abs(purposePosition.getY() - currentPosition.getY()) == 0)) {
            GameBoard board = new GameBoard(cave.getGameBoard());
            board.printGameOverMatrix();
            printGameOver();
            System.exit(0);
        }
    }
}
