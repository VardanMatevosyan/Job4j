package ru.matevosyan;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * DynamicSetByLinkedListclass.
 * Created on 29.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class DynamicSetByLinkedList<E> implements IDynamicSetByLinkedList<E>, Iterable<E> {

    private Node<E> last;
    private int size = 0;
    private Node<E> first;

    /**
     * Constructor.
     */

    public DynamicSetByLinkedList() {

    }

    /**
     * Create to add value to the set.
     * @param value is value that type which you declare in generics.
     */

    @Override
    public void add(E value) {
        if (!checkDuplicate(value)) {
            linkLast(value);
        }
    }

    private boolean checkDuplicate(E value) {
        Node<E> firstNode = first;
        boolean hasDuplicate = false;

        for (int i = 0; i < size & size > 1; i++) {
            firstNode = firstNode.next;
            if (firstNode == null) {
                break;
            }
            if (firstNode.item.equals(value)) {
                hasDuplicate = true;
            }
        }
        return hasDuplicate;
    }

    /**
     * linkLast assign LinkedList Node to the last Node in the list and if it is the first value assign as first too.
     * @param elementValue is value.
     */

    private void linkLast(E elementValue) {
        Node<E> lastNode = last;
        Node<E> newNode = new Node<>(lastNode, elementValue, null);
        last = newNode;
        if (lastNode != null) {
            lastNode.next = newNode;
        } else {
            first = newNode;
        }
        size++;
    }

    /**
     * For returning the size of set.
     * @return size
     */

    public int len() {
        return size;
    }
    /**
     * Class Node describe linkedList entry.
     * @param <E> parameter that defined when create an instance of a class.
     */

    private static class Node<E> {
        E item;
        Node<E> prev;
        Node<E> next;

        Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.item = element;
            this.next = next;
        }
    }

    /**
     * Override iterator method from interface Iterable.
     * @return an instance of Iterator type.
     */

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {

            int count = 0;
            @Override
            public boolean hasNext() {
                return (count < size) && (last != null);
            }

            @Override
            public E next() {
                count++;
                Node<E> nextNode = first;

                if (nextNode != null && count > 1) {
                    nextNode = nextNode.next;
                }

                if (nextNode == null) {
                    throw new NoSuchElementException("Does not exist");
                }
                return nextNode.item;

            }
        };
    }
}