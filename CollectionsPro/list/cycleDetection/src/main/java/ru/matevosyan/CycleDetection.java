package ru.matevosyan;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * CycleDetection class.
 * Created on 26.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class CycleDetection<E> {

    private int size = 0;


    /**
     * Constructor.
     */

    public CycleDetection() {
    }

    public boolean hasCycle(Node<E> first) {
        Node<E> fast;
        Node<E> slow;
        boolean detForFastToSlow = true;

        if (first != null) {
            fast = first;
            slow = first;
        } else {
            return false;
        }

        while (fast != null) {
            if (detForFastToSlow) {
                fast = fast.next.next;
            } else {
                fast = fast.next;
            }

            if (fast == slow.next) {
                detForFastToSlow = false;
                if (fast == slow) {
                    slow = first;
                }
            }
        }
        return detForFastToSlow;
    }




    /**
     * Class Node describe linkedList entry.
     * @param <E> parameter that defined when create an instance of a class.
     */

    static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }

        public Node(E item) {
            this.item = item;
        }
    }

}