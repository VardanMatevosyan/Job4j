package ru.matevosyan.parser;

import org.junit.Test;
import ru.matevosyan.configuration.Settings;
import ru.matevosyan.dataBase.ConnectionDB;

import java.sql.*;
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
    private VacancyParser vacancyParser = new VacancyParser();
    private static final Settings SETTINGS = Settings.getSettingInstance();

    /**
     * get create date from the database that inserted after parsing site and check the last insertion.
     * It should be the first day of the year.
     */
    @Test
    public void when_parsing_the_vacancy_then_check_the_first_date_of_year_to_start_parsing(){
        ConnectionDB connectionDB = new ConnectionDB();
        connectionDB.connectToDB();
        Timestamp lastTime = new Timestamp(0);
        vacancyParser.parsingVacancy();
        Connection connection = ConnectionDB.getConnection();
        Calendar expectedCalendar = new GregorianCalendar();
        expectedCalendar.set(2018, 0, 1, 0, 0, 0);
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


        assertThat(year , is(expectedYear));
        assertThat(month , is(expectedMonth));
    }


}