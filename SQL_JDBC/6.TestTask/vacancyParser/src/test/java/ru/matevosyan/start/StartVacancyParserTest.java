package ru.matevosyan.start;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import ru.matevosyan.configuration.Settings;
import ru.matevosyan.dataBase.ConnectionDB;
import ru.matevosyan.parser.DateTransformation;
import ru.matevosyan.parser.OnePageParser;
import ru.matevosyan.startTimer.StartTimeController;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

/**
 * Created by Admin on 27.01.2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class StartVacancyParserTest {
    private static final long TIME_TO_REBUT = 6000L;
    private static final Settings SETTINGS = Settings.getSettingInstance();


    @Mock private Connection mockConnection;
    @Mock private Statement mockStatement;
    @Mock private StartProgram startProgram;
    @Mock private StartTimeController startTimeController;
    @Mock private OnePageParser onePageParser;


    @InjectMocks public StartVacancyParser startVacancyParser;
    @InjectMocks public ConnectionDB connectionDB;
    @InjectMocks public DateTransformation transformation;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_connection() throws SQLException {

        when(connectionDB.connectToDB()).thenReturn(true);

        when( mockConnection.createStatement()).thenReturn(mockStatement);
        ResultSet resultSet = mockConnection.createStatement().executeQuery("select count(create_date) as 'count' from vacancy" +
                " where author = 'Hr_Julia93'");
        String author = resultSet.getString("author");

        mockConnection.createStatement();
        verify(mockConnection, times(1)).createStatement();

        assertThat(author, is("Hr_Julia93"));

    }


    @Test
    public void test_onePageParser () {
        Document document;
        Elements elements;
        try {
            document = Jsoup.connect(SETTINGS.getValue("sql.WebPage")).get();
            elements = document.select("table.forumTable > tbody > tr");
            onePageParser.pageParser(elements);
            verify(onePageParser, atLeastOnce()).pageParser(elements);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test_DB_parsing () {
        startTimeController.run();
        verify(startTimeController, times(1)).run();
    }

    @Test
    public void Not_Test_Just_Use_For_Run_Program_For_See_What_Is_Program_Actually_Do() {
        new StartProgram(TIME_TO_REBUT).scheduleStart();

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}