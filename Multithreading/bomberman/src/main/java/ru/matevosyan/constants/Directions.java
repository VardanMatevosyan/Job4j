package ru.matevosyan.constants;

/**
 * Created by Admin on 03.12.2017.
 */
public enum Directions {
    LEFT,
    RIGHT,
    UP,
    DOWN,
    NONE;

    /**
     * Horizontal direction.
     * @return int value.
     */
    public int getHorizontalDirection() {
        return this == LEFT ? -1 : (this == RIGHT ? 1 : 0);
    }

    /**
     * Vertical direction.
     * @return int value.
     */
    public int getVerticalDirection() {
        return this == UP ? -1 : (this == DOWN ? 1 : 0);
    }
}
