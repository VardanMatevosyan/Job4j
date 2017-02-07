package ru.matevosyan.Figures;

import org.junit.Test;
import ru.matevosyan.exceptions.FigureNotFoundException;
import ru.matevosyan.exceptions.ImpossibleMoveException;
import ru.matevosyan.models.Cell;
import ru.matevosyan.models.Figure;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created BishopTest ato testing Bishop class.
 * Created on 29.01.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class BishopTest {
    @Test
    public void WhenWayIsRightAndStepToOneCellToTopRightSideThanCheckXAndYWay() throws Exception {
        Cell sourcePosition = new Cell(0, 2);
        Cell distStepPosition = new Cell(1, 3);
        Cell distPosition = new Cell(2, 4);

        Bishop bishop = new Bishop(sourcePosition);
        Cell[] inputWay = bishop.way(distPosition);

        Cell[] expectedWay = {sourcePosition, distStepPosition, distPosition};

        for (int i = 0; i < expectedWay.length & i < inputWay.length; i++) {
            assertThat(expectedWay[i].getX(), is(inputWay[i].getX()));
            assertThat(expectedWay[i].getY(), is(inputWay[i].getY()));
        }
    }

    @Test
    public void WhenWayIsRightAndStepGoTwoCellToLeftTopSideAndDestinationXBiggerYThanCheckXAndYWay() throws Exception {

        Cell sourcePosition = new Cell(0, 7);
        Cell distStepPosition = new Cell(1, 6);
        Cell distPosition = new Cell(2, 5);

        Bishop bishop = new Bishop(sourcePosition);
        Cell[] inputWay = bishop.way(distPosition);

        Cell[] expectedWay = {sourcePosition, distStepPosition, distPosition};

        for (int i = 0; i < expectedWay.length & i < inputWay.length; i++) {
            assertThat(expectedWay[i].getX(), is(inputWay[i].getX()));
            assertThat(expectedWay[i].getY(), is(inputWay[i].getY()));
        }
    }

    @Test
    public void WhenWayIsRightAndStepGoTwoCellToLeftTopSideAndDestinationYBiggerXThanCheckXAndYWay() throws Exception {

        Cell sourcePosition = new Cell(2, 5);
        Cell distStepPosition = new Cell(1, 6);
        Cell distPosition = new Cell(0, 7);

        Bishop bishop = new Bishop(sourcePosition);
        Cell[] inputWay = bishop.way(distPosition);

        Cell[] expectedWay = {sourcePosition, distStepPosition, distPosition};

        for (int i = 0; i < expectedWay.length & i < inputWay.length; i++) {
            assertThat(expectedWay[i].getX(), is(inputWay[i].getX()));
            assertThat(expectedWay[i].getY(), is(inputWay[i].getY()));
        }
    }

    @Test
    public void WhenWayIsRightAndStepToTwoCellToLeftBottomSideThanCheckXAndYWay() throws Exception {

        Cell sourcePosition = new Cell(3, 4);
        Cell distStepPosition = new Cell(2, 3);
        Cell distPosition = new Cell(1, 2);

        Bishop bishop = new Bishop(sourcePosition);
        Cell[] inputWay = bishop.way(distPosition);

        Cell[] expectedWay = {sourcePosition, distStepPosition, distPosition};

        for (int i = 0; i < expectedWay.length & i < inputWay.length; i++) {
            assertThat(expectedWay[i].getX(), is(inputWay[i].getX()));
            assertThat(expectedWay[i].getY(), is(inputWay[i].getY()));
        }


    }

    @Test
    public void WhenWayIsRightAndStepToFourCellToLeftBottomSideThanCheckXAndYWay() throws Exception {

        Cell sourcePosition = new Cell(5, 6);
        Cell distStepPosition1 = new Cell(4, 5);
        Cell distStepPosition2 = new Cell(3, 4);
        Cell distStepPosition3 = new Cell(2, 3);
        Cell distPosition = new Cell(1, 2);

        Bishop bishop = new Bishop(sourcePosition);
        Cell[] inputWay = bishop.way(distPosition);

        Cell[] expectedWay = {sourcePosition, distStepPosition1, distStepPosition2, distStepPosition3, distPosition};

        for (int i = 0; i < expectedWay.length & i < inputWay.length; i++) {
            assertThat(expectedWay[i].getX(), is(inputWay[i].getX()));
            assertThat(expectedWay[i].getY(), is(inputWay[i].getY()));
        }


    }

    @Test
    public void WhenWayIsNotValidateThanReturnFalse() throws Exception{
        boolean check = true;
        Cell sourcePosition = new Cell(3, 4);
        Cell distPosition = new Cell(1, 1);

        Bishop bishop = new Bishop(sourcePosition);

        try {
            bishop.way(distPosition);
        } catch (ImpossibleMoveException ime) {
            check = false;
        }

        assertThat(check, is(false));
    }

    @Test
    public void WhenWayIsValidateButFigureGoToSamePositionThanReturnFalse() throws Exception{
        boolean check = true;
        Cell sourcePosition = new Cell(3, 4);
        Cell distPosition = new Cell(3, 4);

        Bishop bishop = new Bishop(sourcePosition);

        try {
            bishop.way(distPosition);
        } catch (ImpossibleMoveException ime) {
            check = false;
        }

        assertThat(check, is(false));
    }

    @Test
    public void WhenFigureIsCloneThanCheckXAndY() throws FigureNotFoundException {
        Cell sourcePosition = new Cell(3, 4);
        Cell distPosition = new Cell(1, 2);

        Bishop bishop = new Bishop(sourcePosition);

        Bishop bishopClone = (Bishop) bishop.clone(distPosition);

        int figureX = bishopClone.position.getX();
        int figureY = bishopClone.position.getY();


        assertThat(figureX, is(1));
        assertThat(figureY, is(2));
    }
}