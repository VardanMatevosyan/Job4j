package ru.matevosyan;

import java.util.Arrays;

/**
 * FastSimpleSet class.
 * Created on 04.06.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class FastSimpleSet<E> {

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
            this.container[this.container.length - 1 & hash] = value;
            size++;

        }
    }

    private int hash(E value) {
        int h = 0;
        return h = value.hashCode() ^ (h >>> 16);
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

}
