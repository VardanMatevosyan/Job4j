package ru.matevosyan;

import java.util.Iterator;

/**
 * Synchronized DynamicArrayList.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 27.09.2017
 */

public class SynchronizedDynamicArrayList<E> extends DynamicArray<E> {

    private boolean finish = false;
    private int size = 0;

    /**
     * Constructor with ability to assign array size.
     * @param size array size.
     */

    public SynchronizedDynamicArrayList(int size) {
        super(size);
    }

    /**
     * Default constructor.
     * default size is 10.
     */

    public SynchronizedDynamicArrayList() {
        super();
    }

    /**
     * Setter for finish variable.
     * @param finish when run method in running thread is finished.
     */

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    /**
     * Add value to dynamic array.
     * @param value is value that type which you declare in generics.
     */

    @Override
    public synchronized void add(E value) {
            super.add(value);
            this.size++;
            if (finish) {
                this.notify();
            }

    }

    /**
     * Get value from the dynamic array.
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
     * Iterator for dynamic array.
     * @return iterator type of E.
     */

    @Override
    public synchronized Iterator<E> iterator() {
        if (this.size < 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return super.iterator();
    }
}
