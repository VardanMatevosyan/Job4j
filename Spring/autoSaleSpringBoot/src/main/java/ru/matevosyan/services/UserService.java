package ru.matevosyan.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.matevosyan.entity.User;
import ru.matevosyan.repository.UserDataRepository;

/**
 * User service.
 * @param <T> User object type.
 */
@Service
public class UserService<T extends User> {
    private final UserDataRepository<User> userRepository;

    /**
     * Constructor.
     * @param userRepository user repository.
     */
    @Autowired
    public UserService(UserDataRepository<User> userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Save user object.
     * @param user obj.
     */
    public void save(final User user) {
        this.userRepository.save(user);
    }

    /**
     * Find user by name.
     * @param name user.
     * @return user obj.
     */
    public User findByName(final String name) {
        return this.userRepository.findUserByName(name);
    }


}
