package ru.matevosyan;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

/**
 * DynamicSetByLinkedListTest class for testing DynamicLinkedList class.
 * Created on 29.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class DynamicSetByLinkedListTest {

    /**
     * Create whenAddTwoElementsThanCheckOneOfThenGettingByIndex() method to check added element to the list.
     */

    @Test
    public void whenAddTwoElementsThanCheckOneOfThenGettingByIndex() {

        DynamicLinkedList<Integer> integerDynamicLinkedList = new DynamicLinkedList<>();

        integerDynamicLinkedList.add(12);
        integerDynamicLinkedList.add(42);

        assertThat(integerDynamicLinkedList.get(1), is(42));

    }

    /**
     * Create whenGetValueFromListThanCheckTheGetResults() to invoke {@link DynamicLinkedList#get(int)} to check.
     * the result.
     */

    @Test
    public void whenGetValueFromListThanCheckTheGetResults() {
        DynamicLinkedList<Integer> integerDynamicLinkedList = new DynamicLinkedList<>();

        integerDynamicLinkedList.add(12);
        integerDynamicLinkedList.add(2);
        integerDynamicLinkedList.add(2);

        int value = integerDynamicLinkedList.get(2);
        assertThat(value, is(2));
    }

    /**
     * Create whenGetValueFromLinkedListWithForLoopThanCheckTheGetResults() to check value of elements.
     * When added elements to the LinkedList using for loop, that check the result.
     */

    @Test
    public void whenGetValueFromLinkedListWithForLoopThanCheckTheGetResults() {
        DynamicLinkedList<Integer> integerDynamicLinkedList = new DynamicLinkedList<>();

        for (int i = 0; i < 20; i++) {
            integerDynamicLinkedList.add(i);
        }

        int value = integerDynamicLinkedList.get(2);
        assertThat(value, is(2));
    }

    /**
     * Create whenInvokeNextMethodWithoutAddingTheValuesToLinkedListThanCheckWaitingForException to check an exception.
     */

    @Test
    public void whenInvokeNextMethodWithoutAddingTheValuesToLinkedListThanCheckWaitingForException() {

        Throwable e = null;

        try {
            DynamicLinkedList<Integer> integerDynamicLinkedList = new DynamicLinkedList<>();
            Iterator<Integer> itr = integerDynamicLinkedList.iterator();
            itr.next();
        } catch (Throwable ex) {
            e = ex;
        }

        assertTrue(e instanceof NoSuchElementException);
    }


    /**
     * Create whenGetIteratorFromLinkedListAndUseHasNextAndNextThanCheckTheGetResults() to check next method.
     * In {@link DynamicLinkedList#iterator()}.
     */

    @Test
    public void whenGetIteratorFromLinkedListAndUseHasNextAndNextThanCheckTheGetResults() {
        DynamicLinkedList<Integer> integerDynamicLinkedList = new DynamicLinkedList<>();

        Iterator<Integer> itr = integerDynamicLinkedList.iterator();

        integerDynamicLinkedList.add(12);
        integerDynamicLinkedList.add(2);
        integerDynamicLinkedList.add(3);

        int value = 0;
        if (itr.hasNext()) {
            value = itr.next();
        }


        assertThat(value, is(12));
    }

    /**
     * Create whenInvokeHasNextThanCheckTheGetResultsIsGoingToBeTrue().
     * To check hasNext() in {@link DynamicLinkedList#iterator()}.
     */

    @Test
    public void whenInvokeHasNextThanCheckTheGetResultsIsGoingToBeTrue() {
        DynamicLinkedList<Integer> integerDynamicLinkedList = new DynamicLinkedList<>();

        Iterator<Integer> itr = integerDynamicLinkedList.iterator();

        integerDynamicLinkedList.add(12);
        integerDynamicLinkedList.add(2);

        boolean has = itr.hasNext();

        assertThat(has, is(true));
    }

    /**
     * Create whenInvokeHasNextAndNextThanCheckTheGetResultsIsGoingToBeFalse() to check hasNext().
     * when in the LinkedList does not have any element and expected return false.
     */

    @Test
    public void whenInvokeHasNextAndNextThanCheckTheGetResultsIsGoingToBeFalse() {
        DynamicLinkedList<Integer> integerDynamicLinkedList = new DynamicLinkedList<>();

        Iterator<Integer> itr = integerDynamicLinkedList.iterator();

        integerDynamicLinkedList.add(12);
        integerDynamicLinkedList.add(2);

        itr.hasNext();
        int a = itr.next();
        int c = itr.next();
        boolean has = itr.hasNext();

        assertThat(has, is(false));
        assertThat(a, is(12));
        assertThat(c, is(2));

    }



}