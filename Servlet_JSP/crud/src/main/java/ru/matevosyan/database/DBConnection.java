package ru.matevosyan.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.configuration.Settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class is for connection to database.
 * Created on 08.03.2018.
 * @author Matevosyan Vardan.
 * @version 1.0
 */

public class DBConnection {
    private static Connection connection;
    private static AtomicBoolean isConnected = new AtomicBoolean(false);
    private static final Settings SETTINGS = Settings.getSettingInstance();
    private static final String DB_URL = SETTINGS.getValue("jdbc.url");
    private static final String DB_USER_NAME = SETTINGS.getValue("jdbc.username");
    private static final String DB_PASSWORD = SETTINGS.getValue("jdbc.password");
    private static final String DB_DRIVER = SETTINGS.getValue("jdbc.driver_class");
    private static final Logger LOG = LoggerFactory.getLogger(DBConnection.class.getName());

    static {
        LOG.debug("try to connect to DB");
        connection = connectToDB();
        LOG.debug("Connect to DB");
    }

    /**
     * Constructor that don't allow create an instance of DBConnection.
     */
    private DBConnection() {
    }

    /**
     * Load JDBC driver.
     */
    private static void loadJDBCDriver() {
        try {
            Class.forName(DB_DRIVER);
            LOG.debug("Database driver was loaded {}", DB_DRIVER);
        } catch (ClassNotFoundException nfe) {
            LOG.warn("Failed by loading driver class", nfe);
        }
    }

    /**
     * Connect to database with {@link DBConnection} class.
     * @return true if connected, otherwise return false.
     */
    private static Connection connectToDB() {
        loadJDBCDriver();
        if (!isConnected.get()) {
            try {
                connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
                isConnected.set(true);
                LOG.debug("Get connection to database {}", connection);
            } catch (SQLException sqlE) {
                LOG.warn("Failed by connecting to database", sqlE);
            }
        }
        return connection;
    }

    /**
     * Get connection.
     * @return connection to database.
     */
    public static Connection getDBConnection() {
        return connection;
    }

    /**
     * Has connected to database.
     * @return true if connected.
     */
    public static boolean isConnected() {
        return isConnected.get();
    }

}



