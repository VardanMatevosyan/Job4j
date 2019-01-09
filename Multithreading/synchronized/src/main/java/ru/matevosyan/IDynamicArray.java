package ru.matevosyan;

/**
 * IDynamicArray interface.
 * Created on 18.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 * @param <E> undefine type.
 */

public interface IDynamicArray<E> {

    /**
     * Add value to an array.
     * @param value is value that type which you declare in generics.
     */

    void add(E value);

    /**
     * Get value from an array by index passing.
     * @param index position value in an array.
     * @return value that type specify in generic.
     */

    E get(int index);
}
