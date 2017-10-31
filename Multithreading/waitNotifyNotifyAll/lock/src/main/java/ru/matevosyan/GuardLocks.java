package ru.matevosyan;


import com.google.common.base.Preconditions;

/**
 * GuardLock class use for adding sort of flexibility to synchronization process.
 * @author  Vardan Matevosyan on 30.10.2017.
 * @version 1.0.
 */

public class GuardLocks implements MyLock {

    private static final long NOBODY = -1;
    private long owner;

    /**
     * GuardLocks constructor.
     */

    public GuardLocks() {
        this.owner = NOBODY;
    }

    /**
     * override the lock method in MyLock which is hold lock for the MyLock object.
     * @throws InterruptedException if some threads want to invoke method with owned lock object.
     * @throws IllegalStateException if some threads want to hold owned lock.
     */

    @Override
    public synchronized void lock() throws InterruptedException, IllegalStateException {
        throwIf(!ownerIsCurrentThread(), "The lock has been acquired");
        long threadId = Thread.currentThread().getId();

        while (isLocked()) {
            log(threadId, "Waiting for owner unlock");
            wait();
        }
        this.owner = threadId;
        log(owner, "MyLock acquired ");
    }

    /**
     * Unlock the MyLock object lock.
     * @throws IllegalStateException if some threads want to unlock the lock when lock is not owned.
     */

    @Override
    public synchronized void unlock() throws IllegalStateException {
        throwIf(isLocked(), "Only a locked lock can be unlock.");
        throwIf(ownerIsCurrentThread(), "Only owner can unlock the lock.");
        log(this.owner, "Unlocking owner ");
        this.owner = NOBODY;
        notify();
    }

    /**
     * return value about lock.
     * @return true if object is locked.
     */

    @Override
    public synchronized boolean isLocked() {
        return owner != NOBODY;
    }

    /**
     * Check if owner is current thread.
     * @return true if lock is acquired and owner is current thread with his id.
     */

    private boolean ownerIsCurrentThread() {
        return owner == Thread.currentThread().getId();
    }

    /**
     * throw IllegalStateException with message when ifOwnerIsCurrentThread return true.
     * @param isNotOwner pass to check if owner is current thread or not.
     * @param message out to the console with exception.
     * @throws IllegalStateException  throw if owner is current thread.
     */

    private void throwIf(boolean isNotOwner, String message) throws IllegalStateException {
        Preconditions.checkState(isNotOwner, message);
    }

    /**
     * Out the messages from owner lock.
     * @param owner pass owner lock.
     * @param message pass message to explain what happen when lock and unlock the lock.
     */

    private void log(long owner, String message) {
        System.out.printf("Owner %d - %s \n", owner, message);
    }

}
