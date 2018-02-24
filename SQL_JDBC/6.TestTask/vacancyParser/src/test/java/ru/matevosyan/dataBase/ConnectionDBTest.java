package ru.matevosyan.dataBase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.matevosyan.start.StartVacancyParser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

/**
 * ConnectionDBTest to test database connection.
 * @author Matevosyan Vardan.
 * Created on 16.02.2018
 * @version 1.0
 */
public class ConnectionDBTest {

    @InjectMocks private ConnectionDB dbConnection;
    @InjectMocks public StartVacancyParser startVacancyParser;
    @Mock private Connection mockConnection;
    @Mock private Statement mockStatement;
    @Mock public ConnectionDB connectionDB;

    /**
     * Init mocks before each test methods.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Testing when_connect_to_database_then_return_statement at least ones.
     */

    @Test
    public void when_connect_to_database_then_return_statement_ones() {
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
    public void when_connection_to_database_is_closed_then_return_true() {
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
    public void test_connection() throws SQLException {

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