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

        if (((dist.getX() & source.getX()) < 8 && (dist.getY() & source.getY()) < 8 ) &
                (((dist.getX() & source.getX()) >= 0 && (dist.getY() & source.getY()) >= 0 )))  {
            return true;
        } else {
            return false;
        }

    }
}
