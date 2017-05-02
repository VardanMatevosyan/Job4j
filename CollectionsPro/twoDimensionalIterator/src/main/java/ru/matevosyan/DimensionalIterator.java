package ru.matevosyan;

import java.util.Objects;

/**
 * TwoDementionIterator class.
 * Created on 28.04.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class DimensionalIterator implements ArrayIterator {

    private int indexX = 0;
    private int indexY = 0;
    private final int[][] array;

    public DimensionalIterator(int[][] array) {
        this.array = array;
    }

    @Override
    public int next() {
        return array[indexX++][indexY++];
    }


    @Override
    public boolean hasNext() {
        return array.length > indexX && array.length > indexY;
    }
}
