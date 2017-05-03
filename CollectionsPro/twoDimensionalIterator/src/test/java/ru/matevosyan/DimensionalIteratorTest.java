package ru.matevosyan;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


/**
 * DimensionIteratorTest class.
 * Created on 28.04.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class DimensionalIteratorTest {

    /**
     * whenPassTwoDimensionSquareArrayThanIterateAndReturnTheSameValue was created to test two methods.
     * in {@link DimensionalIteratorTest}. When passing square array, program iterate through all elements.
     * and return each of them, finally test check if our expected value is equal to actual value.
     */

    @Test
    public void whenPassTwoDimensionSquareArrayThanIterateAndReturnTheSameValue() {
        int[][] actualArray = new int[][]{{1, 2}, {3, 4}};
        DimensionalIterator dimensionalIterator = new DimensionalIterator(actualArray);

        ArrayList<Integer> actualValue = new ArrayList<>();

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);

        while (dimensionalIterator.hasNext()) {
            actualValue.add(dimensionalIterator.next());
        }

        assertThat(actualValue, is(expected));
    }

    /**
     * whenPassTwoDimensionRectangleArrayThanIterateAndReturnTheSameValue was created to test two methods.
     * in {@link DimensionalIteratorTest}. When passing rectangle array and array column is less than rows.
     * program iterate through all elements and return each of them, finally test check if our expected value.
     * is equal to actual value.
     */

    @Test
    public void whenPassTwoDimensionRectangleArrayThanIterateAndReturnTheSameValue() {
        int[][] actualArray = new int[][]{{1, 2}, {3, 4}, {5, 6}};
        DimensionalIterator dimensionalIterator = new DimensionalIterator(actualArray);

        ArrayList<Integer> actualValue = new ArrayList<>();

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(5);
        expected.add(6);

        while (dimensionalIterator.hasNext()) {
            actualValue.add(dimensionalIterator.next());
        }

        assertThat(actualValue, is(expected));
    }

    /**
     * whenPassTwoDimensionRectangleArrayAndColumnsBiggerThanRowsThanIterateAndReturnTheSameValue was created to test two methods.
     * in {@link DimensionalIteratorTest}. When passing rectangle array and array column is bigger than rows.
     * program iterate through all elements and return each of them, finally test check if our expected value.
     * is equal to actual value.
     */

    @Test
    public void whenPassTwoDimensionRectangleArrayAndColumnsBiggerThanRowsThanIterateAndReturnTheSameValue() {
        int[][] actualArray = new int[][]{{1, 2, 3}, {4, 5, 6}};
        DimensionalIterator dimensionalIterator = new DimensionalIterator(actualArray);

        ArrayList<Integer> actualValue = new ArrayList<>();

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(5);
        expected.add(6);

        while (dimensionalIterator.hasNext()) {
            actualValue.add(dimensionalIterator.next());
        }

        assertThat(actualValue, is(expected));
    }


}