package ru.matevosyan.dataBase;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.matevosyan.parser.VacancyParser;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


/**
 * ConnectionDBTest to test database connection.
 * @author Matevosyan Vardan.
 * Created on 16.02.2018
 * @version 1.0
 */
public class ConnectionDBTest {
    private VacancyParser vacancyParser = new VacancyParser();
    @InjectMocks private ConnectionDB dbConnection;
    @Mock private Connection mockConnection;
    @Mock private Statement mockStatement;

    /**
     * Init mocks before each test methods.
     */
    @Ignore
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ConnectionDB connectionDB = new ConnectionDB();
        connectionDB.connectToDB();
        vacancyParser.parsingVacancy();
    }

    /**
     * close connection to database.
     */
    @Ignore
    @After
    public void dearDown() {
        try {
            ConnectionDB.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Testing when_connect_to_database_then_return_statement at least ones.
     */
    @Ignore
    @Test
    public void whenConnectToDatabaseThenReturnStatementOnes() {
        try {
            when(mockConnection.createStatement()).thenReturn(mockStatement);
            verify(mockConnection, times(1)).createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Testing when_connection_to_database_is_closed_then_return_true.
     */

    @Ignore
    @Test
    public void whenConnectionToDatabaseIsClosedThenReturnTrue() {
        try {
            when(mockConnection.isClosed()).thenReturn(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}