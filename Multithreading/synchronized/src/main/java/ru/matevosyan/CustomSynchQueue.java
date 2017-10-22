package ru.matevosyan;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * CustomSynchQueue for synchronize adding and taking files from common queue.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 03.10.2017
 */

public class CustomSynchQueue<E> extends PriorityQueue<E>{

    private Lock readLock = new ReentrantLock();

    private Condition isFull = readLock.newCondition();
    private Condition isEmpty = readLock.newCondition();
    private Object[] files;
    private int count;
    private int indexPut;
    private int indexTake;

    /**
     * Constructor with initialization capacity.
     * @param capacity for files holder.
     */

    public CustomSynchQueue(int capacity) {
        this.files = new Object[capacity];
    }

    /**
     * add file to files holder.
     * @param e is the adding value, in our case is file.
     */

    public void addToQueue(E e) {
        try {
            readLock.lockInterruptibly();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        try {

            while (count == files.length) {
                try {
                    isFull.await();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
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

    /**
     * wait if nothing to return and return returnFile method result.
     * @return removing value, in our case is file.
     */

    public  E removeFromQueue() {
        try {
            readLock.lockInterruptibly();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            while (count == 0) {
                try {
                    isEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return returnFile();
        } finally {
            readLock.unlock();
        }
    }

    /**
     * synchronously remove file from file holder and return that file.
     * @return removing value, in our case is file.
     */

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



