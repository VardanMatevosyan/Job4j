package ru.matevosyan.Figures;

import ru.matevosyan.exceptions.ImpossibleMoveException;
import ru.matevosyan.models.Cell;
import ru.matevosyan.models.Figure;

import java.util.Arrays;

/**
 * Created Bishop as one of figures.
 * Created on 29.01.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class Bishop extends Figure {

    /**
     * Bishop name.
     */

    private final String name = "Bishop";

    /**
     * bishopWay is array that hold bishop steps.
     */

    private Cell[] bishopWay = new Cell[7];

    /**
     * countWay is variable that count how many steps bishop run.
     */

    private int countWay = 0;

    /**
     * Bishop constructor invoke parent constructor which assign Cell position.
     * @param position Bishop position
     */

    public Bishop(Cell position) {
      super(position);
    }

    @Override
    public String toString() {
        return String.format("%s", Arrays.toString(this.bishopWay));
    }

    /**
     * Method way find the bishop movement and assign to Cell array which is returning in the end.
     * And we get all the way that bishop is getting.
     * @param dist the way that we want to get.
     * @return bishopWay that is actually array of cell or array of movement.
     * @throws ImpossibleMoveException throw when move is imposable.
     */

    @Override
    public Cell[] way(Cell dist) throws ImpossibleMoveException {

        Cell bishopStep = new Cell();
        int i = 0;

        if (Math.abs(dist.getX() - this.position.getX()) == Math.abs(dist.getY() - this.position.getY())) {

            /**
             * Get Cell distance X and Y getting from passing to method way.
             */

            int distX = dist.getX();
            int distY = dist.getY();

            /**
             * Get Cell current position X and Y getting from passing to method way.
             */

            int currentDistX = this.position.getX();
            int currentDistY = this.position.getY();

            /**
             * BishopStepsX = |distX-sourceX|-> it is figure steps.
             */

            int bishopStepsX = Math.abs(distX - currentDistX);

            /**
             * BishopStepsY = |distY-sourceY|-> it is figure steps.
             */

            int bishopStepsY = Math.abs(distY - currentDistY);

            /**
             * Example.
             * if dist = 0.7 from 6.1 then we go from 6.1  when distX < sourceX then increment distX while dist = source
             * and else distX > sourceX then decrement distX while dist = source and the same way we can use distY
             */

            if (bishopStepsX != 0 & bishopStepsY != 0) {
                do {
                    if (distX < currentDistX & distY < currentDistY) {
                        this.bishopWay[i++] = new Cell(currentDistX--, currentDistY--);
                        if (distX == currentDistX & distY == currentDistY) {
                            this.bishopWay[i++] = dist;
                        }
                    } else if (distX < currentDistX & distY > currentDistY) {
                        this.bishopWay[i++] = new Cell(currentDistX--, currentDistY++);
                        if (distX == currentDistX & distY == currentDistY) {
                            this.bishopWay[i++] = dist;
                        }
                    } else if (distX > currentDistX & distY > currentDistY) {
                        this.bishopWay[i++] = new Cell(currentDistX++, currentDistY++);
                        if (distX == currentDistX & distY == currentDistY) {
                            this.bishopWay[i++] = dist;
                        }
                    } else if (distX > currentDistX & distY < currentDistY) {
                        this.bishopWay[i++] = new Cell(currentDistX++, currentDistY--);
                        if (distX == currentDistX & distY == currentDistY) {
                            this.bishopWay[i++] = dist;
                        }
                    }
                    countWay++;
                } while (distX != currentDistX);
            } else {
                throw new ImpossibleMoveException("You can't move that way!!!");
            }

        } else {
            throw new ImpossibleMoveException("You can't move that way!!!");
        }

        countWay = countWay + 1;
        Cell[] finalBishopWay = new Cell[countWay];
        System.arraycopy(this.bishopWay, 0, finalBishopWay, 0, finalBishopWay.length);

        return finalBishopWay;
    }

    /**
     * Override clone method in Figure class which is actually abstract.
     * @param destination the way that we want to get.
     * @return new bishop instance.
     * @throws FigureNotFoundException that is men that clone can not find figure in destination position.
     */

    @Override
    public Figure clone(Cell destination) {
        return new Bishop(destination);
    }

}
