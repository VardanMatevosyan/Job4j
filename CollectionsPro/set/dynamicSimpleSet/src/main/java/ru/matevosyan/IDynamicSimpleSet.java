package ru.matevosyan;

/**
 * IDynamicSimpleSet interface.
 * Created on 27.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public interface IDynamicSimpleSet<E> {

    /**
     * Add value to set.
     * @param value is value that type which you declare in generics.
     */

    void add(E value);

    /**
     * Get value from an array by index passing.
     * @param index position value in an array.
     * @return value that type specify in generic.
     */

    E get(int index);

    /**
     * Size of an array.
     * @return set size.
     */

    int length();

}
