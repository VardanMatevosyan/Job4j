package ru.matevosyan;


import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * GuardLock class use for adding sort of flexibility to synchronization process.
 * @author  Vardan Matevosyan on 30.10.2017.
 * @version 1.0.
 */

public class GuardLocksTest {
    private MyLock myLock = new GuardLocks();
    private AtomicInteger incAndDecValue = new AtomicInteger();

    /**
     * Two threads increment and decrement using MyLock, one value and, at the end, value must be zero.
     */

    @Test
    public void whenFirstThreadIncrementSecondDecrementValueInCriticalSectionThanGetResultZero() {
        int expected = 0;

        Thread incrementThreadForLock = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    myLock.lock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                increment(incAndDecValue);

                myLock.unlock();
            }
        });

        Thread decrementThreadForLock = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    myLock.lock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                decrement(incAndDecValue);

                myLock.unlock();
            }
        });


        incrementThreadForLock.start();
        decrementThreadForLock.start();

        try {
            incrementThreadForLock.join();
            decrementThreadForLock.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("\nValue for decrement and increment is %d", incAndDecValue.get());

        assertThat(expected, is(incAndDecValue.get()));

    }

    /**
     * increment value.
     * @param incrementer value for incrementing.
     */

    private void increment(AtomicInteger incrementer) {
        incrementer.incrementAndGet();
    }

    /**
     * decrement value.
     * @param incrementer value for decrementing.
     */

    private void decrement(AtomicInteger incrementer) {
        incrementer.decrementAndGet();
    }

}
