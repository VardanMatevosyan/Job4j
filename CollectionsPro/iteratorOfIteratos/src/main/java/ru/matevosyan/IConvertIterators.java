package ru.matevosyan;

import java.util.Iterator;

/**
 * Iterators class.
 * Created on 08.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public interface IConvertIterators {

    /**
     * Convert interator of iterators to one simple iterator of Integer values.
     * @param it interator of iterators of Integers.
     * @return IteratorConvertor to get values.
     */
    IteratorConverter convert(Iterator<Iterator<Integer>> it);

}