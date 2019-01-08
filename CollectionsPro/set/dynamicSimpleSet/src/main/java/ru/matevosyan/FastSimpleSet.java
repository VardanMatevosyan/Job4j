package ru.matevosyan;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * FastSimpleSet class.
 * Created on 04.06.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 * @param <E> type.
 */

public class FastSimpleSet<E> implements Iterable<E> {

    private Object[] container;
    private static final int DEFAULT_ARRAY_SIZE = 10;
    private int size = 0;

    /**
     * Constructor.
     * If arrays size was not passed to constructor, create an array with default size.
     */

    public FastSimpleSet() {
        this.container = new Object[DEFAULT_ARRAY_SIZE];
    }

    /**
     * Add method.
     * @param value that gonna be put in container.
     */

    public void add(E value) {
        checkSize(this.size + 1);

        if (!checkDuplicate(value)) {
            int index = getInsertIndex(value);

            Object[] tmpHolder = new Object[this.container.length];
            System.arraycopy(this.container, 0, tmpHolder, 0, this.container.length - index - 1);

            tmpHolder[index] = value;

            System.arraycopy(this.container, index, tmpHolder, index + 1, this.size - index);
            this.container = tmpHolder;

            size++;
        }
    }

    /**
     * To get the index use binary search.
     * @param value that gonna be added.
     * @return index.
     */

    private int getInsertIndex(E value) {
        int left = 0;
        int right = this.size;

        while (left < right - 1) {
            int mid = left + ((right - left) / 2);
            int hashObject = 0;

            if (this.container[mid] != null) {
                hashObject = this.container[mid].hashCode();
            }

            if (hashObject < value.hashCode()) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    /**
     * Check array size and rise it when an array length is equal or bigger that default size.
     * Or if it is the last index that going to be hold by passing value.
     * @param nextIndex is next index that going to be checked.
     */

    private void checkSize(int nextIndex) {
        if (this.container.length - 1 == nextIndex && this.container.length >= DEFAULT_ARRAY_SIZE) {
            int nexSize = (this.container.length * 3) / 2 + 1;
            this.container = Arrays.copyOf(this.container, nexSize);
        }
    }

    /**
     * Get array size.
     * @return array size.
     */

    public int getSize() {
        return size;
    }

    /**
     * Use binary search.
     * @param value value that gonna be compare with element in an array.
     * @return true if container has duplicates.
     */

    public boolean checkDuplicate(E value) {

        int left = 0;
        int right = this.size - 1;

        boolean theSame = false;

        while (left <= right) {
            int mid = left + ((right - left) / 2);
            int hashObject = 0;

            if (this.container[mid] != null) {
                hashObject = this.container[mid].hashCode();
            }

            if (hashObject == value.hashCode()) {
                theSame = true;
                break;
            } else if (hashObject < value.hashCode()) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return theSame;
    }

    /**
     * Override iterator method from interface Iterable.
     * @return an instance of Iterator type.
     */

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {

            private int count = 0;
            private int index = 0;

            @Override
            public boolean hasNext() {
                boolean has = false;
                if (count < container.length - (container.length - size)) {
                    while (index < container.length && container[index] != null) {
                        has = true;
                        if (container[count] != null) {
                            break;
                        }
                    }
                }
                return has;
            }

            @Override
            @SuppressWarnings("unchecked")
            public E next() throws NoSuchElementException {
                count++;
                E value;
                if (index < container.length) {
                    value = (E) container[index++];

                    while (value == null) {
                        value = (E) container[index++];
                    }

                } else {
                    throw new NoSuchElementException("NO such element");
                }
                return value;
            }
        };
    }

}
