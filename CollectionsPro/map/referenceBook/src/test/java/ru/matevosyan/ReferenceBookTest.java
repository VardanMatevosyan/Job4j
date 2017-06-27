package ru.matevosyan;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

/**
 * Test ReferenceBook class.
 * Created on 23.06.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class ReferenceBookTest {

    /**
     * whenAddOneObjectThanGetOneValue use to test insertion and get methods.
     */

    @Test
    public void whenAddOneObjectThanGetOneValue() {
        ReferenceBook<Integer, String> book = new ReferenceBook<>();

        book.insert(10, "Ten");

        assertThat(book.get(10), is("Ten"));
    }

    /**
     * whenAddTwoObjectAndDeleteOneThanGetOneObjectValue use to test delete, insertion, get and getSize methods.
     */

    @Test
    public void whenAddTwoObjectAndDeleteOneThanGetOneObjectValue() {
        ReferenceBook<Integer, String> book = new ReferenceBook<>();

        book.insert(10, "Ten");
        book.insert(1, "One");
        book.delete(10);

        assertThat(book.get(1), is("One"));
        assertThat(book.getSize(), is(1));
        assertThat(book.get(10) == null, is(true));
    }

    /**
     * whenAddTwoSameObjectThanGetFalseFromSecondObject use to check return value, when putting the same value with.
     * the same key object.
     */

    @Test
    public void whenAddTwoSameObjectThanGetFalseFromSecondObject() {
        ReferenceBook<Integer, String> book = new ReferenceBook<>();

        book.insert(10, "Ten");
        boolean expectedFalse = book.insert(10, "Ten");


        assertThat(book.getSize(), is(1));
        assertThat(expectedFalse, is(false));
    }

    /**
     * whenAddThreeObjectThanIterate use to check how iterate method is work.
     */

    @Test
    public void whenAddThreeObjectThanIterate() {
        ReferenceBook<Integer, String> book = new ReferenceBook<>();
        ReferenceBook<Integer, String> newBook = new ReferenceBook<>();

        book.insert(10, "Ten");
        book.insert(1, "One");
        book.insert(2, "Two");

        Iterator<ReferenceBook.Node<Integer, String>> iterator = book.iterator();

        while (iterator.hasNext()) {
            ReferenceBook.Node<Integer, String> node = iterator.next();
            newBook.insert(node.getKey(), node.getValue());
        }


        assertThat(book.getSize(), is(newBook.getSize()));
        assertThat(book.get(2), is(newBook.get(2)));
        assertThat(book.get(1), is(newBook.get(1)));
        assertThat(book.get(10), is(newBook.get(10)));

    }

    /**
     * whenAddManyObjectThanCheckTime use to test the quick insertion to the reference-book.
     * capacity is power of two, because of hash function work.
     */

    @Test
    public void whenAddManyObjectThanCheckTime() {
        ReferenceBook<Integer, String> book = new ReferenceBook<>(65536);
        long t0 = System.currentTimeMillis();

        for (int i = 0; i < 65536; i++) {
            book.insert(i, "Ten" +  i);
        }

        long t1 = System.currentTimeMillis();

        double sec = (t1 - t0) * 0.001;

        assertTrue(sec < 0.09);
        assertThat(book.getSize(), is(65536));
    }

    /**
     * whenAddFourObjectThanIterateByNextAndCatchTheException use to test next method and catch NoSuchElementException.
     * when no element left to iteration.
     */

    @Test
    public void whenAddFourObjectThanIterateByNextAndCatchTheException() {
        ReferenceBook<Integer, String> book = new ReferenceBook<>();
        boolean NoElement = false;

        book.insert(10, "Ten");
        book.insert(1, "One");
        book.insert(2, "Two");
        book.insert(5, "wo");

        Iterator<ReferenceBook.Node<Integer, String>> iterator = book.iterator();

        try {
            iterator.next();
            iterator.next();
            iterator.next();
            iterator.next();
            iterator.next();
        } catch (NoSuchElementException nee) {
            NoElement = true;
        }

        assertThat(NoElement, is(true));


    }

}