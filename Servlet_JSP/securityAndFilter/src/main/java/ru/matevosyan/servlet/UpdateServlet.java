package ru.matevosyan.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.database.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * UpdateServlet class.
 * Created on 13.03.2018.
 * @since 1.0
 * @author Matevosyan Vardan.
 * @version 1.0
 */

public class UpdateServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UpdateServlet.class.getName());

    /**
     * doGet method.
     * @param req request.
     * @param resp response.
     * @throws ServletException servlet exception.
     * @throws IOException input output exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        synchronized (session) {
            session.setAttribute("users", UserStore.STORE.getResult());
            session.setAttribute("errorInUpdate", "");
        }
        this.doNotCashData(resp);
        printContent(req, resp);
    }

    /**
     * doPost method.
     * @param req request.
     * @param resp response.
     * @throws ServletException servlet exception.
     * @throws IOException input output exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("user");
        String login = req.getParameter("login");
        if (userReceivedValuesIsNotEmpty(userName, login)) {
            HttpSession session = req.getSession();
            if (UserStore.STORE.checkUserValuesInTheSystem("CHECK_LOGIN_OR_NAME_IS_IN_DB", login, userName)) {
                UserStore.STORE.update(userName, login, req.getParameter("newUserName"),
                        req.getParameter("newUserLogin"),
                        req.getParameter("newUserRole"));
                synchronized (session) {
                    session.setAttribute("users", UserStore.STORE.getResult());
                }
                LOG.debug("Update user from the  database with name {} and login {}", userName, login);

            } else {
                synchronized (session) {
                    session.setAttribute("errorInUpdate", "User are not exist or invalidate data!");
                }
            }
        }
        this.doNotCashData(resp);
        printContent(req, resp);
    }

    /**
     * Check if user's values is fill in (not empty).
     * @param userName user name.
     * @param login user login.
     * @return false if even one of all values is empty, else true.
     */
    private boolean userReceivedValuesIsNotEmpty(String userName, String login) {
        return !userName.equals("") || !login.equals("");
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
        req.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(req, resp);
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
