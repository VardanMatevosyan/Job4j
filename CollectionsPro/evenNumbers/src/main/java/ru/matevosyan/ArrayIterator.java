package ru.matevosyan;

/**
 * ArrayIterator class.
 * Created on 28.04.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public interface ArrayIterator {


    /**
     * Created next() method to iterate each element from two-dimensional array.
     * @return  arrays element value.
     */

    int[] next();

    /**
     * Created hasNext() method to check if eny of elements are in array to read, looking to next() pointer.
     * @return true if there are any value to read, else false.
     */

    boolean hasNext();
}