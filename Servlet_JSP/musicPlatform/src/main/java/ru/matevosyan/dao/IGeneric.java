package ru.matevosyan.dao;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * IGeneric DAO interface.
 * @param <Entity> domain model.
 * @param <Key> key for searching.
 */
public interface IGeneric<Entity, Key> {
    /**
     * Get list of domain model.
     * @return list of models.
     * @throws DaoException DAO exception.
     */
    CopyOnWriteArrayList<Entity> getAll() throws DaoException;

    /**
     * Get model by key.
     * @param key to search.
     * @return domain model object.
     * @throws DaoException DAO exception.
     */
    Entity getById(Key key) throws DaoException;

    /**
     * Insert model.
     * @param entity to insert.
     * @throws DaoException DAO exception.
     */
    void insert(Entity entity) throws DaoException;

    /**
     * Update model.
     * @param entity to update.
     * @throws DaoException DAO exception.
     */
    void update(Entity entity) throws DaoException;
    /**
     * Delete model.
     * @param entity to delete.
     * @throws DaoException DAO exception.
     */
    void delete(Entity entity) throws DaoException;
}
