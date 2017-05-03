package ru.matevosyan;

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
        int value = 0;
        if (array.length % 2 == 0 && array[0].length % 2 == 0) {
            if (indexY < array.length) {
                value = this.array[indexX][indexY++];
            } else if (indexX < array.length) {
                indexY = 0;
                indexX++;
                value = this.array[indexX][indexY++];
            }
        } else {
            if (indexY < array[0].length) {
                value = this.array[indexX][indexY++];
            } else if (indexX < array.length){
                indexY = 0;
                indexX++;
                value = this.array[indexX][indexY++];
            }
        }
        return  value;
    }


    @Override
    public boolean hasNext() {
        boolean has = false;
        if (array.length % 2 == 0 && array[0].length % 2 == 0) {

            if (indexY >= array.length && indexX >= array.length - 1) {
                has = false;
            } else {
                has = true;
            }

        } else if (array.length % 2 != 0 &&  array.length > array[0].length) {

            if (indexX + indexY <= array.length) {
                has = true;
            } else {
                has = false;
            }
        } else if (array.length % 2 == 0 &&  array.length < array[0].length) {
                if (indexX + indexY <= array[0].length) {
                    has = true;
                } else {
                    has = false;
                }
            }
        return has;
    }
}
