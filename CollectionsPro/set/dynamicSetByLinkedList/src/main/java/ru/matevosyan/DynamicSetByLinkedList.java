package ru.matevosyan;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * DynamicSetByLinkedListclass.
 * Created on 29.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 * @param <E> type.
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

    /**
     * Check duplicate.
     * @param value to check.
     * @return true if is duplicate.
     */
    private boolean checkDuplicate(E value) {
        Node<E> lastNode = last;
        boolean hasDuplicate = false;

        while (lastNode != null) {
            if (size >= 1) {
                if (lastNode.item != null & size >= 1) {
                    hasDuplicate = false;
                } else if (lastNode.item == null) {
                    hasDuplicate = true;
                } else {
                    hasDuplicate = true;
                }

                if (!hasDuplicate) {
                    if (lastNode.item.equals(value)) {
                        hasDuplicate = true;
                    }
                }
            }
            lastNode = lastNode.prev;
        }

        return hasDuplicate;
    }

    /**
     * linkLast assign LinkedList Node to the last Node in the list and if it is the first value assign as first too.
     * @param elementValue is value.
     */

    private void linkLast(E elementValue) {
        Node<E> lastNode = this.last;
        Node<E> newNode = new Node<>(lastNode, elementValue, null);
        this.last = newNode;
        if (lastNode != null) {
            lastNode.next = newNode;
        } else {
            this.first = newNode;
        }
        size++;

        if (size > 1) {
            sortByHashcode();
        }
    }

    /**
     *  Created to sort collection by objects hash code.
     */

    private void sortByHashcode() {
        Node<E> node = this.first;

        while (node != null) {
            if (this.last.item.hashCode() < node.item.hashCode()) {
                Node<E> tmp = this.last.prev;
                this.last.next = node;
                this.last.prev = node.prev;
                node.prev = this.last;
                if (this.last.prev == null) {
                    this.first = this.last;
                } else {
                    this.last.prev.next = this.last;
                }
                tmp.next = null;
                this.last = tmp;
                break;
            }

            node = node.next;
        }
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
        private E item;
        private Node<E> prev;
        private Node<E> next;

        /**
         * Constructor for NODE.
         * @param prev previous element.
         * @param element itseld representing value.
         * @param next next element.
         */
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

            private int count = 0;
            @Override
            public boolean hasNext() {
                return (count < size) && (last != null);
            }

            @Override
            public E next() {
                count++;

                if (first != null && count > 1) {
                    first = first.next;
                }

                if (size < count) {
                    throw new NoSuchElementException("Does not exist");
                } else {
                    return first != null ? first.item : null;
                }


            }
        };
    }
}