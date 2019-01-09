
package ru.matevosyan;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

    /**
     * SynchronizedBlockingQueue for synchronize adding and taking money from common queue.
     * @author Matevosyan Vardan
     * @version 1.0
     * created on 05.11.2017
     * @param <E> type.
     */

    public class SynchronizedBlockingQueue<E> extends PriorityQueue<E> {
        private Lock readLock = new ReentrantLock();
        private Condition isFull = readLock.newCondition();
        private Condition isEmpty = readLock.newCondition();
        private Object[] moneyHolder;
        private int count;
        private int indexPut;
        private int indexTake;

        /**
         * Constructor with initialization capacity.
         * @param capacity for files holder.
         */

        public SynchronizedBlockingQueue(int capacity) {
            this.moneyHolder = new Object[capacity];
        }

        /**
         * add value to holder.
         * @param e is the adding value, in our case is money.
         */

        public void addToQueue(E e) {
            try {
                readLock.lockInterruptibly();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            try {

                while (count == moneyHolder.length) {
                    try {
                        isFull.await();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
                final Object[] moneyHolderInTheMethod = this.moneyHolder;
                moneyHolderInTheMethod[indexPut] = e;
                //Shame on u if y'll do this -> indexPut++ instead of this -> ++indexPut
                if (++indexPut == moneyHolderInTheMethod.length) {
                    indexPut = 0;
                }
                count++;
                isEmpty.signal();
            } finally {
                readLock.unlock();
            }

        }

        /**
         * wait if nothing to return and return returnRemovingValue method result.
         * @return removing value, in our case is money.
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
                return returnRemovingValue();
            } finally {
                readLock.unlock();
            }
        }

        /**
         * synchronously remove money from money holder and return that file.
         * @return removing value, in our case is money.
         */

        private synchronized E returnRemovingValue() {

            final Object[] moneyHolderInTheMethod = this.moneyHolder;
            @SuppressWarnings("unchecked")
            E money = (E) moneyHolderInTheMethod[indexTake];
            moneyHolderInTheMethod[indexTake] = null;
            //Shame on u if y'll do this -> indexTake++ instead of this -> ++indexTake
            if (++indexTake == moneyHolderInTheMethod.length) {
                indexTake = 0;
            }
            count--;
            isFull.signal();
            return money;
        }

    }





