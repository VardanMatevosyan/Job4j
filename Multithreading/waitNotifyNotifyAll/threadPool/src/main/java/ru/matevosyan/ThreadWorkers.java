package ru.matevosyan;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * ThreadWorkers class is own workers class is thread implementation for run threads from the pool.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 26.10.2017.
 */

public class ThreadWorkers extends Thread {
    private BlockingQueue<Runnable> blockingQueue;
    private AtomicBoolean isStopped = null;

    /**
     * Constructor for ThreadWorkers.
     * @param blockingQueue is blocking queue with task.
     */

    public ThreadWorkers(BlockingQueue<Runnable> blockingQueue) {
        this.blockingQueue = blockingQueue;
        this.isStopped = new AtomicBoolean(false);
    }

    /**
     * run method take task from the blocking queue and run it.
     */

    @Override
    public void run() {
        while(!isStopped()) {
            try {
                Runnable worker = blockingQueue.take();
                worker.run();
                System.out.println(" Thread " + this.hashCode() + " run task " + worker.hashCode());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * isStopped method return isStopped value from the AtomicBoolean variable.
     * @return boolean value from the AtomicBoolean variable isStopped.
     */


    protected boolean isStopped() {
        return isStopped.get();
    }

    /**
     * stopWorking method try to stop the thread and exit from the run method.
     */

    protected void stopWorking() {
        this.isStopped.set(true);
        this.interrupt();
    }
}