package ru.matevosyan;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


/**
 * EvenNumberTest class.
 * Created on 28.04.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class EvenNumberTest {

    /**
     * whenPassTwoDimensionSquareArrayThanIterateAndReturnTheSameValue was created to test two methods.
     * in {@link EvenNumber}. When passing square array, program iterate through all elements.
     * and return each of them, finally test check if our expected value is equal to actual value.
     */

    @Test
    public void whenPassTwoDimensionSquareArrayThanIterateAndReturnTheSameValue() {
        int[] actualArray = new int[]{1, 2, 3, 4};
        EvenNumber evenNumberIterator = new EvenNumber(actualArray);

        ArrayList<Integer> actualValue = new ArrayList<>();

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(4);

        while (evenNumberIterator.hasNext()) {
            actualValue.add(evenNumberIterator.next());
        }

        assertThat(actualValue, is(expected));
    }

    /**
     * whenPassTwoDimensionSquareArrayThanCheckHasNextReturnValueIs was created to test hasNext methods.
     * in {@link EvenNumber}. When passing square array, program iterate through all elements.
     * and return each of them, finally test check if we has more element to get? then get it and check one more.
     * to be shore that is not there.
     */

    @Test
    public void whenPassTwoDimensionSquareArrayThanCheckHasNextReturnValueIs() {
        int[] actualArray = new int[]{1, 2, 3, 4};
        EvenNumber evenNumberIterator = new EvenNumber(actualArray);

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(2);
        arrayList.add(4);

        boolean actual = evenNumberIterator.hasNext();
        evenNumberIterator.next();
        evenNumberIterator.next();
        boolean checkIndexOutElement = evenNumberIterator.hasNext();

        assertThat(actual, is(true));

        assertThat(checkIndexOutElement, is(false));
    }


}