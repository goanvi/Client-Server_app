package model;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private final Integer x; //Максимальное значение поля: 811, Поле не может быть null
    private final int y;

    public Coordinates(Integer x, int y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "x=" + x +
                "y=" + y;
    }
}
