package ru.matevosyan;

import org.junit.Test;

/**
 * ThreadPoolTest class is for testing own threadPool implementation.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 26.10.2017.
 */

public class ThreadPoolImpTest {

    /**
     * create 5 thread workers and put to the poolOfThreads.
     * Then for each out to the console message with number iterating.
     */

    @Test
    public void whenStartThreadPoolWorkThanCheckTheOutput() {
        ThreadPoolImp poolImp = new ThreadPoolImp(2, 4);


        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.print("Task 1 is doing something");
            }
        };

        Runnable runnable3 = new Runnable() {
            @Override
            public void run() {
                System.out.print("Task 3 is doing something");
            }
        };

        Runnable runnable4 = new Runnable() {
            @Override
            public void run() {
                System.out.print("Task 4 is doing something");
            }
        };



        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                System.out.print("Task 2 is doing something");
            }
        };

        try {
            poolImp.putTaskToQueue(runnable1);
            Thread.sleep(100);
            poolImp.putTaskToQueue(runnable2);
            Thread.sleep(100);
            poolImp.putTaskToQueue(runnable3);
            Thread.sleep(100);
            poolImp.putTaskToQueue(runnable4);
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Try to stop poolThread workers when at least one thread is alive (working)
     */

    @Test
    public void whenStartThreadPoolWorkThanTryToStopIfThreadIfThreadIsAliveCheckTheOutput() {
        ThreadPoolImp poolImp = new ThreadPoolImp(2, 5);

        int i = 0;

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < 500000) {
                        System.out.println("Task is doing something" + i);
                    i++;
                    }
            }
        };

        while (i < 5) {
            try {
                poolImp.putTaskToQueue(runnable1);
            } catch (IllegalStateException e) {
                e.getMessage();
            }
            i++;


        }

        if (poolImp.isAlive()) {
            poolImp.stopPuttingTask();
        }

    }
}