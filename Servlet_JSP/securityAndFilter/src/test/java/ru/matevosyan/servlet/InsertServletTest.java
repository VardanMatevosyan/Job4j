package ru.matevosyan.servlet;

import org.junit.After;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.database.DBConnection;
import ru.matevosyan.model.User;
import ru.matevosyan.model.UserRole;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * InsertServletTest class to test user insertion.
 */
public class InsertServletTest {
    private static final Logger LOG = LoggerFactory.getLogger(InsertServletTest.class.getName());

    /**
     * Delete added user from the database.
     */
    @After
    public void tearDown() {
        Connection connection = DBConnection.getInstance().getDBConnection();
        try (Statement ps = connection.createStatement()) {
            String query = "DELETE FROM users WHERE login='UserMock'";
            ps.execute(query);
        } catch (SQLException sqlEx) {
            LOG.warn("Problem with execution query invoke by get method {}", sqlEx);
        }
    }

    /**
     *  When insert user to database than get the same data from the database.
     * @throws ServletException servletException.
     * @throws IOException IOException.
     */
    @Test
    public void whenInsertToDBThanGetGetTheTheSameDataFromTheDB() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        InsertServlet insert = new InsertServlet();
        String expected = "UserMock";
        when(request.getParameter("user")).thenReturn("UserMock");
        when(request.getParameter("login")).thenReturn("UserMock");
        when(request.getParameter("password")).thenReturn("UserMock");
        when(request.getParameter("email")).thenReturn("UserMock@UserMock");
        when(request.getParameter("userRole")).thenReturn("user");
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/WEB-INF/views/index.jsp")).thenReturn(dispatcher);
        insert.doPost(request, response);

        User user = this.getResult(request);
        assertThat(expected, is(user.getName()));
    }

    /**
     * get user info from the database to represent to the browser.
     * @return list with user value.
     */
    private User getResult(HttpServletRequest request) {

        User user = new User("", "", "", "", new UserRole(0, ""));
        Connection connection = DBConnection.getInstance().getDBConnection();
        try (Statement ps = connection.createStatement()) {
            String query = "SELECT u.name, u.login, "
                    + "u.createDate, u.password, u.email, r.id, r.name FROM users AS u "
                    + "INNER JOIN roles AS r "
                    + "ON u.fk_role=r.id";
            ResultSet resultSet = ps.executeQuery(query);
            while (resultSet.next()) {
                user = new User(resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getTimestamp("createDate"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        new UserRole(resultSet.getInt(6), resultSet.getString(7)));
            }
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.warn("Problem with execution query invoke by get method {}", sqlExp);
        }
        return user;
    }
}