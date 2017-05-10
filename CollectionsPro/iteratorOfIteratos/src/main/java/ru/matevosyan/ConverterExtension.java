package ru.matevosyan;

import java.util.Iterator;

/**
 * Created by Admin on 08.05.2017.
 */
public class ConverterExtension implements IConvertIterators {
    @Override
    public IteratorConverter convert(Iterator<Iterator<Integer>> it) {
        return new IteratorConverter(it);
    }
}
