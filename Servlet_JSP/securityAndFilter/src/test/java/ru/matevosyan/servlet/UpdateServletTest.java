package ru.matevosyan.servlet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.database.UserStore;
import ru.matevosyan.model.User;
import ru.matevosyan.model.UserRole;

import javax.servlet.RequestDispatcher;
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

public class UpdateServletTest {

    private static final Logger LOG = LoggerFactory.getLogger(InsertServletTest.class.getName());
    private static final User USER = new User("UpdateTestName", "UpdateTestLogin",
            "UpdateTestPassword", "email@email",
            new UserRole(1, "admin"));

    private static final User NEW_UPDATE_USER = new User("NEWUpdateName", "NEWUpdateLogin",
            "NEWUpdatePassword", "email@email",
            new UserRole(1, "admin"));

    /**
     * Insert user to the database.
     */
    @Before
    public void setUp() {
        UserStore.STORE.insert(USER);
        LOG.debug("add user to database in setUp method test {} ", USER.getName());
    }

    /**
     * Delete the same user from the database.
     */
    @After
    public void tearDown() {
        UserStore.STORE.delete(NEW_UPDATE_USER.getName(), NEW_UPDATE_USER.getLogin());
        LOG.debug("delete user from database in tearDown method test {} ", NEW_UPDATE_USER.getName());
    }

    /**
     * When update user in the db than return true if get updated user.
     * @throws ServletException ServletException.
     * @throws IOException IOException.
     */
    @Test
    public void whenUpdateUserInDBThanReturnTrueIfGetNewUser() throws ServletException, IOException {
        boolean isInTheDBBefore = this.getUserFromTheDB();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        UpdateServlet update = new UpdateServlet();

        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/WEB-INF/views/update.jsp")).thenReturn(dispatcher);
        when(request.getParameter("user")).thenReturn(USER.getName());
        when(request.getParameter("login")).thenReturn(USER.getLogin());
        when(request.getParameter("newUserName")).thenReturn(NEW_UPDATE_USER.getName());
        when(request.getParameter("newUserLogin")).thenReturn(NEW_UPDATE_USER.getLogin());
        when(request.getParameter("newUserRole")).thenReturn(NEW_UPDATE_USER.getRole().getName());

        update.doPost(request, response);
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
        isInTheDB = users.stream().anyMatch(user ->  user.getLogin().equals(NEW_UPDATE_USER.getLogin())
                && user.getName().equals(NEW_UPDATE_USER.getName()));
        return isInTheDB;
    }
}