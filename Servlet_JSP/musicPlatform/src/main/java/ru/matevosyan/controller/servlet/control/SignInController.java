package ru.matevosyan.controller.servlet.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.matevosyan.dao.DaoException;
import ru.matevosyan.dao.Users;
import ru.matevosyan.database.UserStore;
import ru.matevosyan.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * SignInController use to handle sign in users.
 */
public class SignInController extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(SignInController.class.getName());
    private static final Users USER_DAO = new Users();


    /**
     * Represent login page.
     * @param req user request object.
     * @param resp response send to the user.
     * @throws ServletException exception.
     * @throws IOException  exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    /**
     * Check user values to sign in to the system.
     * @param req user request object.
     * @param resp response send to the user.
     * @throws ServletException exception.
     * @throws IOException  exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        CopyOnWriteArrayList<User> users = null;
        try {
            users = USER_DAO.getAll();
        } catch (DaoException daoExp) {
            LOG.error("Problem with getting all users in SignInController class", daoExp);
        }
        LOG.debug("login - " + login + " password " + password);
        if ((UserStore.STORE.checkUserValuesInTheSystem("SELECT_ONE_USER", login, password))) {
            HttpSession session = req.getSession();
            synchronized (session) {
                session.setAttribute("login", login);
                session.setAttribute("password", password);
                for (User user : users) {
                    if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                        session.setAttribute("userRole", user.getRole());
                        session.setAttribute("name", user.getName());
                        break;
                    }
                }
            }
            resp.sendRedirect(String.format("%s", req.getContextPath()));
        } else {
            req.setAttribute("error", "Credential invalid");
            doGet(req, resp);
        }
    }
}
