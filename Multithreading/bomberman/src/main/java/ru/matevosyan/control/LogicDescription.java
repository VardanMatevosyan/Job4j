package ru.matevosyan.control;

import ru.matevosyan.constants.Directions;

/**
 * Created by Admin on 03.12.2017.
 */
public interface LogicDescription {
    /**
     * Opposite direction from Direction.
     * @param direction direction.
     * @return Direction value.
     */
    Directions changeToOppositeDirection(Directions direction);

    /**
     * Check border game.
     * @param x int.
     * @param y int.
     * @return boolean value.
     */
    boolean checkBorderGame(final int x, final int y);
}
