package ru.matevosyan;

import java.util.NoSuchElementException;

/**
 * Queue class.
 * Created on 25.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class Queue<E> implements IQueue<E> {

    private Node<E> tail;
    private Node<E> head;
    private int size = 0;

    /**
     * Add value to the Queues tail.
     * @param value is value that type which you declare in generics.
     */

    @Override
    public E push(E value) {
        Node<E> tailElement = new Node<>(value);
        if (tail != null) {
            tail.next = tailElement;
        }
        tail = tailElement;
        if (head == null) {
            head = tailElement;
        }
        size++;
        return tailElement.item;
    }

    /**
     * Remove value from the head.
     * @return value that type specify in generic.
     */

    @Override
    public E pop() {
        Node<E> popElement;
        if (head != null) {
            popElement = head;
            head = head.next;
        }  else {
            throw new NoSuchElementException("No such element in the queue");
        }
        size--;
        return popElement.item;
    }

    /**
     * Get value from the Queue.
     * @return value that type specify in generic.
     */

    @Override
    public E peek() {
        E peekElement;
        if (head != null) {
            peekElement = head.item;
        }  else {
            throw new NoSuchElementException("No such element in the queue");
        }
        return peekElement;
    }


    /**
     * Class Node describe linkedList entry.
     * @param <E> parameter that defined when create an instance of a class.
     */

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element) {
            this.item = element;
        }
    }
}
