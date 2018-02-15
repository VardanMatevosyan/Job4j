package ru.matevosyan.dataBase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.configuration.Settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class is for connection to database.
 * Created on 26.01.2018.
 * @since 1.0
 * @author Matevosyan Vardan.
 * @version 1.0
 */


public class ConnectionDB {
    private final String dbUrl;
    private final String dbUserName;
    private final String dbPassword;
    private final String dbDriver;
    private static boolean isConnected;
    private static Connection connection;
    private static final Logger LOG = LoggerFactory.getLogger(ConnectionDB.class.getName());
    private static final Settings SETTINGS = Settings.getSettingInstance();

    /**
     * ConnectionDB constructor.
     * Loading all connection settings to connect with database.
     */

    public ConnectionDB() {
        this.dbUrl = SETTINGS.getValue("jdbc.url");
        this.dbUserName = SETTINGS.getValue("jdbc.username");
        this.dbPassword = SETTINGS.getValue("jdbc.password");
        this.dbDriver = SETTINGS.getValue("jdbc.driver_class");
        isConnected = false;
    }

    /**
     * Load JDBC driver.
     */

    private void loadJDBCDriver() {
        try {
            Class.forName(this.dbDriver);
            LOG.debug("Database driver was loaded {}", this.dbDriver);
        } catch (ClassNotFoundException nfe) {
            LOG.warn("Failed by loading driver class", nfe);
        }
    }

    /**
     * Connect to database with {@link ConnectionDB} class.
     * @return true if connected, otherwise return false.
     */

    public boolean connectToDB() {
        this.loadJDBCDriver();
        if (!isConnected) {
            try {
                connection = DriverManager.getConnection(this.dbUrl, this.dbUserName, this.dbPassword);
                isConnected = true;
                LOG.debug("Get connection to database {}", connection);
            } catch (SQLException sqlE) {
                LOG.warn("Failed by connecting to database", sqlE);
            }
        }
        return isConnected;
    }

    /**
     * Get connection.
     * @return connection to database.
     */

    public static Connection getConnection() {
        return connection;
    }




}
