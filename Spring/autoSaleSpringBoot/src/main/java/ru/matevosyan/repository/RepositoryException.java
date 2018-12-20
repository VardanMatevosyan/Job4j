package ru.matevosyan.repository;

/**
 * Repository exception class represent runtime exception in the system.
 */
public class RepositoryException extends RuntimeException {
    /**
     * Constructor with exception message.
     * @param message exception message.
     */
    public RepositoryException(String message) {
        super(message);
    }
}
