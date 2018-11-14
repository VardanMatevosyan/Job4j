package ru.matevosyan.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.matevosyan.entity.User;

import java.util.Optional;
import java.util.Set;

/**
 * Repository for User entity.
 * @param <T> User and child.
 */
@Repository
public interface UserDataRepository<T extends User> extends CrudRepository<User, Integer> {
    /**
     * find user by name and password for credential.
     * @param name user.
     * @param password user.
     * @return user object.
     */
    Optional<User> findUserByNameAndPassword(String name, String password);

    /**
     *find all user by name.
     * @param name user.
     * @return set of users.
     */
    Set<User> findAllUserByName(String name);
}
