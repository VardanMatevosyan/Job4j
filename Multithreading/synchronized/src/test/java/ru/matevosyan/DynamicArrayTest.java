package ru.matevosyan;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

/**
 * DynamicArrayTest class for testing DynamicArray class.
 * Created on 20.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class DynamicArrayTest {

    /**
     * Create whenAddMoreThatDefaultElementThatCheckIt() method to add more element than default size.
     * And check if size of an array is become bigger or not.
     */

    @Test
    public void whenAddMoreThatDefaultElementThatCheckIt() {
        DynamicArray<Integer> integerDynamicArray = new DynamicArray<>();

        integerDynamicArray.add(12);
        integerDynamicArray.add(42);
        integerDynamicArray.add(52);
        integerDynamicArray.add(62);
        integerDynamicArray.add(1);
        integerDynamicArray.add(4);
        integerDynamicArray.add(6);
        integerDynamicArray.add(7);
        integerDynamicArray.add(92);
        integerDynamicArray.add(11);
        integerDynamicArray.add(111);

        assertThat(integerDynamicArray.get(10), is(111));

    }

    /**
     * Create whenGetValueFromArrayThanCheckTheGetResults() invoke {@link DynamicArray#get(int)} to check.
     * the result.
     */

    @Test
    public void whenGetValueFromArrayThanCheckTheGetResults() {
        DynamicArray<Integer> integerDynamicArray = new DynamicArray<>();

        integerDynamicArray.add(12);
        integerDynamicArray.add(2);
        integerDynamicArray.add(3);

        int value = integerDynamicArray.get(1);
        assertThat(value, is(2));
    }

    /**
     * Create whenCreateArrayWithYourNotCorrectLengthButAddMoreThanLengthThanCheckTheGetResults() to check an exception.
     * when an array size is illegal, for example is negative.
     */

    @Test
    public void whenCreateArrayWithYourNotCorrectLengthButAddMoreThanLengthThanCheckTheGetResults() {

        Throwable e = null;

        try {
            DynamicArray<Integer> integerDynamicArray = new DynamicArray<>(-1);
        } catch (Throwable ex) {
            e = ex;
        }

        assertTrue(e instanceof IllegalArgumentException);
    }

    /**
     * Create  whenCreateArrayWithYourLengthButAddMoreThanLengthThanCheckTheGetResults() to check exception.
     * ArrayIndexOutOfBoundsException when use fixed length of an array.
     */

    @Test
    public void whenCreateArrayWithYourLengthButAddMoreThanLengthThanCheckTheGetResults() {
        DynamicArray<Integer> integerDynamicArray = new DynamicArray<>(1);
        Throwable e = null;

        try {
            integerDynamicArray.add(1);
            integerDynamicArray.add(2);
            integerDynamicArray.add(8);
            integerDynamicArray.add(3);
            integerDynamicArray.add(3);
        } catch (Throwable ex) {
            e = ex;
        }

        assertTrue(e instanceof ArrayIndexOutOfBoundsException);
    }

    /**
     * Create whenGetIteratorArrayThanCheckTheGetResults() to check next method in {@link DynamicArray#iterator()}.
     */

    @Test
    public void whenGetIteratorArrayThanCheckTheGetResults() {
        DynamicArray<Integer> integerDynamicArray = new DynamicArray<>();

        Iterator<Integer> itr = integerDynamicArray.iterator();

        integerDynamicArray.add(12);
        integerDynamicArray.add(2);
        integerDynamicArray.add(3);

        int value = 0;
        if (itr.hasNext()) {
            value = itr.next();
        }


        assertThat(value, is(12));
    }

    /**
     * Create whenInvokeHasNextThanCheckTheGetResultsIsGoingToBeTrue().
     * To check hasNext() in {@link DynamicArray#iterator()}.
     */

    @Test
    public void whenInvokeHasNextThanCheckTheGetResultsIsGoingToBeTrue() {
        DynamicArray<Integer> integerDynamicArray = new DynamicArray<>();

        Iterator<Integer> itr = integerDynamicArray.iterator();

        integerDynamicArray.add(12);
        integerDynamicArray.add(2);

        itr.hasNext();
        boolean has = itr.hasNext();

        assertThat(has, is(true));
    }

    /**
     * Create whenInvokeHasNextAndNextThanCheckTheGetResultsIsGoingToBeFalse() to check hasNext().
     * when in an array does not have any element and expected return false.
     */

    @Test
    public void whenInvokeHasNextAndNextThanCheckTheGetResultsIsGoingToBeFalse() {
        DynamicArray<Integer> integerDynamicArray = new DynamicArray<>();

        Iterator<Integer> itr = integerDynamicArray.iterator();

        integerDynamicArray.add(12);
        integerDynamicArray.add(2);

        itr.hasNext();
        int a = itr.next();
        int c = itr.next();
        boolean has = itr.hasNext();

        assertThat(has, is(false));
        assertThat(a, is(12));
        assertThat(c, is(2));

    }

    /**
     * Create whenCreateEmptyArrayInvokeHasNextThanCheckTheGetResultsIsGoingToBeFalse().
     * Check that when created an array is empty and hasNext() method was invoked by iterator.
     * that expected value is false.
     */

    @Test
    public void whenCreateEmptyArrayInvokeHasNextThanCheckTheGetResultsIsGoingToBeFalse() {
        DynamicArray<Integer> integerDynamicArray = new DynamicArray<>(0);

        Iterator<Integer> itr = integerDynamicArray.iterator();

        boolean has = itr.hasNext();

        assertThat(has, is(false));

    }

}