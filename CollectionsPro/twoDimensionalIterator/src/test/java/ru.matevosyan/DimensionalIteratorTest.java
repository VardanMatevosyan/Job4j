package ru.matevosyan;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


/**
 * Created by Пользователь on 02.05.2017.
 */
public class DimensionalIteratorTest {
    @Test
    public void whenPassTwoDimensionArrayThanIterateAndReturnTheSameValue() {
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
}