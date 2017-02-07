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
    private Figure[] figures = new Figure[6];

    public Board(Figure[] figures) {
        this.figures = figures;
    }

    boolean move(Cell source, Cell dist) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {

        int sourceX = source.getX();
        int sourceY = source.getY();
        int distX = dist.getX();
        int distY = dist.getY();

        if (((distX | sourceX) < 8 & (distY | sourceY) < 8 ) & (((distX | sourceX) >= 0 & (distY | sourceY) >= 0 ))) {
            int figureX = 0;
            int figureY = 0;

            int i = 0;
            do {
                figureX = this.figures[i].position.getX();
                figureY = this.figures[i].position.getY();
                if (figureX == sourceX && figureY == sourceY) {
                    int j = 0;
                    do {
                        figureX = this.figures[i].position.getX();
                        figureY = this.figures[i].position.getY();
                        if (figureX == sourceX || figureY == sourceY) {
                            try {
                                Cell[] figureSteps = this.figures[i].way(dist);

                                for (Cell steps : figureSteps) {
                                    int stepX = steps.getX();
                                    int stepY = steps.getY();

                                    if (stepX == figureX && stepY == figureY) {
                                        throw new OccupiedWayException("Occupied way");
                                    } else {
                                        this.figures[i].clone(dist);
                                    }
                                }

                            } catch (ImpossibleMoveException ime) {
                                System.out.printf("%s%n", "Impossible movement");
                            }
                        }
                        j++;
                    } while (j < this.figures.length);

                } else {
                    throw new FigureNotFoundException("Figure not found");
                }
                i++;
            } while (i < this.figures.length);

        } else {
            throw new ImpossibleMoveException("Try again");
        }

        return false;
    }
}

