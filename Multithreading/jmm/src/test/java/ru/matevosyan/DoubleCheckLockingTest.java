package ru.matevosyan;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Practice with threads problem DoubleCheckLockingTest.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 21.09.2017
 */
public class DoubleCheckLockingTest {
    private String thread1 = "";
    private String thread2 = "";

    /**
     * whenStartTwoThreadsThanCheckIfGetStringIsTheSame check two string field in the each thread and.
     * variable must to be the same.
     * @throws InterruptedException if some thread methods throw exception.
     */

    @Test
    public void whenStartTwoThreadsThanCheckIfGetStringIsTheSame() throws InterruptedException {
        Thread first = new Thread(new Runnable() {

            /**
             * run DoubleCheckLocking thread and pass "Thread - 1" after get it.
             * from the method getDoubleCheckLockingInstance.
             */

            @Override
            public void run() {
                DoubleCheckLocking doubleCheckLockingFirstThread = new DoubleCheckLocking("Thread - 1");
                DoubleCheckLocking lockingThreadFirst = null;
                try {
                    lockingThreadFirst = doubleCheckLockingFirstThread.getDoubleCheckLockingInstance();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                assert lockingThreadFirst != null;
                thread1 = lockingThreadFirst.getString();
                System.out.println("Thread - 1 - First ~~~ " + (thread1));

            }
        });

        Thread second = new Thread(new Runnable() {

            /**
             * run DoubleCheckLocking thread and pass "Thread - 2" after get it.
             * from the method getDoubleCheckLockingInstance.
             */

            @Override
            public void run() {
                DoubleCheckLocking doubleCheckLockingSecondThread = new DoubleCheckLocking("Thread - 2");
                DoubleCheckLocking lockingThreadSecond = null;
                try {
                    lockingThreadSecond = doubleCheckLockingSecondThread.getDoubleCheckLockingInstance();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                assert lockingThreadSecond != null;
                thread2 = lockingThreadSecond.getString();
                System.out.println("Thread - 2 - Second ~~~ " + (thread2));
            }
        });


        first.start();
        second.start();

        first.join();
        second.join();


        assertThat(thread1.equals(thread2), is(true));

    }
}