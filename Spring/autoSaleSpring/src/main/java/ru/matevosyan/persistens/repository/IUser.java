package ru.matevosyan.persistens.repository;

import ru.matevosyan.entity.User;

import java.util.Optional;

/**
 * Interface for User repository.
 * @param <E> class of User type.
 */
public interface IUser<E extends User> {

    /**
     * Check if user is in the system (database).
     * @param s1 user name.
     * @param s2 user password.
     * @return true if user is in the system, else false.
     */
    boolean userCredential(String s1, String s2);

    /**
     * get user from the memory.
     * @param name user name.
     * @return user of Optional type.
     */
    Optional<User> loadCurrentUser(String name);

    /**
     * save root user to the database.
     * @throws RepositoryException  exception if get some problem during saving a root user.
     */
    void addRoot() throws RepositoryException;

    /**
     * save user to the system (database).
     * @param user object itself.
     * @throws RepositoryException exception if get some problem during saving a user.
     */
    void add(E user) throws RepositoryException;

    /**
     *  Get last user added offer.
     * @return Offer last added user offer.
     * @throws RepositoryException if get some problem during getting a offer image path.
     */
    String getLastAddedOfferImagePath() throws RepositoryException;
}
