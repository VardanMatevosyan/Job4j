package ru.matevosyan.controller.servlet.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.dao.DaoException;
import ru.matevosyan.dao.IGeneric;
import ru.matevosyan.dao.Roles;
import ru.matevosyan.dao.Users;
import ru.matevosyan.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * UsersServlet class.
 * All sign in clients forward to index.jsp.
 * and for all clients - users and roles attribute is preparing.
 * Created on 13.03.2018.
 * @since 1.0
 * @author Matevosyan Vardan.
 * @version 1.0
 */

public class UsersServletController extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UsersServletController.class.getName());
    private static final IGeneric ROLE_DAO = new Roles();
    private static final IGeneric USER_DAO = new Users();

    /**
     * doGet method.
     * @param req request.
     * @param resp response.
     * @throws ServletException servlet exception.
     * @throws IOException input output exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();

        synchronized (session) {
            try {
                CopyOnWriteArrayList<User> users = USER_DAO.getAll();
                Map<String, User> mapOfUsers = new HashMap<>();
                users.forEach(user -> mapOfUsers.put(user.getName() + user.getLogin(), user));
                session.setAttribute("usersMap", mapOfUsers);
                session.setAttribute("users", users);
                session.setAttribute("roles", ROLE_DAO.getAll());
            } catch (DaoException daoExp) {
                LOG.error("problem with getting all users from dao users class {}", daoExp);
            }
        }
        printContent(req, resp);
    }

    /**
     * printContent method.
     * @param req request.
     * @param resp response.
     * @throws IOException input output exception.
     * @throws ServletException servlet exception.
     */
    private void printContent(final HttpServletRequest req, final HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html; charset=utf-8");
        this.doNotCashData(resp);
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }

    /**
     * Don't allow the browser cash the data from the response.
     * @param resp response.
     */
    private void doNotCashData(HttpServletResponse resp) {
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // for HTTP 1.1
        resp.setHeader("Pragma", "no-cache"); // for HTTP 1.0
        resp.setHeader("Expires", "0"); // for proxy
    }
}
