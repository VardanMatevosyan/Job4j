package ru.matevosyan.Figures;

import org.junit.Test;
import ru.matevosyan.models.Cell;

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
    public void WhenWayIsWrightThanGetThisWay() throws Exception {
        Cell sourcePosition = new Cell();
        sourcePosition.setX(0);
        sourcePosition.setY(2);

        Cell distPosition = new Cell();
        distPosition.setX(1);
        distPosition.setY(3);

        Bishop bishop = new Bishop(sourcePosition);
        Cell[] inputWay = bishop.way(distPosition);

        Cell[] expectedWay = {sourcePosition, distPosition, null, null, null, null, null};

        assertThat(expectedWay[0].getX(), is(inputWay[0].getX()));
        assertThat(expectedWay[0].getY(), is(inputWay[0].getY()));
        assertThat(expectedWay[1].getX(), is(inputWay[1].getX()));
        assertThat(expectedWay[1].getY(), is(inputWay[1].getY()));
        assertThat(expectedWay, is(inputWay));
    }

}