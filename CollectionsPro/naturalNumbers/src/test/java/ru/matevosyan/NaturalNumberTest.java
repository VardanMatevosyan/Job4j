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
     * whenSendArrayThanReturnOnlySimpleNumbers was created to test two methods in this class.
     * 1. whenPassArrayWithLastSimpleNumberThanReturnAllSimpleNumber;
     * 2. whenPassArrayWithLastCompositeNumberThanReturnAllSimpleNumber.
     * Test will get actualArray numbers and compare with only simple, returning by program.
     */

    private void whenSendArrayThanReturnOnlySimpleNumbers(int[] actualArray, ArrayList<Integer> expected) {

        NaturalNumber evenNumberIterator = new NaturalNumber(actualArray);

        ArrayList<Integer> actualValue = new ArrayList<>();

        while (evenNumberIterator.hasNext()) {
            actualValue.add(evenNumberIterator.next());
        }

        assertThat(actualValue, is(expected));
    }

    /**
     * whenPassArrayWithLastSimpleNumberThanReturnAllSimpleNumber was created to test {@link NaturalNumber}.
     * When passing an array of four elements and the last one is simple, program return two simple elements.
     */

    @Test
    public void whenPassArrayWithLastSimpleNumberThanReturnAllSimpleNumber() {

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(5);

        whenSendArrayThanReturnOnlySimpleNumbers(new int[]{1, 2, 4, 5}, expected);
    }

    /**
     * whenPassArrayWithLastCompositeNumberThanReturnAllSimpleNumber was created to test {@link NaturalNumber}.
     * When passing an array of four elements and the last one is composite, program return one simple elements.
     */

    @Test
    public void whenPassArrayWithLastCompositeNumberThanReturnAllSimpleNumber() {

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(2);

        whenSendArrayThanReturnOnlySimpleNumbers(new int[]{1, 2, 4, 15}, expected);
    }


}