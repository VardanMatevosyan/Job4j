package ru.matevosyan.models;

/**
 * Created Cell to describe Chess cell.
 * Created on 27.01.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class Cell {

    private int x;
    private int y;

    public Cell(int x,  int y) {
        this.x = x;
        this.y = y;
    }
    public Cell() {}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
