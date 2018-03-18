package ru.matevosyan.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.configuration.Settings;
import ru.matevosyan.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class is for manipulate with data in the database.
 * Created on 11.03.2018.
 * @author Matevosyan Vardan.
 * @version 1.0
 */
public class UserStore {
    private static final Connection CONNECTION = DBConnection.getDBConnection();
    private static UserStore ourInstance = new UserStore();
    private static final Settings SETTINGS = Settings.getSettingInstance();
    private static final Logger LOG = LoggerFactory.getLogger(UserStore.class.getName());

    static  {
        createDB();
        LOG.info("create database");
        createDBTable(CONNECTION);
        LOG.info("create table in the database");
    }

    /**
     * Get an instance of UserStore class.
     * @return an instance of UserStore.
     */
    public static UserStore getInstance() {
        return ourInstance;
    }

    /**
     * Insert user to database.
     * @param user to insert to database.
     */
    public void insert(final User user) {
        synchronized (this) {
            Connection connection = DBConnection.getDBConnection();
            try (PreparedStatement ps = connection.prepareStatement(Queries.INSERT.getGuery())) {
                ps.setString(1, user.getName());
                ps.setString(2, user.getLogin());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getPassword());
                ps.setTimestamp(5, user.getCreateDate());
                ps.execute();
                connection.close();
            } catch (SQLException sqlExp) {
                LOG.warn("Problem with getting connection  invoke by create method {}", sqlExp);
            }
        }
    }

    /**
     * Update user in the database.
     * @param name in the database.
     * @param login in the database.
     * @param newName to set.
     * @param newLogin to set.
     */
    public void update(final String name, final String login, final String newName, final String newLogin) {
        synchronized (this) {
            Connection connection = DBConnection.getDBConnection();
            try (PreparedStatement ps = connection.prepareStatement(Queries.UPDATE.getGuery())) {
                ps.setString(1, newName);
                ps.setString(2, newLogin);
                ps.setString(3, name);
                ps.setString(4, login);
                ps.execute();
                connection.close();
            } catch (SQLException sqlExp) {
                LOG.warn("Problem with execution query invoke by update method {}", sqlExp);
            }
        }
    }

    /**
     * delete user from the database.
     * @param name in the database.
     * @param login in the database.
     */
    public void delete(final String name, final String login) {
        synchronized (this) {
            Connection connection = DBConnection.getDBConnection();
            try (PreparedStatement ps = connection.prepareStatement(Queries.DELETE.getGuery())) {
                ps.setString(1, name);
                ps.setString(2, login);
                ps.execute();
                connection.close();
            } catch (SQLException sqlExp) {
                LOG.warn("Problem with execution query invoke by delete method {}", sqlExp);
            }
        }
    }

    /**
     * get user info from the database to represent to the browser.
     * @return list with user value.
     */
    public CopyOnWriteArrayList<User> getResult() {
        CopyOnWriteArrayList<User> list = new CopyOnWriteArrayList<>();
        synchronized (this) {
            Connection connection = DBConnection.getDBConnection();
            try (PreparedStatement ps = connection.prepareStatement(Queries.SELECT.getGuery())) {
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    list.add(new User(resultSet.getString("name"),
                            resultSet.getString("login"),
                            resultSet.getTimestamp("createDate"),
                            resultSet.getString("password"),
                            resultSet.getString("email")));

                }
                connection.close();
            } catch (SQLException sqlExp) {
                LOG.warn("Problem with execution query invoke by get method {}", sqlExp);
            }
        }
        return list;
    }

    /**
     * createDB method used to create database.
     */

    private static void createDB() {
        boolean exist;
        Connection connection;
        LOG.debug("Get connection");

        try {
            DriverManager.getConnection(SETTINGS.getValue("jdbc.url"),
                    SETTINGS.getValue("jdbc.username"), SETTINGS.getValue("jdbc.password"));
            exist = true;
        } catch (SQLException sql) {
            exist = false;
        }

        if (!exist) {
            try {
                connection = DriverManager.getConnection(SETTINGS.getValue("jdbc.defaultUrl"),
                        SETTINGS.getValue("jdbc.username"), SETTINGS.getValue("jdbc.password"));
                Statement statement = connection.createStatement();
                statement.execute(Queries.CREATE_DATABASE.getGuery());
                exist = true;
                connection.close();
                LOG.info("Create database userstore");
            } catch (SQLException sqlEx) {
                LOG.warn("Problem with execution query", sqlEx);
            }
        }
    }


    /**
     * CreateDBTable method used to create table in database when table is not exist.
     * @param connectionDB connection to database.
     */

    private static void createDBTable(final Connection connectionDB) {
        if (DBConnection.isConnected(connectionDB)) {
            try (final Statement statement = connectionDB.createStatement()) {
                statement.execute(Queries.CREATE_TABLE.getGuery());
                LOG.info("Create table user");
            } catch (SQLException sqlExp) {
                LOG.warn(sqlExp.getMessage(), sqlExp);
            }
            try {
                connectionDB.close();
            } catch (SQLException sqlExp) {
                LOG.warn("Can't close connection ", sqlExp);
            }
        } else {
            LOG.warn("can't create table, because of connection problem");
        }
    }

}
