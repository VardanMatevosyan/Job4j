package ru.matevosyan.servlet;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.database.UserStore;
import ru.matevosyan.model.User;
import ru.matevosyan.model.UserRole;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * DeleteServletTest class is for testing DeleteServletTest.
 * For country США id = 7 and for city Санта-Круз id = 11.
 */
public class DeleteServletTest {
    private static final Logger LOG = LoggerFactory.getLogger(InsertServletTest.class.getName());
    private static final User USER = new User("DeleteTestName", "DeleteTestLogin",
            "DeleteTestPassword", "email@email",
            new UserRole(1, "admin"), "7", "11");

    /**
     * Insert user to the database.
     */
    @Before
    public void setUp() {
        UserStore.STORE.insert(USER);
        LOG.debug("add user to database in setUp method test {} ", USER);
    }

    /**
     * When delete user from the database than if user is deleted than return false.
     * @throws ServletException servlet.
     * @throws IOException input output stuff doing wrong.
     */
    @Test
    @Ignore
    public void whenDeleteUserFromDBThanResturnFalseIfThereIsNotUserPresent() throws ServletException, IOException {
        boolean isInTheDBBefore = this.getUserFromTheDB();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext servletContext = mock(ServletContext.class);
        DeleteServlet delete = new DeleteServlet();

        when(request.getSession()).thenReturn(session);
        when(session.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRequestDispatcher("/WEB-INF/views/delete.jsp")).thenReturn(dispatcher);
        when(request.getParameter("user")).thenReturn("DeleteTestName");
        when(request.getParameter("login")).thenReturn("DeleteTestLogin");
        delete.doPost(request, response);
        boolean isInTheDBAfter = this.getUserFromTheDB();

        assertThat(isInTheDBBefore, is(!(isInTheDBAfter)));
    }

    /**
     * getting users from the database.
     * @return true is user is in the database, else false.
     */
    private boolean getUserFromTheDB() {
        boolean isInTheDB;
        CopyOnWriteArrayList<User> users = UserStore.STORE.getResult();
        isInTheDB = users.stream().anyMatch(user ->  user.getLogin().equals(USER.getLogin())
                && user.getPassword().equals(USER.getPassword()));
        return isInTheDB;
    }
}
