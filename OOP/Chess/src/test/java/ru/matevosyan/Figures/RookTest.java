package ru.matevosyan.Figures;

import org.junit.Test;
import ru.matevosyan.exceptions.FigureNotFoundException;
import ru.matevosyan.exceptions.ImpossibleMoveException;
import ru.matevosyan.models.Cell;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * Created BoardTest to testing Board class.
 * Created on 07.02.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class RookTest {

    /**
     * Method whenRookDistYBiggerCurrentDistYAndXIsSameThanCheckCoordinates.
     * Testing rook way, when destination coordinate y bigger than current destination coordinate y.
     * and x is tha same.
     * @throws ImpossibleMoveException when catch ImpossibleMoveException.
     */

    @Test
    public void whenRookDistYBiggerCurrentDistYAndXIsSameThanCheckCoordinates() throws ImpossibleMoveException {
        Cell source = new Cell(4, 1);
        Cell destinationFirstSteps = new Cell(4, 2);
        Cell destinationSecondSteps = new Cell(4, 3);
        Cell destination = new Cell(4, 4);

        Rook rook = new Rook(source);

        Cell[] inputRookWay = rook.way(destination);
        Cell[] expectedRookWay = {source, destinationFirstSteps, destinationSecondSteps, destination};

        for (int i = 0; i < inputRookWay.length; i++) {
            assertThat(inputRookWay[i].getX(), is(expectedRookWay[i].getX()));
            assertThat(inputRookWay[i].getY(), is(expectedRookWay[i].getY()));
        }
    }

    /**
     * Method whenRookDistYLessCurrentDistYAndXIsSameThanCheckCoordinates.
     * Testing rook way, when destination coordinate y less than current destination coordinate y.
     * and x is the same.
     * @throws ImpossibleMoveException when catch ImpossibleMoveException.
     */

    @Test
    public void whenRookDistYLessCurrentDistYAndXIsSameThanCheckCoordinates() throws ImpossibleMoveException {
        Cell source = new Cell(4, 5);
        Cell destinationFirstSteps = new Cell(4, 4);
        Cell destinationSecondSteps = new Cell(4, 3);
        Cell destinationThirdSteps = new Cell(4, 2);
        Cell destination = new Cell(4, 1);

        Rook rook = new Rook(source);

        Cell[] inputRookWay = rook.way(destination);
        Cell[] expectedRookWay = {source, destinationFirstSteps, destinationSecondSteps, destinationThirdSteps, destination};

        for (int i = 0; i < inputRookWay.length; i++) {
            assertThat(inputRookWay[i].getX(), is(expectedRookWay[i].getX()));
            assertThat(inputRookWay[i].getY(), is(expectedRookWay[i].getY()));
        }
    }

    /**
     * Method whenRookDistXBiggerCurrentDistXAndYIsSameThanCheckCoordinates.
     * Testing rook way, when destination coordinate y less than current destination coordinate y.
     * and y is tha same.
     * @throws ImpossibleMoveException when catch ImpossibleMoveException.
     */

    @Test
    public void whenRookDistXBiggerCurrentDistXAndYIsSameThanCheckCoordinates() throws ImpossibleMoveException {
        Cell source = new Cell(1, 5);
        Cell destinationFirstSteps = new Cell(2, 5);
        Cell destinationSecondSteps = new Cell(3, 5);
        Cell destinationThirdSteps = new Cell(4, 5);
        Cell destination = new Cell(5, 5);

        Rook rook = new Rook(source);

        Cell[] inputRookWay = rook.way(destination);
        Cell[] expectedRookWay = {source, destinationFirstSteps, destinationSecondSteps, destinationThirdSteps, destination};

        for (int i = 0; i < inputRookWay.length; i++) {
            assertThat(inputRookWay[i].getX(), is(expectedRookWay[i].getX()));
            assertThat(inputRookWay[i].getY(), is(expectedRookWay[i].getY()));
        }
    }

    /**
     * Method whenRookDistXLessCurrentDistXAndYIsSameThanCheckCoordinates.
     * Testing rook way, when destination coordinate x less than current destination coordinate x.
     * and y is tha same.
     * @throws ImpossibleMoveException when catch ImpossibleMoveException.
     */

    @Test
    public void whenRookDistXLessCurrentDistXAndYIsSameThanCheckCoordinates() throws ImpossibleMoveException {
        Cell source = new Cell(5, 5);
        Cell destinationFirstSteps = new Cell(4, 5);
        Cell destinationSecondSteps = new Cell(3, 5);
        Cell destinationThirdSteps = new Cell(2, 5);
        Cell destination = new Cell(1, 5);

        Rook rook = new Rook(source);

        Cell[] inputRookWay = rook.way(destination);
        Cell[] expectedRookWay = {source, destinationFirstSteps, destinationSecondSteps, destinationThirdSteps, destination};

        for (int i = 0; i < inputRookWay.length; i++) {
            assertThat(inputRookWay[i].getX(), is(expectedRookWay[i].getX()));
            assertThat(inputRookWay[i].getY(), is(expectedRookWay[i].getY()));
        }
    }

    /**
     * Method WhenFigureIsCloneThanCheckXAndY.
     * Testing clone method, that move rook to destination.
     * and y is tha same.
     * @throws FigureNotFoundException when catch FigureNotFoundException.
     */

    @Test
    public void whenFigureIsCloneThanCheckXAndY() throws FigureNotFoundException {
        Cell sourcePosition = new Cell(3, 4);
        Cell distPosition = new Cell(1, 4);

        Rook rook = new Rook(sourcePosition);

        Rook rookClone = (Rook) rook.clone(distPosition);

        int figureX = rookClone.getPosition().getX();
        int figureY = rookClone.getPosition().getY();


        assertThat(figureX, is(1));
        assertThat(figureY, is(4));
    }
}