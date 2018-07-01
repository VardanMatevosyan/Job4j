package ru.matevosyan.repository;

import ru.matevosyan.entity.Address;
import ru.matevosyan.entity.MusicType;
import ru.matevosyan.entity.User;
import ru.matevosyan.entity.UserRole;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * IUserRepository for repository implementation.
 * @param <E1> all User type.
 * @param <E2> all Address type.
 * @param <E3> all User type.
 * @param <E4> all UserRole type.
 * @param <E5> all MusicType type.
 */
public interface IUserRepository<E1 extends User, E2, E3 extends Address, E4 extends UserRole,
        E5 extends MusicType> {
    /**
     * Get list of all Entity.
     * @return map with key as integer and list of entities as value.
     * @throws SQLException exception.
     */
    Map<Integer, List<E2>> getAllUsersEntity() throws SQLException;

    /**
     * add to db User type entity.
     * @param entity User type.
     * @throws SQLException exception.
     */
    void add(E1 entity) throws SQLException;

    /**
     * Find list of Users by entity of Address type.
     * @param entity Address type
     * @return list of Users.
     * @throws SQLException exception.
     */
    List<E1> find(E3 entity) throws SQLException;

    /**
     * Find list of Users by entity of UserRole type.
     * @param entity UserRole type
     * @return list of Users.
     * @throws SQLException exception.
     */
    List<E1> find(E4 entity) throws SQLException;
    /**
     * Find list of Users by entity of MusicType type.
     * @param entity MusicType type
     * @return list of Users.
     * @throws SQLException exception.
     */
    List<E1> find(E5 entity) throws SQLException;
}
