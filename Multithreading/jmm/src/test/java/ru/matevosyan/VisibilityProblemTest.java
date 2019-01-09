package ru.matevosyan;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Test with threads problem visibility.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 21.09.2017
 */

public class VisibilityProblemTest {

    /**
     * whenStartThreadsThanCheckTheOutputValue to test volatile modifier use output to console.
     * and output count variable.
     * @throws InterruptedException  if methods throw interrupted exception.
     */

    @Ignore
    @Test
    public void whenStartThreadsThanCheckTheOutputValue() throws InterruptedException {
        System.out.println("Visibility problem");

        VisibilityProblem.Looper looper = new VisibilityProblem.Looper();
        VisibilityProblem.Listener listener = new VisibilityProblem.Listener();

        looper.start();
        listener.start();

        looper.join();
        listener.join();

        System.out.println("Count to " + VisibilityProblem.getCountingVal());

    }
}