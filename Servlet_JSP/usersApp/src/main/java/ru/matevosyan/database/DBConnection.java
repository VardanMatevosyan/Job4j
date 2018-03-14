package ru.matevosyan.database;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.configuration.Settings;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class is for connection to database.
 * Created on 13.03.2018.
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
    private static final BasicDataSource CONNECT_POOL = new BasicDataSource();

    static {
        CONNECT_POOL.setDriverClassName(DB_DRIVER);
        CONNECT_POOL.setUrl(DB_URL);
        CONNECT_POOL.setUsername(DB_USER_NAME);
        CONNECT_POOL.setPassword(DB_PASSWORD);
    }

    /**
     * Constructor that don't allow create an instance of DBConnection.
     */
    private DBConnection() {
    }


    /**
     * Get connection.
     * @return connection to database.
     */
    public static Connection getDBConnection() {
        LOG.debug("try to connect to DB");
        try {
            connection = CONNECT_POOL.getConnection();
        } catch (SQLException sqlExp) {
            LOG.debug("Problem with getting connection from connection pool", sqlExp);
        }
        LOG.debug("Get connection v from pool {}", connection);
        return  connection;
    }

    /**
     * Has connected to database.
     * @param connection is database connection.
     * @return true if connected.
     */
    public static boolean isConnected(Connection connection) {
        try {
            if (!connection.isClosed()) {
                isConnected.set(true);
            }
        } catch (SQLException sqlExp) {
            LOG.warn("Problem to check if connection is open", sqlExp);
        }
        return isConnected.get();
    }

    /**
     * Close connection pool.
     */
    public static void closePool() {
        try {
            CONNECT_POOL.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}



