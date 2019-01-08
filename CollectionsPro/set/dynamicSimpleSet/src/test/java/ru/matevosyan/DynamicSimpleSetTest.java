package ru.matevosyan;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

/**
 *  DynamicSimpleSetTest class.
 * Created on 27.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class DynamicSimpleSetTest {

    /**
     * Create whenAddDuplicateThanReturnWithoutDuplicates() to test {@link DynamicSimpleSet#add(Object)}.
     * if try to add duplicate to set than do not add.
     */

    @Test
    public void whenAddDuplicateThanReturnWithoutDuplicates() {
        DynamicSimpleSet<Integer> dynamicSimpleSet = new DynamicSimpleSet<>();

        dynamicSimpleSet.add(3);
        dynamicSimpleSet.add(3);
        dynamicSimpleSet.add(2);

        Integer[] integers = new Integer[2];
        integers[0] = 3;
        integers[1] = 2;

        assertThat(dynamicSimpleSet.get(0), is(integers[0]));
        assertThat(dynamicSimpleSet.length(), is(2));
    }

    /**
     * Create whenAddNotDuplicateThanCheckTheSameValues() to test {@link DynamicSimpleSet#add(Object)}.
     * if try to add not duplicate value to set than add it.
     */

    @Test
    public void whenAddNotDuplicateThanCheckSize() {
        DynamicSimpleSet<Integer> dynamicSimpleSet = new DynamicSimpleSet<>();

        dynamicSimpleSet.add(3);
        dynamicSimpleSet.add(2);
        dynamicSimpleSet.add(4);
        dynamicSimpleSet.add(5);

        assertThat(dynamicSimpleSet.length(), is(4));
    }

    /**
     * Create whenAddValuesThanWithIteratorCheckTheSameValues() to test {@link DynamicSimpleSet#add(Object)}.
     * if add value to set than, check values with iterator.
     */

    @Test
    public void whenAddValuesThanWithIteratorCheckTheSameValues() {
        DynamicSimpleSet<Integer> dynamicSimpleSet = new DynamicSimpleSet<>();

        dynamicSimpleSet.add(3);
        dynamicSimpleSet.add(3);
        dynamicSimpleSet.add(3);
        dynamicSimpleSet.add(3);

        int[] integers = new int[2];
        integers[0] = 3;

        Iterator iterator = dynamicSimpleSet.iterator();
        int i = 0;

        while (iterator.hasNext()) {
            assertTrue((int) iterator.next() == integers[0]);
        }

        assertThat(dynamicSimpleSet.length(), is(1));
    }

    /**
     * Create whenGetValuesFromIndexThatOutOfBoundsArrayThanCheckArrayIndexOutOfBoundsException() to test.
     * ArrayIndexOutOfBoundsException when get index that is out of bounds.
     */

    @Test
    public void whenGetValuesFromIndexThatOutOfBoundsArrayThanCheckArrayIndexOutOfBoundsException() {
        DynamicSimpleSet<Integer> dynamicSimpleSet = new DynamicSimpleSet<>();
        Throwable throwable = null;

        dynamicSimpleSet.add(3);

        try {
            dynamicSimpleSet.get(1);
        } catch (Throwable e) {
            throwable = e;
        }

        assertTrue(throwable instanceof ArrayIndexOutOfBoundsException);
    }

    /**
     * Create whenGetValuesFromIndexThatIllegalArgumentExceptionThanCheckIllegalArgumentException() to test.
     * IllegalArgumentException when get index that is illegal.
     */

    @Test
    public void whenGetValuesFromIndexThatIllegalArgumentExceptionThanCheckIllegalArgumentException() {
        DynamicSimpleSet<Integer> dynamicSimpleSet = new DynamicSimpleSet<>();
        Throwable throwable = null;
        dynamicSimpleSet.add(3);

        try {
            dynamicSimpleSet.get(-5);
        } catch (Throwable e) {
            throwable = e;
        }
        assertTrue(throwable instanceof IllegalArgumentException);
    }

}