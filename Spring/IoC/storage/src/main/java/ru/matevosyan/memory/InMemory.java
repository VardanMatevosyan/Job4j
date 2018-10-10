package ru.matevosyan.memory;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * In memory component (bean) to save data in the list in memory.
 * @param <M> type of model.
 */
@Component
public class InMemory<M> implements Memory<M> {

    private final List<M> users;

    /**
     * Memory default constructor with init list.
     */
    public InMemory() {
        this.users = new ArrayList<>();
    }

    @Override
    public void add(M model) {
        this.users.add(model);
    }
}
