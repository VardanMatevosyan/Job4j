package ru.matevosyan;

import java.util.NoSuchElementException;

/**
 * EvenNumber class.
 * Created on 03.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class EvenNumber implements ArrayIterator {

    private int index = 0;
    private int value = 0;
    private final int[] array;

    /**
     * EvenNumber constructor.
     * @param array an array.
     */

    public EvenNumber(int[] array) {
        this.array = array;
    }

    /**
     * Override the next() method to iterate each element from an array.
     * @return even numbers from an array.
     */

    @Override
    public int next() throws NoSuchElementException {
        int result = 0;
        if (!check() && this.index < this.array.length) {
            throw new NoSuchElementException("No such element exception");
        } else {
            this.index++;
            result = this.array[value];
        }
        return result;
        }

    /**
     * Override hasNext() method to check if eny of elements are in array to read, looking to next() pointer.
     * @return true if there are any elements to read, else return false.
     */

    @Override
    public boolean hasNext() {
        return this.array.length > index && check();
    }

    /**
     * Check if array has even number.
     * @return true if contains even nuber.
     */
    public boolean check() {
        boolean hasEven = false;
        for (int i = this.index; i < this.array.length; i++) {
            if (this.array[i] % 2 == 0) {
                this.value = i;
                hasEven = true;
                break;
            } else {
                this.index++;
            }
        }
        return  hasEven;
    }

}
