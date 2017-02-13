package ru.matevosyan.Figures;

import ru.matevosyan.exceptions.FigureNotFoundException;
import ru.matevosyan.exceptions.ImpossibleMoveException;
import ru.matevosyan.models.Cell;
import ru.matevosyan.models.Figure;

/**
 * Created Rook as one of figures.
 * Created on 29.01.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class Rook extends Figure {

    /**
     * Rook name.
     */

    private final String name = "Rook";

    /**
     * Rook constructor invoke parent constructor which assign Cell position.
     * @param position Rook position.
     */

    public Rook(Cell position) {
        super(position);
    }

    /**
     * Method way find the rook movement and assign to Cell array which is returning in the end.
     * And we get all the way that bishop is getting.
     * @param dist the way that we want to get.
     * @return rookWay that is actually array of cell or array of movement.
     * @throws ImpossibleMoveException throw when move is imposable.
     */

    @Override
    public Cell[] way(Cell dist) throws ImpossibleMoveException {

        Cell[] rookWay = new Cell[7];
        int i = 0;
        int countWayRook = 0;

        /**
         * Get Cell distance X and Y getting from passing to method way.
         */

        int distX = dist.getX();
        int distY = dist.getY();

        /**
         * Get Cell current position X and Y getting from passing to method way.
         */

        int currentDistX = this.getPosition().getX();
        int currentDistY = this.getPosition().getY();

        if (distY > currentDistY & distX == currentDistX) {
            do {
                if (i == 0) {
                    rookWay[i++] = this.getPosition();
                } else {
                    rookWay[i++] = new Cell(currentDistX, ++currentDistY);
                }
                countWayRook++;
            } while (currentDistY != distY);
        } else if (distY < currentDistY & distX == currentDistX) {
            do {
                if (i == 0) {
                    rookWay[i++] = this.getPosition();
                } else {
                    rookWay[i++] = new Cell(currentDistX, --currentDistY);
                }
                countWayRook++;
            } while (currentDistY != distY);
        } else if (distX > currentDistX & distY == currentDistY) {
            do {
                if (i == 0) {
                    rookWay[i++] = this.getPosition();
                } else {
                    rookWay[i++] = new Cell(++currentDistX, currentDistY);
                }
                countWayRook++;
            } while (currentDistX != distX);
        } else if (distX < currentDistX & distY == currentDistY) {
            do {
                if (i == 0) {
                    rookWay[i++] = this.getPosition();
                } else {
                    rookWay[i++] = new Cell(--currentDistX, currentDistY);
                }
                countWayRook++;
            } while (currentDistX != distX);
        }

        Cell[] finalRookWay = new Cell[countWayRook];
        System.arraycopy(rookWay, 0, finalRookWay, 0, finalRookWay.length);

        return finalRookWay;
    }

    /**
     * Override clone method in Figure class which is actually abstract.
     * @param destination the way that we want to get.
     * @return new rook instance.
     * @throws FigureNotFoundException that is men that clone can not find figure in destination position.
     */

    @Override
    public Figure clone(Cell destination) throws FigureNotFoundException {
        return new Rook(destination);
    }


}
