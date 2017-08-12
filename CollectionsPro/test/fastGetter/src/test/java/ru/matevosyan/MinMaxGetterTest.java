package ru.matevosyan;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * MinMaxGetterTest class.
 * Created on 12.08.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class MinMaxGetterTest {

    /**
     * Check bigger and smaller value from the map.
     */

    @Test
    public void whenAddValueToTheMapThanCheckBiggerAndSmallerValue() {
        MinMaxGetter<Integer, Integer> minMaxGetter = new MinMaxGetter<>();

        minMaxGetter.add(1, 45);
        minMaxGetter.add(2, 12);
        minMaxGetter.add(4, 1);
        minMaxGetter.add(6, 100);

        Integer minValue = minMaxGetter.getMinValue();
        Integer maxValue = minMaxGetter.getMaxValue();

        Integer expectedMinValue = 1;
        Integer expectedMaxValue = 100;

        assertThat(minValue, is(expectedMinValue));
        assertThat(maxValue, is(expectedMaxValue));
    }

    /**
     * Check bigger and smaller values key.
     */

    @Test
    public void whenAddValueToTheMapThanCheckTheBiggerValuesKeyAndTheSmallerValuesKey() {
        MinMaxGetter<Integer, Integer> minMaxGetter = new MinMaxGetter<>();

        minMaxGetter.add(1, 45);
        minMaxGetter.add(2, 12);
        minMaxGetter.add(42300, 1);
        minMaxGetter.add(6, 10000);

        Integer minValue = minMaxGetter.getMinValuesKey();
        Integer maxValue = minMaxGetter.getMaxValuesKey();

        Integer expectedMinValueKey = 42300;
        Integer expectedMaxValueKey = 6;

        assertThat(minValue, is(expectedMinValueKey));
        assertThat(maxValue, is(expectedMaxValueKey));
    }

    /**
     * Check value after pop it from the map.
     */

    @Test
    public void whenAddValueToTheMapAndPopValueThanCheckBiggerAndSmallerValue() {
        MinMaxGetter<Integer, Integer> minMaxGetter = new MinMaxGetter<>();

        Integer expectedMinValueKey = 1;
        Integer expectedMaxValueKey = 1560;
        Integer expectedPopValueKey = 3;
        boolean containsKeyInTheMap;
        boolean expectedContainsKeyAfterPop;

        minMaxGetter.add(1, 45);
        minMaxGetter.add(2, 12);
        minMaxGetter.add(4, 1);
        minMaxGetter.add(6, 200);
        minMaxGetter.add(8, 1560);
        minMaxGetter.add(3, 100560007);

        containsKeyInTheMap = minMaxGetter.containsKey(3);
        Integer popValue = minMaxGetter.popKey();
        expectedContainsKeyAfterPop = minMaxGetter.containsKey(expectedPopValueKey);

        Integer minValue = minMaxGetter.getMinValue();
        Integer maxValue = minMaxGetter.getMaxValue();


        assertThat(minValue, is(expectedMinValueKey));
        assertThat(maxValue, is(expectedMaxValueKey));
        assertThat(popValue, is(expectedPopValueKey));
        assertThat(containsKeyInTheMap, is(true));
        assertThat(expectedContainsKeyAfterPop, is(false));


    }

    /**
     * test all getter method that provide exception and check if map is empty.
     */

    @Test
    public void whenTryGetValueFromEmptyMapThanGetExceptionAndCheckIt() {
        MinMaxGetter<Integer, Integer> minMaxGetter = new MinMaxGetter<>();

        boolean cachedByExceptionByGetMaxKey = false;
        boolean cachedByExceptionByGetMinKey = false;
        boolean cachedByExceptionByGetMin = false;
        boolean cachedByExceptionByGetMax = false;
        boolean cachedByExceptionByPop = false;

        try {
            minMaxGetter.getMaxValuesKey();
        } catch (NoSuchElementException nse) {
            cachedByExceptionByGetMaxKey = true;
        }

        try {
            minMaxGetter.getMinValuesKey();
        } catch (NoSuchElementException nse) {
            cachedByExceptionByGetMinKey = true;
        }

        try {
            minMaxGetter.getMinValue();
        } catch (NoSuchElementException nse) {
            cachedByExceptionByGetMax = true;
        }

        try {
            minMaxGetter.getMaxValue();
        } catch (NoSuchElementException nse) {
            cachedByExceptionByGetMin = true;
        }

        try {
            minMaxGetter.popKey();
        } catch (NoSuchElementException nse) {
            cachedByExceptionByPop = true;
        }

        assertThat(cachedByExceptionByGetMin, is(true));
        assertThat(cachedByExceptionByPop, is(true));
        assertThat(cachedByExceptionByGetMax, is(true));
        assertThat(cachedByExceptionByGetMinKey, is(true));
        assertThat(cachedByExceptionByGetMaxKey, is(true));
    }

}