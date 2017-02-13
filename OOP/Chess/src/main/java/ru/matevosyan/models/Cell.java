package ru.matevosyan.models;

/**
 * Created Cell to describe Chess cell.
 * Created on 27.01.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class Cell {

    /**
     * x first cell coordinate which is row.
     */

    private int x;

    /**
     * y second cell coordinate which is column.
     */

    private int y;

    /**
     * Cell constructor which defined cell coordinates.
     * @param x x first cell coordinate which is row and passing to constructor.
     * @param y y second cell coordinate which is column and passing to constructor.
     */

    public Cell(int x,  int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor by default.
     */

    public Cell() {

    }

    /**
     * Getter for first coordinate.
     * @return x is first coordinate.
     */

    public int getX() {
        return x;
    }

    /**
     * Getter for second coordinate.
     * @return y is second coordinate.
     */

    public int getY() {
        return y;
    }

    /**
     * Setter setX assign to current variable this.x passing variable x.
     * @param x is passing variable x.
     */

    public void setX(int x) {
        this.x = x;
    }

    /**
     * Setter setY assign to current variable this.y passing variable y.
     * @param y is passing variable y.
     */

    public void setY(int y) {
        this.y = y;
    }
}
