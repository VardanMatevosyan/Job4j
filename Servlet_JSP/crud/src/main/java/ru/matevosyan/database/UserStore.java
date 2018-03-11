package ru.matevosyan.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.configuration.Settings;
import ru.matevosyan.model.User;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
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
        createDBTable(CONNECTION);
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
            try (PreparedStatement ps = DBConnection.getDBConnection().prepareStatement(Queries.INSERT.getGuery())) {
                ps.setString(1, user.getName());
                ps.setString(2, user.getLogin());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getPassword());
                ps.setTimestamp(5, user.getCreateDate());
                ps.execute();
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
            try (PreparedStatement ps = DBConnection.getDBConnection().prepareStatement(Queries.UPDATE.getGuery())) {
                ps.setString(1, newName);
                ps.setString(2, newLogin);
                ps.setString(3, name);
                ps.setString(4, login);
                ps.execute();
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
            try (PreparedStatement ps = DBConnection.getDBConnection().prepareStatement(Queries.DELETE.getGuery())) {
                ps.setString(1, name);
                ps.setString(2, login);
                ps.execute();
            } catch (SQLException sqlExp) {
                LOG.warn("Problem with execution query invoke by delete method {}", sqlExp);
            }
        }
    }

    /**
     * get user info from the database to represent to the browser.
     * @param name to return.
     * @param login  to return.
     * @return list with user value.
     */
    public CopyOnWriteArrayList<String> getResult(final String name, final String login) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        synchronized (this) {
            try (PreparedStatement ps = DBConnection.getDBConnection().prepareStatement(Queries.SELECT.getGuery())) {
                ps.setString(1, name);
                ps.setString(2, login);
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    list.add(resultSet.getString("name"));
                    list.add(resultSet.getString("login"));
                    list.add(resultSet.getTimestamp("createDate").toString());
                }

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
        if (DBConnection.isConnected()) {
            try (final Statement statement = connectionDB.createStatement()) {
                statement.execute(Queries.CREATE_TABLE.getGuery());
                LOG.info("Create table user");
            } catch (SQLException sqlExp) {
                LOG.warn(sqlExp.getMessage(), sqlExp);
            }
        } else {
            LOG.warn("can't create table, because of connection problem");
        }
    }

    /**
     * Get sql script from the resource folder file.
     * @param value name of the sql file.
     * @return script as a String.
     */

    public String getSqlScript(String value) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            ClassLoader classLoader = Settings.class.getClassLoader();
            URL urlToSqlFile = classLoader.getResource(value);
            assert urlToSqlFile != null;
            File path = Paths.get(urlToSqlFile.toURI()).toFile();
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            while (reader.ready()) {
                stringBuilder.append(reader.readLine());
            }
            LOG.debug("sql script is ready to return");
        } catch (IOException ioe) {
            LOG.warn("Can't read the file", ioe);
        } catch (URISyntaxException uri) {
            LOG.warn("Can't convert URL to URI", uri);
        }
        return stringBuilder.toString();
    }
}
