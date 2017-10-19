package ru.matevosyan;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Admin on 11.10.2017.
 */

public class CustomSynchQueue<E> extends PriorityQueue<E>{

    private Lock readLock = new ReentrantLock();
    private Condition isFull = readLock.newCondition();
    private Condition isEmpty = readLock.newCondition();
    private Object[] files;
    private int count;
    private int indexPut;
    private int indexTake;

    public CustomSynchQueue(int capacity) {
        this.files = new Object[capacity];
    }

    public void addToQueue(E e) throws InterruptedException {
        readLock.lockInterruptibly();
        try {

            while (count == files.length) {
                isFull.await();
            }
            final Object[] files = this.files;
            files[indexPut] = e;
            //Shame on u if y'll do this -> indexPut++ instead of this -> ++indexPut
            if (++indexPut == files.length) {
                indexPut = 0;
            }
            count++;
            isEmpty.signal();
        } finally {
            readLock.unlock();
        }

    }


    public  E removeFromQueue() throws InterruptedException {
        readLock.lockInterruptibly();
        try {
            while (count == 0) {
                isEmpty.await();
            }
            return returnFile();
        } finally {
            readLock.unlock();
        }
    }

    private synchronized E returnFile() {

        final Object[] files = this.files;
        @SuppressWarnings("unchecked")
        E file = (E) files[indexTake];
        files[indexTake] = null;
        //Shame on u if y'll do this -> indexTake++ instead of this -> ++indexTake
        if (++indexTake == files.length) {
            indexTake = 0;
        }
        count--;
        isFull.signal();
        return file;
    }

}
