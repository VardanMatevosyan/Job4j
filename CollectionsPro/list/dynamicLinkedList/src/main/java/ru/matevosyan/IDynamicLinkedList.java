package ru.matevosyan;

/**
 * IDynamicLinkedList interface.
 * Created on 21.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public interface IDynamicLinkedList<E> {

    /**
     * Add value to LinkedList.
     * @param value is value that type which you declare in generics.
     */

    void add(E value);

    /**
     * Get value from LinkedList by index passing.
     * @param index position value in the list.
     * @return value that type specify in generic.
     */

    E get(int index);
}
