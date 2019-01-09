package ru.matevosyan;

/**
 * Practice with threads problem visibility.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 21.09.2017
 */

public class VisibilityProblem {
    private static int countingVal = 0;

    /**
     * getter for countingVal variable.
     * @return countingVal.
     */

    public static int getCountingVal() {
        return countingVal;
    }

    /**
     * Looper class to start thread which increase variable countingVal.
     */

    public static class Looper extends Thread {

        /**
         * Override run to run Looper thread and increase countingVal variable.
         */

        @Override
        public void run() {
            while (VisibilityProblem.countingVal < 5) {
                VisibilityProblem.countingVal++;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("increase " + VisibilityProblem.countingVal);
            }
        }
    }

    /**
     * Listener.
     */
    public static class Listener extends Thread {

        /**
         * Override run to run Listener thread read countingVal variable and print if it less than countingVal.
         */

        @Override
        public void run() {
            int localCount = VisibilityProblem.countingVal;
            while (localCount < 5) {
                if (localCount != VisibilityProblem.countingVal) {
                    System.out.println("It is " + localCount + " now");
                    localCount = VisibilityProblem.countingVal;
                }
            }
        }
    }
}
