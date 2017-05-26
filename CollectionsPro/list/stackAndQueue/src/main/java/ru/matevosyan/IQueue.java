package ru.matevosyan;

/**
 * IQueue interface.
 * Created on 25.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public interface IQueue<E> {
    /**
     * Add value to the Queues tail.
     * @param value is value that type which you declare in generics.
     */

    E push(E value);

    /**
     * Remove value from the head.
     * @return value that type specify in generic.
     */

    E pop();

    /**
     * Get value from the Queue.
     * @return value that type specify in generic.
     */

    E peek();
}
