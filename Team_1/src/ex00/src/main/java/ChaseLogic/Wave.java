package ChaseLogic;

import java.util.*;

import static ChaseLogic.ObjectsData.*;

public final class Wave {
    private final Position currentPosition;
    private final Position purposePosition;

    private Cave cave;
    private ArrayList<ArrayList<Integer>> wave;
    private ArrayList<ArrayList<Boolean>> visibility;
    private Position nextStep;

    public Wave(Position currentPosition, Position purposePosition, Cave cave) {
        this.currentPosition = currentPosition;
        this.purposePosition = purposePosition;
        this.cave = cave;
        createVisibility();
        createEmptyWave();
        this.nextStep = createRealWave();
    }

    public Position getNextStep() {
        return nextStep;
    }

    private void createEmptyWave() {
        ArrayList<ArrayList<Integer>> emptyWave = new ArrayList<>(SIZE);
        for (int i = 0; i < SIZE; ++i) {
            emptyWave.add(i, new ArrayList<Integer>(SIZE));
            for (int j = 0; j < SIZE; ++j)
                emptyWave.get(i).add(j, -1);
        }
        this.wave = emptyWave;
    }

    private void createVisibility() {
        ArrayList<ArrayList<Boolean>> data = new ArrayList<>(SIZE);
        for (int i = 0; i < SIZE; ++i) {
            data.add(i, new ArrayList<Boolean>(SIZE));
            for (int j = 0; j < SIZE; ++j)
                data.get(i).add(j, false);
        }
        this.visibility = data;
    }

    private Position createRealWave() {
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
            if (this.visibility.get(x).get(y) == true) continue;
            getNextPositions(x, y, points);
            this.visibility.get(x).set(y, true);
        }
        return null;
    }

    private void getNextPositions(final int x, final int y, ArrayDeque<Position> points) {
        ArrayList<Position> nextPoints = new ArrayList<>();
        if (x + 1 < SIZE && cave.getCaveElement(x + 1, y) != -1
                && this.visibility.get(x + 1).get(y) == false) {
            points.add(new Position(x + 1, y));
            this.wave.get(x + 1).set(y, this.wave.get(x).get(y) + 1);
        }

        if (x - 1 >= 0 && cave.getCaveElement(x - 1, y) != -1
                && this.visibility.get(x - 1).get(y) == false) {
            points.add(new Position(x - 1, y));
            this.wave.get(x - 1).set(y, this.wave.get(x).get(y) + 1);
        }

        if (y + 1 < SIZE && cave.getCaveElement(x, y + 1) != -1
                && this.visibility.get(x).get(y + 1) == false) {
            points.add(new Position(x, y + 1));
            this.wave.get(x).set(y + 1, this.wave.get(x).get(y) + 1);
        }

        if (y - 1 >= 0 && cave.getCaveElement(x, y - 1) != -1
                && this.visibility.get(x).get(y - 1) == false) {
            points.add(new Position(x, y - 1));
            this.wave.get(x).set(y - 1, this.wave.get(x).get(y) + 1);
        }
    }

    private Position RestoreWayAndReturnNextMonsterStep(int xpos, int ypos) {
        Position current = new Position(xpos, ypos);
        while (wave.get((current.getX())).get(current.getY()) != 1) {
            int x = current.getX(), y = current.getY();
            if (wave.get(x).get(y) - 1 == wave.get(x - 1).get(y) && x - 1 >= 0) {
                current.setPosition(x - 1, y);
            } else if (wave.get(x).get(y) - 1 == wave.get(x + 1).get(y) && x + 1 < SIZE) {
                current.setPosition(x + 1, y);
            } else if (wave.get(x).get(y) - 1 == wave.get(x).get(y + 1) && y + 1 < SIZE) {
                current.setPosition(x, y + 1);
            } else if (wave.get(x).get(y) - 1 == wave.get(x).get(y - 1) && y - 1 >= 0) {
                current.setPosition(x, y - 1);
            }
        }
        return current;
    }
}
