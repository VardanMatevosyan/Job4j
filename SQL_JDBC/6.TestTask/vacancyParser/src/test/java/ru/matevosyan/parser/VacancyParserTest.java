package ru.matevosyan.parser;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.matevosyan.configuration.Settings;
import ru.matevosyan.dataBase.ConnectionDB;
import ru.matevosyan.dataBase.Utils;
import ru.matevosyan.start.StartVacancyParser;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * VacancyParserTest to test parsing site elements and compare the values that should be inserted to database.
 * @author Matevosyan Vardan.
 * Created on 23.02.2018
 * @version 1.0
 */

public class VacancyParserTest {
    private static final Settings SETTINGS = Settings.getSettingInstance();
    private static final ConnectionDB CONNECTION_DB = new ConnectionDB();
    private static final Utils UTILS = new Utils();


    /**
     * setup database connection and create table.
     */
    @BeforeClass
    public static void setUp() {
        connect();
        createTable();
    }

    /**
     * delete table from the database and close connection to the database.
     */
    @AfterClass
    public static void tearDown() {
        deleteTable();
        close();
    }

    /**
     * get create date from the database that inserted after parsing site and check the last insertion.
     * It should be the first day of the year.
     */
    @Test
    public void whenParsingTheVacancyThenCheckTheFirstDateOfYearToStartParsing() {
        ConnectionDB connectionDB = new ConnectionDB();
        connectionDB.connectToDB();
        Timestamp lastTime = new Timestamp(0);
        new StartVacancyParser().parsingSite();
        Connection connection = ConnectionDB.getConnection();
        Calendar expectedCalendar = new GregorianCalendar();
        expectedCalendar.set(2019, 0, 1, 0, 0, 0);
        int expectedYear = expectedCalendar.get(Calendar.YEAR);
        int expectedMonth = expectedCalendar.get(Calendar.JANUARY);

        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(SETTINGS.getValue("css.selectCreate_date"));
            boolean last = resultSet.last();
            if (last) {
                lastTime = resultSet.getTimestamp("create_date");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        long time = lastTime.getTime();
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(time);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.JANUARY);

        assertThat(year, is(expectedYear));
        assertThat(month, is(expectedMonth));
    }

    /**
     * connect to the database.
     */
    private static void connect() {
        CONNECTION_DB.connectToDB();
    }

    /**
     * create table vacancy in the database.
     */
    private static void createTable() {
        UTILS.createDBTable(ConnectionDB.getConnection());
    }

    /**
     * delete table from the database.
     */
    private static void deleteTable() {
        try (Statement statement = ConnectionDB.getConnection().createStatement()) {
            statement.execute(SETTINGS.getValue("sql.deleteTable"));
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    /**
     * close connection to the database.
     */
    private static void close() {
        try {
            ConnectionDB.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}