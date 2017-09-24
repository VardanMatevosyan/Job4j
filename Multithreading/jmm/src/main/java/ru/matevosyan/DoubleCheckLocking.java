package ru.matevosyan;

/**
 * test threads with solution problem DoubleCheckLockingTest.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 21.09.2017
 */

public class DoubleCheckLocking {

    private volatile static DoubleCheckLocking doubleCheckLocking;
    private String string;

    /**
     * Constructor.
     * @param string pass thread name.
     */

    public DoubleCheckLocking(String string) {
        this.string = string;
    }

    /**
     * Getter for string.
     * @return string.
     */

    public String getString() {
        return string;
    }

    /**
     * Getter for DoubleCheckLocking instance.
     * @return DoubleCheckLocking instance.
     * @throws InterruptedException if methods throw interrupted exception.
     */

    public DoubleCheckLocking getDoubleCheckLockingInstance() throws InterruptedException {
        Thread.sleep(2000);
        if (doubleCheckLocking == null) {
            synchronized (DoubleCheckLocking.class) {
                if (doubleCheckLocking == null) {
                    doubleCheckLocking = new DoubleCheckLocking(string);
                }
            }
        }
        return doubleCheckLocking;
    }
}
