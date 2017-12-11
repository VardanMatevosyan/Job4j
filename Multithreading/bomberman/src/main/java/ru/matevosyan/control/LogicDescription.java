package ru.matevosyan.control;

import ru.matevosyan.constants.Directions;

/**
 * Created by Admin on 03.12.2017.
 */
public interface LogicDescription {
    Directions changeToOppositeDirection(Directions direction);
    boolean checkBorderGame(final int x, final int y);
//    void takeCell();
}
