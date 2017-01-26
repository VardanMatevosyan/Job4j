package ru.matevosyan;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created testing BiggerRepeatValue fo finding bigger repeating number in array.
 * Created on 25.01.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class BiggerRepeatValueTest {

    /**
     * testing BiggerRepeatValue class.
     */

    @Test
    public void whenPassArrayThenGetBiggerRepeatNumberValue() {
        final int[] arrayNumber = {1, 2, 2, 3, 4, 4, 4, 4, 4, 5, 6, 7, 7, 7};
        final int expectedValue = 4;

        BiggerRepeatValue biggerRepeatValue = new BiggerRepeatValue();

        int actualValue = biggerRepeatValue.findRepeatValues(arrayNumber);

        assertThat(expectedValue, is(actualValue));
    }
}
