package ru.matevosyan.models;

import ru.matevosyan.exceptions.FigureNotFoundException;
import ru.matevosyan.exceptions.ImpossibleMoveException;
import ru.matevosyan.exceptions.OccupiedWayException;

/**
 * Created Board for describe Chess board.
 * Created on 27.01.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class Board {

    /**
     * figures is an array that hold figures.
     */

    protected Figure[] figures = new Figure[6];
    protected Cell[] figureSteps;

    /**
     * Board constructor.
     *
     * @param figures assign to current instance variable this.figures.
     */

    public Board(Figure[] figures) {
        this.figures = figures;
    }

    /**
     * Move method that check figure movement and return false if figure can't really move.
     * and if figure can really move than invoke clone method and move to destination cell.
     *
     * @param source is start figure position.
     * @param dist   is figure destination.
     * @return false if figure can't move or if can move to destination cell.
     * @throws ImpossibleMoveException if figure can't move.
     * @throws OccupiedWayException    when figure cell is occupied.
     * @throws FigureNotFoundException call when figure not found.
     */

    boolean move(Cell source, Cell dist) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        int i = 0;
        do {
        int sourceX = source.getX();
        int sourceY = source.getY();
        int distX = dist.getX();
        int distY = dist.getY();

        if (((distX | sourceX) < 8 & (distY | sourceY) < 8) & (((distX | sourceX) >= 0 & (distY | sourceY) >= 0))) {
            int figureX = 0;
            int figureY = 0;

            for (int j = 0; j < this.figures.length; j++) {
                if (sourceX == this.figures[j].getPosition().getX() && sourceY == this.figures[j].getPosition().getY()) {
                    figureX = this.figures[j].getPosition().getX();
                    figureY = this.figures[j].getPosition().getY();
                    i = j;
                }
            }

            if (figureX == sourceX || figureY == sourceY) {

                try {
                    Cell[] figureSteps = this.figures[i].way(dist);
                    Cell step = new Cell(distX, distY);
                    boolean isOccupied = false;

                    for (Figure figure : this.figures) {
                        int getDistPositionX = figure.getPosition().getX();
                        int getDistPositionY = figure.getPosition().getY();

                        if (step.getX() == getDistPositionX && step.getY() == getDistPositionY) {
                            isOccupied = true;
                        }
                    }

                    if (isOccupied) {
                        throw new OccupiedWayException("Occupied way");
                    } else {
                        this.figures[i] = this.figures[i].clone(dist);
                        break;
                    }
                } catch (ImpossibleMoveException ime) {
                    System.out.printf("%s%n", "Impossible movement");
                }
            } else {
                throw new FigureNotFoundException("Figure not found");
            }
        } else {
            throw new ImpossibleMoveException("Try again");
        }
        } while (i < this.figures.length);
        return false;
    }
}