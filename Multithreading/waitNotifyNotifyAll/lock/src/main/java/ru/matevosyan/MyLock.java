package ru.matevosyan;

/**
 * MyLock interface for define all methods to use in the methods to lock and unlock enter.
 * @author  Vardan Matevosyan on 30.10.2017.
 * @version 1.0.
 */

public interface MyLock {

    /**
     * Hold lock for the MyLock object.
     * @throws InterruptedException if some threads want to invoke method with owned lock object.
     * @throws IllegalStateException if some threads want to hold owned lock.
     */

    void lock() throws InterruptedException, IllegalStateException;

    /**
     * Unlock the MyLock object lock.
     * @throws IllegalStateException if some threads want to unlock the lock when lock is not owned.
     */

    void unlock() throws IllegalStateException;

    /**
     * return value about lock.
     * @return true if object is lock.
     */

    boolean isLocked();
}
