package ru.matevosyan;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;

/**
 * Test EvenNumber class.
 * Created on 14.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class SimpleArrayTest {

    /**
     * whenAddValueToSimpleArrayThanCheckArraysValue() method created to test {@link SimpleArray#add(Object)} method.
     */

    @Test
    public void whenAddValueToSimpleArrayThanCheckArraysValue() {
        SimpleArray<String> stringSimpleArray = new SimpleArray<>(2);
        String expectedValue = "firstString";

        stringSimpleArray.add("firstString");
        stringSimpleArray.add("secondString");

        assertThat(expectedValue, is(stringSimpleArray.getObject()[0]));
    }

    /**
     * whenAddAndGetValueThanCheckGetValue() method created to test {@link SimpleArray#get(int)} method.
     */

    @Test
    public void whenAddAndGetValueThanCheckGetValue() {
        SimpleArray<String> stringSimpleArray = new SimpleArray<>(1);
        String expectedValue = "firstString";

        stringSimpleArray.add("firstString");

        assertThat(expectedValue, is(stringSimpleArray.get(0)));
    }

    /**
     * whenAddAndUpdateValueThanCheckUpdateValue() method created to test {@link SimpleArray#update(Object, Object)}.
     */

    @Test
    public void whenAddAndUpdateValueThanCheckUpdateValue() {
        SimpleArray<String> stringSimpleArray = new SimpleArray<>(1);
        String expectedValue = "updateString";

        stringSimpleArray.add("firstString");
        stringSimpleArray.update("firstString", "updateString");

        assertThat(expectedValue, is(stringSimpleArray.getObject()[0]));
    }

    /**
     * whenAddTwoElementAndDeleteOneThanCheckValue() method created to test {@link SimpleArray#delete(Object)} method.
     */

    @Test
    public void whenAddTwoElementAndDeleteOneThanCheckValue() {
        SimpleArray<String> stringSimpleArray = new SimpleArray<>(2);
        String expectedValue = "secondString";

        stringSimpleArray.add("firstString");
        stringSimpleArray.add("secondString");
        stringSimpleArray.delete("firstString");

        assertThat(expectedValue, is(stringSimpleArray.getObject()[0]));
        assertFalse(stringSimpleArray.getObject()[0].toString().contains("firstString"));
        assertTrue(stringSimpleArray.getObject()[0].toString().contains("secondString"));
    }

}