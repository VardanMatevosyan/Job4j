package ru.matevosyan;

/**
 * IStack interface.
 * Created on 23.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public interface IStack<E> {

    /**
     * Add value to the top of Stack.
     * @param value is value that type which you declare in generics.
     */

    E push(E value);

    /**
     * Remove value from top.
     * @return value that type specify in generic.
     */

    E pop();

    /**
     * Get value from Stack.
     * @return value that type specify in generic.
     */

    E peek();

}
