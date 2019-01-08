package ru.matevosyan;

/**
 * CycleDetection class.
 * Created on 26.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 * @param <E> parametrize type.
 */

public class CycleDetection<E> {

    /**
     * Constructor.
     */

    public CycleDetection() {
    }

    /**
     * Create hasCycle method to check if Linked list have cycle detection.
     * @param first Node from the list.
     * @return true if list have cycle, else return false.
     */

    public boolean hasCycle(Node<E> first) {
        Node<E> fast = first;
        Node<E> slow = first;
        boolean detForFastToSlow = true;

        if (first == null) {
            return false;
        }

        while (fast != null && fast.next != null) {
            if (detForFastToSlow) {
                fast = fast.next.next;
                slow = slow.next;
            } else {
                fast = fast.next;
                slow = slow.next;
                if (fast == slow) {
                    return true;
                }
            }

            if (slow == first) {
                return true;
            } else if (slow == fast) {
                detForFastToSlow = false;
                slow = first;
            }
        }
        return false;
    }




    /**
     * Class Node describe linkedList entry.
     * @param <E> parameter that defined when create an instance of a class.
     */

    static class Node<E> {
        private E item;
        private Node<E> next;

        /**
         * Get item.
         * @return E item.
         */
        public E getItem() {
            return item;
        }

        /**
         * Set item.
         * @param item Node value.
         */
        public void setItem(E item) {
            this.item = item;
        }

        /**
         * Get next Node element.
         * @return Node<E> next element.
         */
        public Node<E> getNext() {
            return next;
        }

        /**
         * Set Node element.
         * @param next set next Node element
         */
        public void setNext(Node<E> next) {
            this.next = next;
        }

        /**
         * Constructor.
         * @param item in the Node represents value.
         */
        Node(E item) {
            this.item = item;
        }
    }

}