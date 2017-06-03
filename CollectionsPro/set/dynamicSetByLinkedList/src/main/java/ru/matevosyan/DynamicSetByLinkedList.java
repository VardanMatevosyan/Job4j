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
        Node<E> firstNode = last;
        boolean hasDuplicate = false;

//        for (int i = 0; i < size & size >= 1; i++) {

//            if (firstNode != null) {
//                firstNode = firstNode.next;
//
//            }
        if (size >= 1) {
            if (firstNode.item != null & size >= 1) {
                hasDuplicate = false;
            } else if (firstNode.item == null) {
                hasDuplicate = true;
            } else {
                hasDuplicate = true;
            }


            if (!hasDuplicate) {
                if (firstNode.item.equals(value)) {
                    hasDuplicate = true;
//                break;
                }
            }
        }

//        }

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

        if(size > 1) {
            sortByHashcode();
        }
    }

    private void sortByHashcode() {
        Node<E> node = first;

        while (node != null) {
            if (last.item.hashCode() < node.item.hashCode()) {
                Node<E> tmp = last.prev;
                last.next = node;
                last.prev = node.prev;
                node.prev = last;
                if (last.prev == null) {
                    first = last;
                } else {
                    last.prev.next = last;
                }
                tmp.next = null;
                last = tmp;
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