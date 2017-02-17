package ru.matevosyan.models;

import org.hamcrest.EasyMock2Matchers;
import org.junit.Test;
import ru.matevosyan.Figures.Bishop;
import ru.matevosyan.Figures.Rook;
import ru.matevosyan.exceptions.FigureNotFoundException;
import ru.matevosyan.exceptions.ImpossibleMoveException;
import ru.matevosyan.exceptions.OccupiedWayException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created BoardTest to testing Board class.
 * Created on 29.01.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class BoardTest {

    /**
     * Method whenFigureMoveThanCheckFigureCoordinates.
     * Testing figure movement, when move to destination coordinates, than check it.
     * @throws Exception when catch Exception.
     */

    @Test
    public void whenFigureMoveThanCheckFigureCoordinates() throws Exception{
        Cell cellCurrentBishop = new Cell(3, 4);
        Cell destinationCellBishop = new Cell(2, 3);

        Cell cellCurrentRook = new Cell(1, 1);
        Cell destinationCellRook = new Cell(3, 1);

        Cell cellCurrentBishop2 = new Cell(4, 5);
        Cell destinationCellBishop2 = new Cell(6, 7);

        Figure WhiteBishop = new Bishop(cellCurrentBishop);
        Figure BlackBishop = new Bishop(cellCurrentBishop2);
        BlackBishop.changeColorToBlack();
        Figure WhiteRook = new Rook(cellCurrentRook);

        Figure[] figures = {WhiteBishop, WhiteRook, BlackBishop};
        Board board = new Board(figures);

        assertThat(board.figures[0].getPosition().getX(), is(3));
        assertThat(board.figures[0].getPosition().getY(), is(4));
        assertThat(board.figures[0].getPosition(), is(cellCurrentBishop));

        assertThat(board.figures[1].getPosition().getX(), is(1));
        assertThat(board.figures[1].getPosition().getY(), is(1));
        assertThat(board.figures[1].getPosition(), is(cellCurrentRook));

        assertThat(board.figures[2].getPosition().getX(), is(4));
        assertThat(board.figures[2].getPosition().getY(), is(5));
        assertThat(board.figures[2].getPosition(), is(cellCurrentBishop2));

        try {

            board.move(cellCurrentBishop, destinationCellBishop);
            board.move(cellCurrentBishop2, destinationCellBishop2);
            board.move(cellCurrentRook, destinationCellRook);

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertThat(board.figures[0].getPosition().getX(), is(2));
        assertThat(board.figures[0].getPosition().getY(), is(3));
        assertThat(board.figures[0].getPosition(), is(destinationCellBishop));

        assertThat(board.figures[1].getPosition().getX(), is(3));
        assertThat(board.figures[1].getPosition().getY(), is(1));
        assertThat(board.figures[1].getPosition(), is(destinationCellRook));

        assertThat(board.figures[2].getPosition().getX(), is(6));
        assertThat(board.figures[2].getPosition().getY(), is(7));
        assertThat(board.figures[2].getPosition(), is(destinationCellBishop2));
    }

    /**
     * Method whenMoveToNotRightCoordinatesXAndYThanReturnFalse.
     * Testing rook movement, when move to not right coordinates.
     * @throws Exception when catch Exception.
     */

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

    /**
     * Method whenMoveToRightCoordinatesXAndYThanReturnTrue.
     * Testing rook movement, when move to right coordinates.
     * @throws Exception when catch Exception.
     */

    @Test
    public void whenMoveToRightCoordinatesXAndYThanReturnTrue() throws Exception {
        boolean moveCheck = false;
        Cell sourcePositionBishop = new Cell(1, 2);
        Cell distPositionBishop = new Cell(3, 4);

        Figure bishop = new Bishop(sourcePositionBishop);

        Figure[] figures = {bishop};

        Board board = new Board(figures);

        try {
            board.move(sourcePositionBishop, distPositionBishop);
            moveCheck = true;
        } catch (ImpossibleMoveException ime) {
            ime.printStackTrace();
            moveCheck = false;
        }

        assertThat(moveCheck, is(true));
    }

    /**
     * Method whenFigureIsFoundThanReturnTrue.
     * Testing figure existing.
     * @throws Exception when catch Exception.
     */

    @Test
    public void whenFigureIsFoundThanReturnTrue() throws Exception {
        boolean moveCheck = false;
        Cell sourcePositionBishop = new Cell(1, 2);
        Cell distPositionBishop = new Cell(2, 3);

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

    /**
     * Method whenFigureIsNotFoundThanReturnFalse.
     * Testing figure existing.
     * @throws Exception when catch Exception.
     */

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

    /**
     * Method whenFigureStepToOccupiedWayThanReturnFalse.
     * Testing that figure move to occupied cell.
     * @throws Exception when catch Exception.
     */

    @Test
    public void whenFigureStepToOccupiedWayThanReturnFalse() throws Exception {
        boolean moveCheck = true;

        Cell sourcePositionAnotherBishop = new Cell(2, 4);
        Cell sourcePositionBishop = new Cell(0, 2);
        Cell distPositionBishop = new Cell(1, 3);

        Figure bishop = new Bishop(sourcePositionBishop);
        Figure anotherBishop = new Bishop(sourcePositionAnotherBishop);

        Figure[] figures = {bishop, anotherBishop};

        Board board = new Board(figures);

        try {
            board.move(sourcePositionBishop, distPositionBishop);
            board.move(sourcePositionAnotherBishop, distPositionBishop);
        } catch (OccupiedWayException owe) {
            moveCheck = false;
        }

        assertThat(moveCheck, is(false));
    }

    /**
     * Method whenFigureStepToNotOccupiedCellWayThanReturnTrue.
     * Testing that figure move to not occupied cell.
     * @throws Exception when catch Exception.
     */

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

        assertThat(moveCheck, is(true));
    }
}