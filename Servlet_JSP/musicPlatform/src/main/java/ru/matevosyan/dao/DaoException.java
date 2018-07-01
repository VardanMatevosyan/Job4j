package ru.matevosyan.dao;

/**
 * DaoException for DAO layer exception.
 */
public class DaoException extends Exception {
    /**
     * Dao constructor with message.
     * @param message msg about exception.
     */
    public DaoException(String message) {
        super(message);
    }
}
