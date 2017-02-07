package ru.matevosyan.models;

import org.junit.Test;
import ru.matevosyan.Figures.Bishop;
import ru.matevosyan.Figures.Rook;
import ru.matevosyan.exceptions.FigureNotFoundException;
import ru.matevosyan.exceptions.ImpossibleMoveException;
import ru.matevosyan.exceptions.OccupiedWayException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created BoardTest to testing Board class.
 * Created on 29.01.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class BoardTest {
    @Test
    public void whenMoveToNotRightCoordinatesXAndYThanReturnFalse() throws Exception {
        boolean moveCheck = true;
        Cell sourcePosition = new Cell(1, 2);
        Cell distPosition = new Cell(8, -1);

        Figure bishop = new Bishop(sourcePosition);
        Figure rook = new Rook(sourcePosition);

        Figure[] figures = {bishop, rook};

        Board board = new Board(figures);

        try {
            board.move(sourcePosition, distPosition);
        } catch (ImpossibleMoveException ime) {
            moveCheck = false;
        }

        assertThat(moveCheck, is(false));
    }

    @Test
    public void whenMoveToRightCoordinatesXAndYThanReturnTrue() throws Exception {
        boolean moveCheck = false;
        Cell sourcePositionBishop = new Cell(1, 2);
        Cell distPositionBishop = new Cell(3, 2);

        Figure bishop = new Bishop(sourcePositionBishop);

        Figure[] figures = {bishop};

        Board board = new Board(figures);

        try {
            board.move(sourcePositionBishop, distPositionBishop);
            moveCheck = true;
        } catch (ImpossibleMoveException ime) {
            moveCheck = false;
        }

        assertThat(moveCheck, is(true));
    }

    @Test
    public void whenFigureIsFoundThanReturnTrue() throws Exception {
        boolean moveCheck = false;
        Cell sourcePositionBishop = new Cell(1, 2);
        Cell distPositionBishop = new Cell(3, 2);

        Figure bishop = new Bishop(sourcePositionBishop);

        Figure[] figures = {bishop};

        Board board = new Board(figures);

        try {
            board.move(sourcePositionBishop, distPositionBishop);
            moveCheck = true;
        } catch (FigureNotFoundException fnfe) {
            moveCheck = false;
        }

        assertThat(moveCheck, is(true));
    }

    @Test
    public void whenFigureIsNotFoundThanReturnFalse() throws Exception {
        boolean moveCheck = true;
        Cell sourcePositionBishop = new Cell(1, 2);
        Cell distPositionBishop = new Cell(3, 2);
        Cell checkFigureIsThere = new Cell(1, 4);

        Figure bishop = new Bishop(sourcePositionBishop);

        Figure[] figures = {bishop};

        Board board = new Board(figures);

        try {
            board.move(checkFigureIsThere, distPositionBishop);
        } catch (FigureNotFoundException fnfe) {
            moveCheck = false;
        }

        assertThat(moveCheck, is(false));
    }

    @Test
    public void whenFigureStepToOccupiedWayThanReturnFalse() throws Exception {
        boolean moveCheck = true;

        Cell sourcePositionAnotherBishop = new Cell(1, 3);
        Cell sourcePositionBishop = new Cell(0, 2);
        Cell distPositionBishop = new Cell(1, 3);

        Figure bishop = new Bishop(sourcePositionBishop);
        Figure anotherBishop = new Bishop(sourcePositionAnotherBishop);

        Figure[] figures = {bishop, anotherBishop};

        Board board = new Board(figures);

        try {
            board.move(sourcePositionBishop, distPositionBishop);
        } catch (OccupiedWayException owe) {
            moveCheck = false;
        }

        assertThat(moveCheck, is(false));
    }

    @Test
    public void whenFigureStepToNotOccupiedCellWayThanReturnTrue() throws Exception {
        boolean moveCheck = false;

        Cell sourcePositionAnotherBishop = new Cell(0, 5);
        Cell sourcePositionBishop = new Cell(0, 2);
        Cell distPositionBishop = new Cell(1, 3);

        Figure bishop = new Bishop(sourcePositionBishop);
        Figure anotherBishop = new Bishop(sourcePositionAnotherBishop);

        Figure[] figures = {bishop, anotherBishop};

        Board board = new Board(figures);

        try {
            board.move(sourcePositionBishop, distPositionBishop);
            moveCheck = true;
        } catch (OccupiedWayException owe) {
            moveCheck = false;
        }

        assertThat(moveCheck, is(false));
    }
}