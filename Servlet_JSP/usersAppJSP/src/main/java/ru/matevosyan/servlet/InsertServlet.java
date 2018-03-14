package ru.matevosyan.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.database.UserStore;
import ru.matevosyan.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * InsertServlet class.
 * Created on 13.03.2018.
 * @since 1.0
 * @author Matevosyan Vardan.
 * @version 1.0
 */

public class InsertServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UsersServlet.class.getName());
    private static UserStore userStore;

    /**
     * Get UserStore object to manipulate with database.
     * @throws ServletException exp.
     */
    @Override
    public void init() throws ServletException {
        userStore = UserStore.getInstance();
    }

    /**
     * doPost method insert data to database.
     * @param req request.
     * @param resp response.
     * @throws ServletException servlet exception.
     * @throws IOException input output exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.insertIfNotEmpty(req, resp);

    }

    /**
     * Create User instance with received value from the request and insert to the database.
     * @param req request.
     * @param resp response.
     * @throws ServletException servlet exception.
     * @throws IOException input output exception.
     */
    private void insertIfNotEmpty(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user;
        if (!req.getParameter("user").equals("") && !req.getParameter("login").equals("")
                && !req.getParameter("password").equals("") && !req.getParameter("email").equals("")) {
            user = new User(req.getParameter("user"),
                    req.getParameter("login"), req.getParameter("password"), req.getParameter("email"));


            userStore.insert(user);
            req.getRequestDispatcher("/forms/index.jsp").forward(req, resp);
            LOG.debug("Add user to database with name {} and login {}", user.getName(), user.getLogin());

        }
        req.getRequestDispatcher("/forms/index.jsp").forward(req, resp);
    }


}
