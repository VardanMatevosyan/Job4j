package ru.matevosyan;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Practice with synchronized threads.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 27.09.2017
 */

@ThreadSafe
public final class Counter {

    @GuardedBy("this")
    private int counting = 0;

    /**
     * Constructor by default.
     */

    public Counter() {
    }

    /**
     * increase the counter.
     * @param value is value.
     */

    public void increase(int value) {
        synchronized (this) {
            counting += value;
        }
    }

    /**
     * Getter for counter.
     *
     * @return counter.
     */

    public int getCountSpace() {
        synchronized (this) {
            return counting;
        }
    }

    /**
     * Countered thread.
     */
    public final class CounterThread extends Thread {
        private Counter counter;
        private final int value;

        /**
         * Constructor.
         *
         * @param counter variable to increase the value.
         * @param value    is to which value increase the variable.
         */

        public CounterThread(final Counter counter, final int value) {
            this.counter = counter;
            this.value = value;
        }

        /**
         * override run to invoke increase method.
         */

        @Override
        public void run() {
            counter.increase(value);
        }

    }
}
