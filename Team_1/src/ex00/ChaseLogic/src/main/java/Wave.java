package ChaseLogic.src.main.java;

import java.util.ArrayDeque;
import java.util.ArrayList;

public final class Wave {
    private final Point currentPosition;
    private final Point purposePosition;
    private int nextPositionX;
    private int nextPositionY;

    private final ArrayList<ArrayList<Integer>> cave;
    private final int SIZE;
    private ArrayList<ArrayList<Integer>> wave;
    private ArrayList<ArrayList<Boolean>> visibility;





    public Wave(final int currentPositionX, final int currentPositionY,
                final int purposePositionX, final int purposePositionY,
                ArrayList<ArrayList<Integer>> cave) {
        this.currentPosition = new Point(currentPositionX, currentPositionY);
        this.purposePosition = new Point(purposePositionX, purposePositionY);
        this.nextPositionX = currentPosition.getX();
        this.nextPositionY = currentPosition.getY();

        this.SIZE = cave.size();

        this.cave = cave;
        createVisibility();
        createEmptyWave();
        createRealWave();
    }

    public int getNextPositionX() {
        return nextPositionX;
    }

    public int getNextPositionY() {
        return nextPositionY;
    }



    private class Point {
        public int x;
        public int y;

        protected Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        protected int getX() {
            return x;
        }

        protected int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }
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
    public boolean createRealWave() {
        Point workingPoint = new Point(currentPosition.getX(), currentPosition.getY());
        ArrayDeque<Point> points = new ArrayDeque<>();
        points.add(workingPoint);
        this.wave.get(currentPosition.getX()).set(currentPosition.getY(), 0);
        while (!points.isEmpty()) {
            workingPoint = points.poll();
            int x = workingPoint.getX(), y = workingPoint.getY();
            if (x == purposePosition.getX() && y == purposePosition.getY()) {
                RestoreWayAndReturnNextMonsterStep(purposePosition.getX(), purposePosition.getY());
            }
            if (this.visibility.get(x).get(y)) continue;
            getNextPositions(x, y, points);
            this.visibility.get(x).set(y, true);
        }
        return false;
    }

    private void getNextPositions(final int x, final int y, ArrayDeque<Point> points) {
        int xRight = x + 1, xLeft = x - 1, yUp = y + 1, yDown = y - 1;
        if (x + 1 < SIZE && cave.get(xRight).get(y) != -1
                && !this.visibility.get(xRight).get(y)) {
            points.add(new Point(xRight, y));
            this.wave.get(xRight).set(y, this.wave.get(x).get(y) + 1);
        }

        if (x - 1 >= 0 && cave.get(xLeft).get(y) != -1
                && !this.visibility.get(xLeft).get(y)) {
            points.add(new Point(xLeft, y));
            this.wave.get(xLeft).set(y, this.wave.get(x).get(y) + 1);
        }

        if (y + 1 < SIZE && cave.get(x).get(yUp) != -1
                && !this.visibility.get(x).get(yUp)) {
            points.add(new Point(x, yUp));
            this.wave.get(x).set(yUp, this.wave.get(x).get(y) + 1);
        }

        if (y - 1 >= 0 && cave.get(x).get(yDown) != -1
                && !this.visibility.get(x).get(yDown)) {
            points.add(new Point(x, yDown));
            this.wave.get(x).set(yDown, this.wave.get(x).get(y) + 1);
        }
    }

    private void RestoreWayAndReturnNextMonsterStep(int xpos, int ypos) {
        if (wave.get(xpos).get(ypos) != 1) {
            Point newPoint = new Point(xpos, ypos);
            while (true) {
                int x = newPoint.getX(), y = newPoint.getY();
                if (x - 1 >= 0 && wave.get(x).get(y) - 1 == wave.get(x - 1).get(y)) {
                    newPoint.setX(x - 1);
                } else if (x + 1 < SIZE && wave.get(x).get(y) - 1 == wave.get(x + 1).get(y)) {
                    newPoint.setX(x + 1);
                } else if (y + 1 < SIZE && wave.get(x).get(y) - 1 == wave.get(x).get(y + 1)) {
                    newPoint.setY(y + 1);
                } else if (y - 1 >= 0 && wave.get(x).get(y) - 1 == wave.get(x).get(y - 1)) {
                    newPoint.setY(y - 1);
                }
                if (wave.get(newPoint.getX()).get(newPoint.getY()) == 1) {
                    nextPositionX = newPoint.getX();
                    nextPositionY = newPoint.getY();
                    break;
                }
            }
        } else {
            this.nextPositionX = xpos;
            this.nextPositionY = ypos;
        }
    }
}
