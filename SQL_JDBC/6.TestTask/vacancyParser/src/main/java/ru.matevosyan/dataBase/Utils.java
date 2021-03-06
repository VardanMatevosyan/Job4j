package ru.matevosyan.dataBase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.configuration.Settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.ResultSet;

/**
 * Utils class for handling database.
 * created on 26.01.2018
 * @author Matevosyan Vardan.
 * @version 1.0
 */

public class Utils {
    private static final Logger LOG = LoggerFactory.getLogger(Utils.class);
    private boolean exist;
    private static final Settings SETTINGS = Settings.getSettingInstance();

    /**
     * Constructor.
     */

    public Utils() {
        this.createDB();
        this.exist = false;
    }

    /**
     * createDB method used to create database.
     * @return true if table is exist.
     */

    public boolean createDB() {
        boolean exist = false;
        Connection connection = ConnectionDB.getConnection();
        try {
            connection = DriverManager.getConnection(SETTINGS.getValue("jdbc.defaultUrl"),
                    SETTINGS.getValue("jdbc.username"), SETTINGS.getValue("jdbc.password"));
            exist = true;
        } catch (SQLException sql) {
            exist = false;
        }

        if (!exist) {
            try {
                Statement statement = connection.createStatement();
                String createDB = getSqlScript(SETTINGS.getValue("sql.create_DB"));
                statement.execute(createDB);
                exist = true;
                connection.close();
            } catch (SQLException sqlEx) {
                LOG.warn("Problem with execution query", sqlEx);
            }
        }
        return exist;
    }

    /**
     * CreateDBTable method used to create table in database when table is not exist.
     * @param connectionDB connection to database.
     * @return true if table is exist.
     */

    public boolean createDBTable(Connection connectionDB) {
        try (final Statement statement = connectionDB.createStatement()) {
            String createTableScript = getSqlScript(SETTINGS.getValue("sql.createTableVacancy"));
            String  triggerSql = getSqlScript(SETTINGS.getValue("sql.createTrigger"));
            statement.execute(createTableScript);
            statement.execute(triggerSql);
            this.exist = true;
        } catch (SQLException sql) {
            LOG.warn(sql.getMessage(), sql);
        }
        return exist;
    }

    /**
     * insertValueToDB created for insert  values to table.
     * @param tittle vacancy title.
     * @param author vacancy author.
     * @param createDate vacancy create date.
     * @return true if inserted else false.
     */
    public boolean insertValueToDB(String tittle, String author, Timestamp createDate) {
        Connection connection = ConnectionDB.getConnection();
        boolean isInserted = false;
        String insertIntoTable = getSqlScript(SETTINGS.getValue("sql.insertValueToDB"));
        try (PreparedStatement preparedStatementToInsert = connection.prepareStatement(insertIntoTable)) {
            connection.setAutoCommit(false);
            preparedStatementToInsert.setString(1, tittle);
            preparedStatementToInsert.setString(2, author);
            preparedStatementToInsert.setTimestamp(3, createDate);
            preparedStatementToInsert.executeUpdate();
            connection.commit();
            isInserted = true;
        } catch (SQLException sqlEx) {
            try {
                connection.rollback();
            } catch (SQLException sqlRollBackEx) {
                LOG.warn(sqlRollBackEx.getMessage(), sqlRollBackEx);
            }
            LOG.warn(sqlEx.getMessage(), sqlEx);
        }
        return isInserted;
    }

    /**
     * Check if any duplicate is in the database.
     * @param tittle vacancy title.
     * @param author vacancy author.
     * @param createDate vacancy create date.
     * @return true if find duplicate in the database.
     */
    public static boolean isTheSameAsInTheDatabase(String tittle, String author, Timestamp createDate) {
        boolean isTheSame = false;
        try {
            Connection connection = ConnectionDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    SETTINGS.getValue("sql.checkDuplicate"),
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.TYPE_SCROLL_INSENSITIVE);
            preparedStatement.setString(1, tittle);
            preparedStatement.setString(2, author);
            preparedStatement.setTimestamp(3, createDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            int fetchSize = resultSet.getFetchSize();
            if (fetchSize > 0) {
                isTheSame = true;
            }
            if (resultSet.first()) {
                isTheSame = true;
            }
        } catch (SQLException sqlEx) {
            LOG.warn("Problem with connection", sqlEx);
        }
        return  isTheSame;
    }


    /**
     * Get sql script from the resource folder file.
     * @param value name of the sql file.
     * @return script as a String.
     */

    private static String getSqlScript(String value) {
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
