package ru.matevosyan.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.services.Settings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DBOperation class for handling database.
 * created on 01.01.2018
 * @author Matevosyan Vardan.
 * @version 1.0
 */

public class DBOperation {
    private static final Logger LOG = LoggerFactory.getLogger(DBOperation.class);
    private final Connection connection;
    private final String tableName;
    private boolean exist;

    /**
     * Constructor.
     * @param connection database connection.
     */

    public DBOperation(final Connection connection) {
        Settings settings = Settings.getSettingInstance();
        this.connection = connection;
        this.tableName = settings.getValue("jdbc.tableName");
        this.exist = false;
    }

    /**
     * CreateDBTable method used to create table in database when table is not exist.
     * @return true if table is exist.
     */

    public boolean createDBTable() {
        try (final Statement statement = this.connection.createStatement()) {
            statement.execute(String.format("create table if not exists %s (field integer)", this.tableName));
            this.exist = true;

        } catch (SQLException sql) {
            LOG.warn(sql.getMessage(), sql);
        }
        return exist;
    }

    /**
     * clear all dara from table
     */

    public void cleatAllData() {
        try (final Statement statementToDelete = this.connection.createStatement()) {
            String sqlForDelete = String.format("delete from %s where field is not null", this.tableName);
            boolean isEmpty = statementToDelete.execute(sqlForDelete);

            if (isEmpty) {
                LOG.debug("Table {} is empty", this.tableName);
            }
        } catch (SQLException sql) {
            LOG.warn(sql.getMessage(), sql);
    }
    }

    /**
     * insertValueToDB created for insert  values to table.
     * @param numberOfRows numbers of values to be inserted.
     * @return true if inserted else false.
     */

    public boolean insertValueToDB(int numberOfRows) {
        this.cleatAllData();
        boolean isInserted = false;
        String sqlInsert = String.format("insert into %s (field) values (?)", this.tableName);

        try (PreparedStatement preparedStatementToInsert = this.connection.prepareStatement(sqlInsert)) {
            for (int i = 1; i <= numberOfRows; i++) {
                preparedStatementToInsert.setInt(1, i);
                preparedStatementToInsert.executeUpdate();
            }
            isInserted = true;
        } catch (SQLException sql) {
            LOG.warn(sql.getMessage(), sql);
        }
        return isInserted;
    }

}
