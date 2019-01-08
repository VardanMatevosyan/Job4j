package ru.matevosyan;

import org.junit.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;


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
     * @param actualArray actual array of int.
     * @param expected array list of Integer.
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

    /**
     * whenPassArrayWithLastThreeCompositeNumberThanReturnAllSimpleNumber was created to test {@link NaturalNumber}.
     * When passing an array of four elements and the last three composites, program return one simple elements.
     */

    @Test
    public void whenPassArrayWithLastThreeCompositeNumberThanReturnAllSimpleNumber() {

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(5);

        whenSendArrayThanReturnOnlySimpleNumbers(new int[]{5, 8, 4, 1}, expected);
    }

    /**
     * whenPassArrayWithFirstThreeCompositeNumberThanReturnAllSimpleNumber was created to test {@link NaturalNumber}.
     * When passing an array of four elements and the first three are composites, program return one simple elements.
     */

    @Test
    public void whenPassArrayWithFirstThreeCompositeNumberThanReturnAllSimpleNumber() {

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(5);

        whenSendArrayThanReturnOnlySimpleNumbers(new int[]{1, 8, 4, 5}, expected);
    }

    /**
     * whenPassArrayThanReturnCheckSimpleNumber was created to test {@link NaturalNumber}.
     * When passing an array of four elements then element should be simple number when invoke next() method.
     */

    @Test
    public void whenPassArrayThanReturnCheckSimpleNumber() {

        ArrayList<Integer> expected = new ArrayList<>();
        ArrayList<Integer> actualValue = new ArrayList<>();
        NaturalNumber evenNumberIterator = new NaturalNumber(new int[]{1, 8, 4, 5});

        expected.add(5);

        actualValue.add(evenNumberIterator.next());

        assertThat(actualValue, is(expected));

    }

    /**
     * whenPassArrayThanReturnCheckException was created to test {@link NaturalNumber}.
     * When passing an array with composites element than check exception.
     */

    @Test
    public void whenPassArrayThanReturnCheckException() {

        Throwable expectedException = null;

        ArrayList<Integer> actualValue = new ArrayList<>();
        NaturalNumber evenNumberIterator = new NaturalNumber(new int[]{1, 8});


        try {
            actualValue.add(evenNumberIterator.next());
            actualValue.add(evenNumberIterator.next());
            actualValue.add(evenNumberIterator.next());
        } catch (Throwable nse) {
            expectedException = nse;
        }


        assertTrue(expectedException instanceof  NoSuchElementException);

    }

}