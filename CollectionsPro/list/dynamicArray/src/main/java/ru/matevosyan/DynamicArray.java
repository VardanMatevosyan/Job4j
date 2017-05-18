package ru.matevosyan;

/**
 * DynamicArray class.
 * Created on 18.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class DynamicArray<E> implements IDynamicArray<E> {

    private Object[] container;
    private static final Object[] EMPTYARRAY = {};
    private static final int DEFAULTARRAYSIZE = 10;
    private int index = 0;

    public DynamicArray(int size) {
        if (size > 0) {
            this.container = new Object[size];
        } else if (size == 0) {
            this.container = EMPTYARRAY;
        } else {
            throw new IllegalArgumentException("Please pass correct array size");
        }
    }

    public DynamicArray() {
        this.container = new Object[DEFAULTARRAYSIZE];
    }

    @Override
    public void add(E value) {
        checkSize(this.index + 1);
        this.container[this.index++] = value;

    }



    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) this.container[index];
    }

    private void checkSize(int nextIndex) {
        if (this.container.length - 1 == nextIndex) {
            int nexSize = (this.container.length * 3) / 2 + 1;
            Object[] container = new Object[nexSize];
            System.arraycopy(this.container, 0, container, 0, this.container.length);

        }
    }
}