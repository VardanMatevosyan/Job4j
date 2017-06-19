package ru.matevosyan;

import java.util.Arrays;
import java.util.Iterator;


/**
 * FastSimpleSet class.
 * Created on 04.06.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class FastSimpleSet<E> implements Iterable<E> {

    private Object[] container;
    private static final int DEFAULT_ARRAY_SIZE = 10;
    private int index = 0;
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
            int hash = hash(value);
            this.container[hash] = value;
            size++;

        }
    }

    /**
     * Bucket index.
     * @param value input.
     * @return bucket index.
     */

//    private int hash(E value) {
//        int h = 0;
//        h = value.hashCode();
//        h = h ^ (h >>> 16);
//        h = (this.container.length - 1) & h;
//        return  h;
//    }
    private int hash(E value) {
        int hCode = value.hashCode();
        int hash = hCode % this.container.length;
        return Math.abs(hash);
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
     * Override method {@link DynamicSimpleSet#checkDuplicate(Object)} and use binary search.
     * @param value value that gonna be compare with element in an array.
     * @return true if container has duplicates.
     */


    public boolean checkDuplicate(E value) {

        int left = 0;
        int size = 0;

        for (Object o : this.container) {
            if (o != null) {
                size++;
            }
        }

        int right = size - 1;

        boolean theSame = false;

            while (left <= right) {
                int mid = left + ((right - left) / 2);
                int hashObject = 0;

                if (this.container[mid] != null) {
                    hashObject = this.container[mid].hashCode();
                }

                if (hashObject == value.hashCode()) {
                    theSame = true;
                } else if (hashObject < value.hashCode()) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        return theSame;
    }

    /**
     * Get value.
     * @param value that want to get.
     * @return value type of E.
     */

    @SuppressWarnings("unchecked")
    public E get(E value) {
        int h = hash(value);
        return  (E) this.container[h];
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
                return count < container.length - (container.length - size);
            }

            @Override
            @SuppressWarnings("unchecked")
            public E next() {
                return (E) container[count++];
            }
        };
    }

}
