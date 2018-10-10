package ru.matevosyan.memory;

/**
 * Memory for storage.
 * @param <T> type to store.
 */
public interface Memory<T> {

    /**
     * add model to the memory.
     * @param model type to save.
     */
    void add(T model);
}
