package ru.matevosyan.models;

import ru.matevosyan.exceptions.FigureNotFoundException;
import ru.matevosyan.exceptions.ImpossibleMoveException;

/**
 * Created abstract for describe figure in Chess.
 * Created on 27.01.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public abstract class Figure {
    /**
     * figure position.
     */

    public final Cell position;

    /**
     * figure constructor that assign figure to cell.
     * @param figurePosition passing cell value to assign position.
     */

    public Figure(Cell figurePosition) {
        this.position = figurePosition;
    }

    /**
     * Method way find the figure movement and assign to Cell array which is returning in the end.
     * And we get all the way that bishop is getting.
     * @param dist the way that we want to get.
     * @return array of cell or array of movement.
     * @throws ImpossibleMoveException throw when move is imposable.
     */

    public abstract Cell[] way(Cell dist) throws ImpossibleMoveException;

    /**
     * Made Figure implements Cloneable and override clone method from Object class.
     * @param destination the way that we want to assign figure.
     * @return new figure instance.
     * @throws FigureNotFoundException that is men that clone can not find figure in destination position.
     */

    public abstract Figure clone(Cell destination) throws FigureNotFoundException;

}
