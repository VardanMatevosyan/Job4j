package ru.matevosyan;

import java.util.Iterator;

/**
 * Synchronized LinkedList.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 27.09.2017
 * @param <E> type.
 */

public class SynchronizedLinkedList<E> extends DynamicLinkedList<E> {

    private boolean isFinished = false;
    private int size = 0;

    /**
     * Default constructor.
     * default size is 10.
     */

    public SynchronizedLinkedList() {
        super();
    }

    /**
     * Setter for finish variable.
     * @param finished when run method in running thread is finished.
     */

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    /**
     * Add value to dynamic Linked list.
     * @param value is value that type which you declare in generics.
     */

    @Override
    public synchronized void add(E value) {
        super.add(value);
        this.size++;

        if (isFinished) {
            notifyAll();
        }
    }

    /**
     * Get value from the Linked list.
     * @param index position value in an array.
     * @return value of type E.
     * @throws IllegalArgumentException when argument is illigal.
     * @throws ArrayIndexOutOfBoundsException if index is bigger than actual.
     */

    @Override
    public synchronized E get(int index) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        return super.get(index);
    }

    /**
     * Iteror for Linked list.
     * @return iterator type of E.
     */

    @Override
    public synchronized Iterator<E> iterator() {
        if (size < 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return super.iterator();
    }
}
