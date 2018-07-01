package ru.matevosyan.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.database.DBConnection;
import ru.matevosyan.database.Queries;
import ru.matevosyan.entity.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Roles DAO class.
 */
public class Roles implements IGeneric<UserRole, Integer> {
    private static final Logger LOG = LoggerFactory.getLogger(Roles.class.getName());

    /**
     * Get role by id.
     * @param name role name.
     * @return int values which is roles id.
     */
    public int getRoleIdByName(String name) {
        int roleId = 0;
        try (Connection connection = DBConnection.getInstance().getDBConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.GET_ROLE_ID.getQuery())) {
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                roleId = resultSet.getInt("id");
            }
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.warn("Problem with getting connection  invoke by getRoleIdByName method {}", sqlExp);
        }
        return roleId;
    }

    /**
     * get all roles info from the database to represent to the browser.
     * @return list with roles value.
     */
    @Override
    public CopyOnWriteArrayList<UserRole> getAll() throws DaoException {
        CopyOnWriteArrayList<UserRole> list = new CopyOnWriteArrayList<>();
        try (Connection connection = DBConnection.getInstance().getDBConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.SELECT_ROLES.getQuery())) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                list.add(new UserRole(resultSet.getInt("id"), resultSet.getString("name")));
            }
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.warn("Problem with execution query invoke by getAll method {}", sqlExp);
        }
        return list;
    }

    @Override
    public UserRole getById(Integer id) throws DaoException {
        String name;
        UserRole role = new UserRole(-1, "");
        try (Connection connection = DBConnection.getInstance().getDBConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.GET_ROLE_BY_ID.getQuery())) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                name = resultSet.getString("name");
                role = new UserRole(id, name);
            }
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.warn("Problem with getting connection  invoke by getById method {}", sqlExp);
        }
        return role;
    }

    @Override
    public void insert(UserRole role) throws DaoException {
        try (Connection connection = DBConnection.getInstance().getDBConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.INSERT_ROLE.getQuery())) {
            ps.setString(1, role.getName());
            ps.execute();
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.error("Problem with getting connection  invoke by create method {}", sqlExp);
            throw new DaoException("Problem with insertion role");
        }
    }

    @Override
    public void update(UserRole userRole) throws DaoException {
        try (Connection connection = DBConnection.INSTANCE.getDBConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.UPDATE_ROLE.getQuery())) {
            ps.setString(1, userRole.getName());
            ps.setInt(2, userRole.getId());
            ps.execute();
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.error("Problem with getting connection invoke by update method in userRole dao {}", sqlExp);
            throw new DaoException("Problem with updating userRole to DB");
        }
    }

    @Override
    public void delete(UserRole userRole) throws DaoException {
        try (Connection connection = DBConnection.INSTANCE.getDBConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.DELETE_ROLE.getQuery())) {
            ps.setInt(1, userRole.getId());
            ps.execute();
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.error("Problem with getting connection invoke by delete method in userRole dao {}", sqlExp);
            throw new DaoException("Problem with deleting user role type to DB");
        }
    }

}
