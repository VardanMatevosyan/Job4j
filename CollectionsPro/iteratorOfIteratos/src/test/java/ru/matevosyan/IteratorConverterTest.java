package ru.matevosyan;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Admin on 08.05.2017.
 */
public class IteratorConverterTest {

    /**
     * when pass iterator of iterators then check return iterator of itegers.
     */
    @Test
    public void whenPassIteratorOfIteratorThenCheckReturnIteratorOfInteger() {
        ArrayList<Integer> firstList = new ArrayList<>();
        firstList.add(4);
        firstList.add(7);
        firstList.add(9);

        ArrayList<Integer> secondList = new ArrayList<>();
        secondList.add(3);
        secondList.add(12);
        secondList.add(32);

        ArrayList<Integer> thirdLisr = new ArrayList<>();
        thirdLisr.add(332);
        thirdLisr.add(122);
        thirdLisr.add(312);

        Iterator<Integer> firstIterator = firstList.iterator();
        Iterator<Integer> secondIterator = secondList.iterator();
        Iterator<Integer> thirdIterator = thirdLisr.iterator();


        List<Integer> listOfInt = new ArrayList<>();
        listOfInt.addAll(firstList);
        listOfInt.addAll(secondList);
        listOfInt.addAll(thirdLisr);


        Iterator<Iterator<Integer>> itOfIter = Arrays.asList(firstIterator, secondIterator, thirdIterator).iterator();

        ConverterExtension convertExtension = new ConverterExtension();
        IteratorConverter iteratorOfInt = convertExtension.convert(itOfIter);
        int i = 0;

        while (iteratorOfInt.hasNext()) {

            assertThat(iteratorOfInt.next(), is(listOfInt.get(i++)));

        }
    }
}