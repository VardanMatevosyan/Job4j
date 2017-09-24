package ru.matevosyan;

/**
 * Practice with threads problem race condition.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 21.09.2017
 */

public class ThreadsProblemRace {

    private int counting = 0;
    private int countingSynch = 0;


    /**
     * Default constructor.
     */

    public ThreadsProblemRace() {}


    /**
     * add to counting.
     */

    public void add() {
        this.counting++;
    }

    /**
     * subtraction to counting.
     */

    public void sub() {
        this.counting--;
    }

    /**
     * add to countingSynch which is synchronized.
     */

    public void addValSynchronized() {
        synchronized (this) {
            this.countingSynch++;
        }
    }

    /**
     * subtraction from countingSynch which is synchronized.
     */

    public void subValSynchronized() {
        synchronized (this) {
            this.countingSynch--;
        }
    }

    /**
     * Getter for counting variable.
     * @return counting.
     */

    public int getCounting() {
        return this.counting;
    }

    /**
     * Getter for countingSynch variable which is synchronized.
     * @return countingSynch.
     */

    public int getCountingSynch() {
        return countingSynch;
    }

    /**
     * Adder class to invoke add method for counting and addValSynchronized for countingSynch which is synchronized.
     */

    public final class Adder implements Runnable {

        /**
         * default constructor for Adder.
         */

        public Adder() {}

        /**
         * Override run to run Adder.
         */

        @Override
        public void run() {
            for (int i = 0; i < 50000000; i++) {
                add();
                addValSynchronized();
            }
        }

    }

    /**
     * Suber class to invoke sub method for counting and subValSynchronized for countingSynch which is synchronized.
     */

    public final class Suber implements Runnable {

        /**
         * default constructor for Suber.
         */

        public Suber() {
        }

        /**
         * Override run to run Suber.
         */

        @Override
        public void run() {
            for (int i = 0; i < 50000000; i++) {
                sub();
                subValSynchronized();
            }
        }
    }



}
