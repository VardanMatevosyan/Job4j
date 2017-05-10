package ru.matevosyan;

import java.util.Iterator;

/**
 * Iterators class.
 * Created on 08.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public interface IConvertIterators {

    IteratorConverter convert(Iterator<Iterator<Integer>> it);

}