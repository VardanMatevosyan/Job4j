package ru.matevosyan;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ReverseLinkedList class.
 * Created on 04.08.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 * @param <T> type.
 */

public class ReverseLinkedList<T> implements Iterable<T> {
    private Node<T> last;
    private Node<T> first;
    private int size = 0;

    /**
     * Class Node describe linkedList entry.
     * @param <T> parameter that defined when create an instance of a class.
     */

    private class Node<T> {
        private T value;
        private Node<T> next;
        private Node<T> prev;

        /**
         * Construor.
         * @param prev previous element.
         * @param nodeValue node value.
         * @param next next element.
         */
        Node(Node<T> prev, T nodeValue, Node<T> next) {
            this.value = nodeValue;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * Constructor.
     */

    public ReverseLinkedList() {
    }

    /**
     * Create to add value to a list.
     * @param addValue is value that type which you declare in generics.
     */

    public void add(T addValue) {
        Node<T> lastNode = this.last;
        Node<T> newNode = new Node<>(lastNode, addValue, null);
        this.last = newNode;

        if (lastNode != null) {
            lastNode.next = newNode;
        } else {
            this.first = newNode;
        }
        size++;
    }

    /**
     * reverseList method reverse linked list.
     */

    public void reverseList() {
        Node<T> nextNode = this.first;

        while (this.last.prev != null && nextNode != null) {
            Node<T> tmpFirstNext = nextNode.next;
            nextNode.next = nextNode.prev;
            nextNode.prev = tmpFirstNext;

            nextNode = tmpFirstNext;
        }

        Node<T> tmp = this.first;
        this.first = this.last;
        this.last = tmp;

    }


    /**
     * Override iterator method from interface Iterable.
     * @return an instance of Iterator type.
     */

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {
            private Node<T> nextNode = first;
            private int count = 0;
            @Override
            public boolean hasNext() {
                return (count < size) && (last != null);
            }

            @Override
            public T next() {
                count++;
                if (nextNode != null && count > 1) {
                    nextNode = nextNode.next;
                }

                if (nextNode == null) {
                    throw new NoSuchElementException("Does not exist");
                }
                return nextNode.value;

            }
        };
    }
}