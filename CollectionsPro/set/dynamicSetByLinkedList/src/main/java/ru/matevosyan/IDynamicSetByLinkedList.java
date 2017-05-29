package ru.matevosyan;

/**
 * IDynamicSetByLinkedList interface.
 * Created on 29.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public interface IDynamicSetByLinkedList<E> {

    /**
     * Add value to the set by LinkedList.
     * @param value is value that type which you declare in generics.
     */

    void add(E value);

    /**
     * Get value from the set by index passing.
     * @param index position value in the list.
     * @return value that type specify in generic.
     */

    E get(int index);
}
