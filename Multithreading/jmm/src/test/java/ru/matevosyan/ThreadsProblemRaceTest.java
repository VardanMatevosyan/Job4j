package ru.matevosyan;

import org.junit.Test;

/**
 * Test with threads problem race condition.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 21.09.2017
 */

public class ThreadsProblemRaceTest {

    /**
     * Start two threads and check results with synchronized and with it.
     * @throws InterruptedException  if methods throw interrupted exception.
     */

    @Test
        public void whenStartThreadsThanCheckProblemResultAndSynchronizedResult() throws InterruptedException {
        ThreadsProblemRace threadsProblemRace = new ThreadsProblemRace();
        ThreadsProblemRace.Adder adder = threadsProblemRace.new Adder();
        ThreadsProblemRace.Suber suber = threadsProblemRace.new Suber();

        Thread adderThreads = new Thread(adder);
        Thread suberThreads = new Thread(suber);

        adderThreads.start();
        suberThreads.start();

        adderThreads.join();
        suberThreads.join();

        System.out.println("Race condition");
        System.out.println(threadsProblemRace.getCounting());
        System.out.println("Synchronized");
        System.out.println(threadsProblemRace.getCountingSynch());

    }


}