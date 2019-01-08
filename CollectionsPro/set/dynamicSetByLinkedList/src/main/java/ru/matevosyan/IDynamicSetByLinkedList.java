package ru.matevosyan;

/**
 * IDynamicSetByLinkedList interface.
 * Created on 29.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 * @param <E> type.
 */

public interface IDynamicSetByLinkedList<E> {

    /**
     * Add value to the set by LinkedList.
     * @param value is value that type which you declare in generics.
     */

    void add(E value);

}
