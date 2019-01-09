package ru.matevosyan.model;

import ru.matevosyan.constants.Directions;

/**
 * CellOfGameBoard class describe cell of board on which bomberman can stand on or move to.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 24.11.2017
 */

public class CellOfGameBoard {

    /**
     * x first cell coordinate which is row.
     */

    private int x;

    /**
     * y second cell coordinate which is column.
     */

    private int y;

    /**
     * person direction.
     */

    private Directions direction;

    /**
     * Cell constructor which defined cell coordinates.
     * @param x x first cell coordinate which is row and passing to constructor.
     * @param y y second cell coordinate which is column and passing to constructor.
     * @param direction direction to walk.
     */

    public CellOfGameBoard(final int x, final int y, Directions direction) {

        this.x = x;
        this.y = y;
        this.direction = direction;
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
     * Setter for x.
     * @param x value x.
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * Setter for y.
     * @param y value y.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Getter for direction.
     * @return Direction.
     */
    public Directions getDirection() {
        return direction;
    }

    /**
     * Setter for direction.
     * @param direction value.
     */
    public void setDirection(final Directions direction) {
        this.direction = direction;
    }
}
