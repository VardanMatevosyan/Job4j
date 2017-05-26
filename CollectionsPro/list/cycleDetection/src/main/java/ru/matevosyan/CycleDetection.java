package ru.matevosyan;

/**
 * CycleDetection class.
 * Created on 26.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
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
            } else if (slow == fast){
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
        E item;
        Node<E> next;

        public Node(E item) {
            this.item = item;
        }
    }

}