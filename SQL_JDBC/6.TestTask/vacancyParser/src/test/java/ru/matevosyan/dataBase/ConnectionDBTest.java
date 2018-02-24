package ru.matevosyan.dataBase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.matevosyan.parser.VacancyParser;
import ru.matevosyan.start.StartVacancyParser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
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
    @InjectMocks private StartVacancyParser startVacancyParser;
    @Mock private Connection mockConnection;
    @Mock private Statement mockStatement;
    @Mock private ConnectionDB connectionDB;

    /**
     * Init mocks before each test methods.
     */
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

    @Test
    public void whenConnectToDatabaseThenReturnStatementOnes() {
        try {
            when(mockConnection.createStatement()).thenReturn(mockStatement);
            verify(mockConnection.createStatement(), times(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Testing when_connection_to_database_is_closed_then_return_true.
     */

    @Test
    public void whenConnectionToDatabaseIsClosedThenReturnTrue() {
        try {
            when(mockConnection.isClosed()).thenReturn(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test database connection.
     * @throws SQLException if has problem with connection.
     */
    @Test
    public void testConnection() throws SQLException {

        when(connectionDB.connectToDB()).thenReturn(true);
        when(mockConnection.createStatement()).thenReturn(mockStatement);

        ResultSet resultSet = ConnectionDB.getConnection().createStatement()
                .executeQuery("select count(create_date) as count from vacancy where author = 'Marina17'");

        resultSet.next();
        int size = Integer.parseInt(resultSet.getString("count"));
        boolean actual = size >= 2;

        mockConnection.createStatement();
        verify(mockConnection, times(1)).createStatement();

        assertThat(actual, is(true));

    }

}