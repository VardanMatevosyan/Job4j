package ru.matevosyan.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.matevosyan.models.User;

/**
 * UserStorage for deal with user model.
 * @param <T>
 */
@Component
public class UserStorage<T extends User> {
    private final Storages storage;

    /**
     * UserStorage constructor.
     * Inject Storages.
     * @param storage use to assign to store data.
     */
    @Autowired
    public UserStorage(Storages storage) {
        this.storage = storage;
    }

    /**
     * save user model to the memory using storages.
     * @param user model.
     * @return saved model.
     */
    public User add(User user) {
        return (User) this.storage.add(user);
    }
}
