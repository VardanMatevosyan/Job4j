package ru.matevosyan;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;


/**
 * FastSimpleSetTest class.
 * Created on 04.06.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class FastSimpleSetTest {

    /**
     * Create whenCreateTwoObjectAndAddValuesThanCheckWhoAddedFasterToTheContainer() to check hwo is faster.
     */

    @Test public void whenCreateTwoObjectAndAddValuesThanCheckWhoAddedFasterToTheContainer() {
        FastSimpleSet<Integer> fastSimpleSet = new FastSimpleSet<>();
        DynamicSimpleSet<Integer> dynamicSimpleSet = new DynamicSimpleSet<>();

        long fastSetTimeBegin = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            fastSimpleSet.add(i);
        }

        long fastSetTimeEnd = System.currentTimeMillis();


        long dynamicSimpleSetTimeBegin = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            dynamicSimpleSet.add(i);
        }

        long dynamicSimpleSetTimeEnd = System.currentTimeMillis();

        long fastTime = fastSetTimeEnd - fastSetTimeBegin;
        long dynamicSimpleTime = dynamicSimpleSetTimeEnd - dynamicSimpleSetTimeBegin;

        assertTrue(fastTime < dynamicSimpleTime);


    }

    /**
     * whenCreateObjectAndAddValuesThanCheckSizeAndIterator for testing iterator and check size.
     * when insert duplicates.
     */

    @Test public void whenCreateObjectAndAddValuesThanCheckSizeAndIterator() {
        FastSimpleSet<String> fastSimpleSet = new FastSimpleSet<>();


            fastSimpleSet.add("---qqqqqqqqqqqqqq");
            fastSimpleSet.add("---qqqqqqqqqqqqqq");
            fastSimpleSet.add("444ergw3grger");
            fastSimpleSet.add("papgerg34ga");
            fastSimpleSet.add("@gerge4g3g@@");
            fastSimpleSet.add(">ergerge>>");
            fastSimpleSet.add("~~~|rgerhrtj|ooo");
            fastSimpleSet.add("~~~|rgerhrtj|ooo");
            fastSimpleSet.add("~~~|rgerhrtj|ooo");
            fastSimpleSet.add("~~~|rgerhrtj|ooo");

        Iterator<String> itr = fastSimpleSet.iterator();

        String last = null;
        while(itr.hasNext()) {
            last = itr.next();
        }

        assertThat(last, is("@gerge4g3g@@"));
        assertThat(fastSimpleSet.getSize(), is(6));
    }


}