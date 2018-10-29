package ru.matevosyan.persistens.repository;

import ru.matevosyan.entity.Role;

/**
 * Interface for Role  repository.
 * @param <E> object of Role type.
 */
public interface IRole<E extends Role> {
    /**
     * Save role to the database.
     * @throws RepositoryException exception if get some problem during saving a role.
     */
    void add() throws RepositoryException;
}
