package ru.matevosyan;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * CycleDetection class.
 * Created on 26.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class CycleDetection<E>{

    private Node<E> last;
    private int size = 0;
    private Node<E> first;

    /**
     * Constructor.
     */

    public CycleDetection() {

    }


    /**
     * Class Node describe linkedList entry.
     * @param <E> parameter that defined when create an instance of a class.
     */

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

}