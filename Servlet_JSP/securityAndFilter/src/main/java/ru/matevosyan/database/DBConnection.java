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

public enum DBConnection {
    INSTANCE;
    private Connection connection;
    private AtomicBoolean isConnected = new AtomicBoolean(false);
    private final BasicDataSource connectPool;
    private static final Logger LOG = LoggerFactory.getLogger(DBConnection.class.getName());

    /**
     * Constructor create and set database connection pool.
     */
    DBConnection() {
        connectPool = new BasicDataSource();
        Settings settings = Settings.getSettingInstance();
        connectPool.setDriverClassName(settings.getValue("jdbc.driver_class"));
        connectPool.setUrl(settings.getValue("jdbc.url"));
        connectPool.setUsername(settings.getValue("jdbc.username"));
        connectPool.setPassword(settings.getValue("jdbc.password"));
    }

    /**
     * Get connection.
     * @return connection to database.
     */
    public Connection getDBConnection() {
        try {
            connection = connectPool.getConnection();
            LOG.debug("get connection from pool", connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Has connected to database.
     * @param connection is database connection.
     * @return true if connected.
     */
    public boolean isConnected(Connection connection) {
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
    public void closePool() {
        try {
            connectPool.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get DBConnection instance.
     * @return dbConnection instance.
     */
    public static DBConnection getInstance() {
        return INSTANCE;
    }
}



