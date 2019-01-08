package ru.matevosyan;

/**
 * IQueue interface.
 * Created on 25.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 * @param <E> parametrize type.
 */

public interface IQueue<E> {
    /**
     * Add value to the Queues tail.
     * @param value is value that type which you declare in generics.
     * @return E parametrize type.
     */

    E push(E value);

    /**
     * Remove value from the head.
     * @return E value that type specify in generic.
     */

    E pop();

    /**
     * Get value from the Queue.
     * @return E value that type specify in generic.
     */

    E peek();
}
