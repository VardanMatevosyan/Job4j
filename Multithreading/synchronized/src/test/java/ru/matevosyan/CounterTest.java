package ru.matevosyan;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * Practice with synchronized threads.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 27.09.2017
 */

public class CounterTest {

    /**
     * Start two threads and check couning variable, wich is common variable for both of threads.
     * @throws InterruptedException throw if something goes wrong.
     */

    @Test
    public void whenStartFourThreadsThanGetCountToTwenty() throws InterruptedException {
        Counter counter = new Counter();

        Thread counterThread = counter.new CounterThread(counter, 2);
        Thread counterThread2 = counter.new CounterThread(counter,4);
        Thread counterThread3 = counter.new CounterThread(counter,4);
        Thread counterThread4 = counter.new CounterThread(counter,10);

        counterThread.start();
        counterThread2.start();
        counterThread3.start();
        counterThread4.start();

        counterThread.join();
        counterThread2.join();
        counterThread3.join();
        counterThread4.join();

        assertThat(counter.getCountSpace(), is(20));

    }
}