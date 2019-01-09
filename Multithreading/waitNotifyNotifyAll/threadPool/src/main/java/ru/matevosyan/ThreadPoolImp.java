package ru.matevosyan;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * ThreadPoolImp class is own threadPool implementation.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 26.10.2017.
 */

public class ThreadPoolImp {
    private ArrayBlockingQueue<Runnable> blockingQueue;
    private List<ThreadWorkers> listOfThreadWorkers = new ArrayList<>();
    private int sizeOfWorkers = 0;
    private AtomicBoolean isStopped;
    private int maxSizeOfTask = 0;

    /**
     * Constructor for ThreadPoolImp.
     * @param sizeOfThreadWorkers is number of thread that invoke {@link ThreadWorkers#run()} method and doing work.
     * @param maxSizeOfTask is maximum number of task for execute tasks work.
     */

    public ThreadPoolImp(int sizeOfThreadWorkers, int maxSizeOfTask) {
        this.sizeOfWorkers = sizeOfThreadWorkers;
        this.isStopped = new AtomicBoolean(false);
        this.blockingQueue = new ArrayBlockingQueue<>(maxSizeOfTask);
        this.maxSizeOfTask = maxSizeOfTask;

        this.addWork();
        this.runWork();
    }

    /**
     * Add worker to list of threads with common queue of tasks.
     */

    private void addWork() {
        for (int i = 0; i < sizeOfWorkers; i++) {
            this.listOfThreadWorkers.add(new ThreadWorkers(this.blockingQueue));
        }
    }

    /**
     * For each of threads in the listOfThreadWorkers {@link ThreadWorkers#run()} method that doing some work.
     */

    private void runWork() {
        this.listOfThreadWorkers.forEach(ThreadWorkers::start);
    }

    /**
     * Putting task to queue.
     * @param runnable is task.
     * @throws IllegalStateException throw when thread was stopped, and can't put anymore task to queue.
     */

    public void putTaskToQueue(Runnable runnable) throws IllegalStateException {
        if (this.isStopped.get()) {
            throw new IllegalStateException("thread was stopped, and can't put anymore task to queue");
        }

        try {
            this.blockingQueue.put(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("was interrupted");
        }
    }

    /**
     * exit from the {@link ThreadPoolImp#putTaskToQueue(Runnable)} method.
     * And try to stop all threads in the {@link ThreadPoolImp#listOfThreadWorkers}.
     */

    public void stopPuttingTask() {
        this.isStopped.set(true);
        this.listOfThreadWorkers.forEach(ThreadWorkers::stopWorking);
    }

    /**
     * if at list one thread is working return true.
     * @return true if is alie
     */

    public boolean isAlive() {
        boolean isAliveThread = false;

        for (ThreadWorkers worker : this.listOfThreadWorkers) {

            isAliveThread = worker.isStopped();

            if (isAliveThread) {
                break;
            }
        }
        return isAliveThread;
    }

}