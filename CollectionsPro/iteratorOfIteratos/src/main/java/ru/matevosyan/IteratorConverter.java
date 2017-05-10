package ru.matevosyan;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * IteratorConverter class.
 * Created on 08.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class IteratorConverter  implements Iterator {

    private  final Iterator<Iterator<Integer>> iteratorOfIterator;
    private Iterator<Integer> currentIterator = null;

    /**
     * IteratorConverter constructor.
     * @param it for assign to this.iterator and use it in the program.
     */

//    private Iterator<Integer> integerIterator;

    public IteratorConverter(Iterator<Iterator<Integer>> it) {
        this.iteratorOfIterator = it;
    }


    /**
     * Override the next() method to return current number and increment index value.
     * @return simple numbers from an array.
     */

    @Override
    public Integer next() throws NoSuchElementException {
        check();
        if (currentIterator == null) {
            throw new NoSuchElementException();
        } else {
            return currentIterator.next();
        }
    }

    /**
     * Override hasNext() method to check if there are a simple element in array to read.
     * @return true if there are any elements to read, else return false.
     */

    @Override
    public boolean hasNext() {
        check();
        return iteratorOfIterator.hasNext() && currentIterator != null;
    }

    public void check() {

        if (currentIterator != null && currentIterator.hasNext())
        {
            return;
        }

        currentIterator = null;
        while (iteratorOfIterator.hasNext()) {
            Iterator<Integer> nextIterator = iteratorOfIterator.next();
            if (nextIterator.hasNext()) {
                currentIterator = nextIterator;
                break;
            }
        }
    }

}
