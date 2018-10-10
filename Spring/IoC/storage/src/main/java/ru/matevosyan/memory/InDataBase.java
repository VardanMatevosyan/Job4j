package ru.matevosyan.memory;

import org.springframework.stereotype.Component;
import ru.matevosyan.utils.SessionManager;

/**
 * In database component (bean) to save data in the database using hibernate.
 * @param <D>
 */
@Component
public class InDataBase<D> implements Memory<D> {
    private static final SessionManager SESSION_MANAGER = SessionManager.TRANSACTION;

    @Override
    public void add(D model) {
        SESSION_MANAGER.useAndReturn(session -> session.save(model));
    }
}
