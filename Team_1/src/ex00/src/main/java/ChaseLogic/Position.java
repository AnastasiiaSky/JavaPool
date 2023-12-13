package ChaseLogic;

import ChaseLogic.Exceptions.IllegalPointPositionException;

import java.util.Objects;

/* Класс описывает точку расположения того или иного объекта на карте игры */
public final class Position {
    private final int width;
    private final int length;

    private int x;
    private int y;


    public Position(final int x, final int y, final int width, final int length) {
        this.width = width;
        this.length = length;
        if(x >= 0 && x <= this.width) {
            this.x = x;
        } else throw new IllegalPointPositionException();
        if(y >= 0 && y <= this.length) {
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
        if(x >= 0 && x <= this.width) {
            this.x = x;
        } else throw new IllegalPointPositionException();
    }

    private void setY(final int y) {
        if(y >= 0 && y <= this.length) {
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
        if(this.hashCode() != o.hashCode()) return false;
        return this.width == position.width
                && this.length == position.length
                && this.x == position.x
                && this.y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash( width * 3 + 5 * length + 7 * x + 9 * y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
