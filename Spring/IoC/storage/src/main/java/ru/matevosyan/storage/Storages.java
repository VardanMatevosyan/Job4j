package ru.matevosyan.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ru.matevosyan.memory.Memory;

/**
 * Stoorages for storing data using memory object.
 * @param <T> type of model.
 */
@Component
public class Storages<T> {
    private final Memory memory;

    /**
     * Storagess constructor for injecting memory object.
     * @param memory object.
     */
    @Autowired
    public Storages(@Qualifier("inMemory") Memory memory) {
        this.memory = memory;
    }

    /**
     * Store the model and return back.
     * @param model to store.
     * @return stored model.
     */
    protected T add(T model) {
        this.memory.add(model);
        return model;
    }
}
