package ru.matevosyan;

import java.util.NoSuchElementException;

/**
 * EvenNumber class.
 * Created on 03.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class NaturalNumber implements NaturalNumberIterator {

    private int index = 0;
    private final int[] array;

    /**
     * EvenNumber constructor.
     * @param array an array.
     */

    public NaturalNumber (int[] array) {
        this.array = array;
    }

    /**
     * Override the next() method to return current number and increment index value.
     * @return simple numbers from an array.
     */

    @Override
    public int next() throws NoSuchElementException {
        int result = 0;
        if (this.index >= this.array.length) {
            throw new NoSuchElementException("No simple number");
        }

        try {
            while (this.array.length > this.index) {
                if (isSimpleOrNot()) {
                    result = this.array[this.index++];
                    break;
                }
                index++;
            }

        } catch (ArrayIndexOutOfBoundsException out) {
            throw  new NoSuchElementException("No such element");
        }

        return result;
    }

    /**
     * Override hasNext() method to check if there are a simple element in array to read.
     * @return true if there are any elements to read, else return false.
     */

    @Override
    public boolean hasNext() {
        boolean isNext = false;

        while (!isNext && this.array.length - 1 > this.index) {
            isNext = isSimpleOrNot() || checkNextIndex();
        }

        return isNext;
    }

    /**
     * Check if next element is simple and increment index.
     * @return true if next element is simple.
     */

    private boolean checkNextIndex() {
        boolean check = true;
        while (this.array.length > this.index) {
            this.index++;
            if (this.array.length > this.index) {
                check = isSimpleOrNot();
                break;
            }

        }
        return check;
    }

    /**
     * Check if umber is simple.
     * @return true if number is simple.
     */

    private boolean isSimpleOrNot() {

        boolean isSimple = true;

        if (this.array[this.index] == 1) {
            isSimple = false;
        }

        for (int i = 2; i <= Math.sqrt(this.array[this.index]); i++) {
            if (this.array[this.index] % i == 0) {
                isSimple = false;
                break;
            }
        }

        return isSimple;
    }
}
