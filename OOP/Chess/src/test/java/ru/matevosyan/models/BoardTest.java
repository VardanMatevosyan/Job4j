package ru.matevosyan.models;

import org.junit.Test;
import ru.matevosyan.Figures.Bishop;
import ru.matevosyan.Figures.Rook;
import ru.matevosyan.exceptions.ImpossibleMoveException;

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
            System.out.println(ime.toString());
        }

        assertThat(moveCheck, is(true));
    }
}