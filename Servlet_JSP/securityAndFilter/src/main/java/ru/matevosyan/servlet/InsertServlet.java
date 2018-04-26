package ru.matevosyan.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.database.UserStore;
import ru.matevosyan.model.User;
import ru.matevosyan.model.UserRole;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * InsertServlet class.
 * Created on 13.03.2018.
 * Update on 13.05.2018
 * @since 1.0
 * @author Matevosyan Vardan.
 * @version 1.0
 */

public class InsertServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UsersServletController.class.getName());

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
        UserRole role;
        String roleName = req.getParameter("userRole");
        int roleId = UserStore.STORE.getRoleIdByName(roleName);
        role = new UserRole(roleId, roleName);
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String userName = req.getParameter("user");
        if (userReceivedValuesIsNotEmpty(userName, login, password, email)) {
            if (UserStore.STORE.validateAllFieldsForUserCreation(login, userName, email)) {
                user = new User(req.getParameter("user"),
                        req.getParameter("login"),
                        req.getParameter("password"), req.getParameter("email"), role,
                        req.getParameter("countrySelect"), req.getParameter("citySelect"));
                UserStore.STORE.insert(user);
                HttpSession session = req.getSession();
                synchronized (session) {
                    session.setAttribute("users", UserStore.STORE.getResult());
                }
                req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
                LOG.debug("Add user to database with name {} and login {}", user.getName(), user.getLogin());
            }  else {
                HttpSession session = req.getSession();
                synchronized (session) {
                    session.setAttribute("validateError", "User are already exist! Or invalidate data");
                }
            }
            req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
        }
    }

    /**
     * Check if user's values is fill in (not empty).
     * @param userName user name.
     * @param login user login.
     * @param password user password.
     * @param email user email.
     * @return false if even one of all values is empty, else true.
     */
    private boolean userReceivedValuesIsNotEmpty(String userName, String login, String password, String email) {
        return !userName.equals("") && !login.equals("") && !password.equals("") && !email.equals("");
    }
}
