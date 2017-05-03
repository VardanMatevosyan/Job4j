package ru.matevosyan;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


/**
 * NaturalNumberTest class.
 * Created on 28.04.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class NaturalNumberTest {

    /**
     * whenPassTwoDimensionSquareArrayThanIterateAndReturnTheSameValue was created to test two methods.
     * in {@link NaturalNumber}. When passing square array, program iterate through all elements.
     * and return each of them, finally test check if our expected value is equal to actual value.
     */

    @Test
    public void whenPassTwoDimensionSquareArrayThanIterateAndReturnTheSameValue() {
        int[] actualArray = new int[]{1, 2, 4, 5};
        NaturalNumber evenNumberIterator = new NaturalNumber(actualArray);

        ArrayList<Integer> actualValue = new ArrayList<>();

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(5);

        while (evenNumberIterator.hasNext()) {
            actualValue.add(evenNumberIterator.next());
        }

        assertThat(actualValue, is(expected));
    }


}