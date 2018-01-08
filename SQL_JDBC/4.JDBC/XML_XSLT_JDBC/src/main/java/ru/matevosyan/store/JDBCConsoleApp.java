package ru.matevosyan.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.services.Settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * JDBCConsoleApplication class.
 * @author Matevosyan Vardan.
 * Created on 01.01.2018
 * @version 1.0
 */
public class JDBCConsoleApp {
    private static final Logger LOG = LoggerFactory.getLogger(JDBCConsoleApp.class);
    private Connection DataBaseConnection;
    private boolean isConnected;
    private final String dbURL;
    private final String dbUserName;
    private final String dbPassword;
    private final String dbDriver;

    /**
     * Constructor for JDBCConsoleApp class.
     */

    public JDBCConsoleApp() {
        isConnected = false;
        Settings settings = Settings.getSettingInstance();
        dbURL = settings.getValue("jdbc.url");
        dbUserName = settings.getValue("jdbc.username");
        dbPassword = settings.getValue("jdbc.password");
        dbDriver = settings.getValue("jdbc.driver_class");
        this.connectToDB();
    }

    /**
     * connectToDB method to create connection to database.
     * @return true if connected else return false.
     */

    private boolean connectToDB() {
        try {
            Class.forName(this.dbDriver);
        } catch (ClassNotFoundException cnfe) {
            LOG.warn("Can't load jdbc driver", cnfe);
        }
        if (!this.isConnected) {
            try {
                this.DataBaseConnection = DriverManager.getConnection(this.dbURL, this.dbUserName, this.dbPassword);
                this.isConnected = true;
                LOG.info("Connected to database {}", this.dbURL);
            } catch (SQLException sql) {
                LOG.warn(sql.getMessage(), sql);
                LOG.warn("Cant't connect with database {}", this.dbURL, sql);
            }
        }
        return isConnected;
    }

    /**
     * closeConnectToDB method to close connection to database.
     */

    public void closeConnectToDB() {
        try {
            this.DataBaseConnection.close();
            LOG.info("Connection to the database {} was closed ", this.dbURL);
        } catch (SQLException sql) {
            sql.printStackTrace();
            LOG.warn("Cant't close connection with database {}", this.dbURL, sql);
        }
    }

    /**
     * Getter for Data Base Connection value.
     * @return DataBaseConnection variable.
     */

    public Connection getDataBaseConnection() {
        return DataBaseConnection;
    }

}
