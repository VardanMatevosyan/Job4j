package ru.matevosyan;

import java.util.Arrays;
import java.util.Iterator;

/**
 * DynamicArray class.
 * Created on 18.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 * @param <E> undefine type.
 */

public class DynamicArray<E> implements IDynamicArray<E>,  Iterable<E> {

    private Object[] container;
    private static final Object[] EMPTYARRAY = {};
    private static final int DEFAULTARRAYSIZE = 10;
    private int index = 0;

    /**
     * Constructor for creating default size for dynamic array or if size is 0 create an empty array.
     * @param size of array.
     */

    public DynamicArray(int size) {
        if (size > 0) {
            this.container = new Object[size];
        } else if (size == 0) {
            this.container = EMPTYARRAY;
        } else {
            throw new IllegalArgumentException("Please pass correct array size");
        }
    }

    /**
     * If arrays size was not passed to constructor, create an array with default size.
     */

    public DynamicArray() {
        this.container = new Object[DEFAULTARRAYSIZE];
    }

    /**
     * Create to add value to an array.
     * @param value is value that type which you declare in generics.
     * @throws ArrayIndexOutOfBoundsException if index is bigger than actual.
     */

    @Override
    public void add(E value) throws ArrayIndexOutOfBoundsException {
        checkSize(this.index + 1);
        this.container[this.index++] = value;

    }

    /**
     * Create to get the value from an array.
     * @param index position value in an array.
     * @return value that type that was declare in generic diamonds.
     * @throws IllegalArgumentException if is not correct index size.
     * @throws ArrayIndexOutOfBoundsException if index is bigger than actual.
     */

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        return (E) this.container[index];
    }

    /**
     * Check array size and rise it when an array length is equal or bigger that default size.
     * Or if it is the last index that going to be hold by passing value.
     * @param nextIndex is next index that going to be checked.
     */

    private void checkSize(int nextIndex) {
        if (this.container.length - 1 == nextIndex && this.container.length >= DEFAULTARRAYSIZE) {
            int nexSize = (this.container.length * 3) / 2 + 1;
            this.container = Arrays.copyOf(this.container, nexSize);
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
                return count < container.length - (container.length - index);
            }

            @Override
            @SuppressWarnings("unchecked")
            public E next() {
                return (E) container[count++];
            }
        };
    }
}