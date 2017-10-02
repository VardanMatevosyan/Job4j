package ru.matevosyan;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * SynchronizedDynamicArrayListTest to test synchronized DynamicArrayList.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 27.09.2017
 */

public class SynchronizedDynamicArrayListTest {

    /**
     * Try to add 10 First string after, 10 times from second thread add Second.
     * Get the 1 element, add wait form First and get from second 10th, 19 and get Second.
     * @throws InterruptedException throw interrupted exception.
     */

    @Test
    public void whenAddStringThanGetTheSameStringInTHeList() throws InterruptedException {

        SynchronizedDynamicArrayList<String> synchronizedDynamicArrayList = new SynchronizedDynamicArrayList<>();

        Thread first = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    synchronizedDynamicArrayList.add("First");
                }
            }
        });

        Thread second = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    synchronizedDynamicArrayList.add("Second");
                }
            }
        });

        first.start();
        Thread.sleep(10);
        second.start();

        first.join();
        second.join();

        assertThat(synchronizedDynamicArrayList.get(1), is("First"));
        assertThat(synchronizedDynamicArrayList.get(19), is("Second"));

    }

    /**
     * Add from first thread First and get it from second thread.
     * @throws InterruptedException throw interrupted exception.
     */

    @Test
    public void whenAddStringAfterDeleteTheSameStringInTheListAndGetIt() throws InterruptedException {

        SynchronizedDynamicArrayList<String> synchronizedDynamicArrayList = new SynchronizedDynamicArrayList<>();
        final String[] actualStr = new String[10];

        Thread first = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    synchronizedDynamicArrayList.add("First");
                }
            }
        });

        Thread second = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    actualStr[i] = synchronizedDynamicArrayList.get(i);
                }
            }
        });

        first.start();
        second.start();

        first.join();
        second.join();

        assertThat(actualStr[9], is("First"));

    }

    /**
     * Add from the first thread 3 times First string after in second thread iterate nad get the value.
     * It should be First.
     * @throws InterruptedException throw interrupted exception.
     */

    @Test
    public void whenAddStringAfterIterateThrough() throws InterruptedException {

        SynchronizedDynamicArrayList<String> synchronizedDynamicArrayList = new SynchronizedDynamicArrayList<>();
        final String[] actualStr = new String[3];


        Thread first = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    synchronizedDynamicArrayList.add("First");
                }
                synchronizedDynamicArrayList.setFinish(true);

            }
        });

        Thread second = new Thread(new Runnable() {

            @Override
            public void run() {
                Iterator<String> itr = synchronizedDynamicArrayList.iterator();
                for (int i = 0; i < 3; i++) {
                    if (itr.hasNext()) {
                        actualStr[i] = itr.next();
                    } else {
                        break;
                    }
                }
            }
        });

        first.start();
        second.start();

        first.join();
        second.join();


        assertThat(actualStr[1], is("First"));


    }

}