package Game.src.main.java;

import Game.Exceptions.IllegalPointPositionException;

import java.util.Objects;

import static Game.src.main.java.ObjectsData.*;

public final class Position {
    private int x;
    private int y;


    public Position(int x, int y) {
        if ((x >= 0 && x < SIZE) && (y >= 0 && y < SIZE)) {
            this.x = x;
            this.y = y;
        } else throw new IllegalPointPositionException();

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private void setX(final int x) {
        if (x >= 0 && x < SIZE) {
            this.x = x;
        } else throw new IllegalPointPositionException();
    }

    private void setY(final int y) {
        if (y >= 0 && y < SIZE) {
            this.y = y;
        } else throw new IllegalPointPositionException();
    }

    public void setPosition(final int x, final int y) {
        setX(x);
        setY(y);
    }

    @Override
    public boolean equals(Object o) {
        if (this.getClass() != o.getClass()) return false;
        if (o == null) return false;
        Position position = (Position) o;
        if (this.hashCode() != o.hashCode()) return false;
        return this.x == position.x
                && this.y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(5 * SIZE + 7 * x + 9 * y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
