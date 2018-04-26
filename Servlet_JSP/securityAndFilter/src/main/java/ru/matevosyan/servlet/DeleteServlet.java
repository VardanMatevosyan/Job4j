package ru.matevosyan.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.database.UserStore;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * DeleteServlet class.
 * Created on 13.03.2018.
 * @since 1.0
 * @author Matevosyan Vardan.
 * @version 1.0
 */

public class DeleteServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UsersServletController.class.getName());

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
            session.setAttribute("errorInDelete", "");
        }
        this.doNotCashData(resp);
        this.printContent(req, resp);
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
        HttpSession session = req.getSession();
        if (UserStore.STORE.checkUserValuesInTheSystem("CHECK_LOGIN_OR_NAME_IS_IN_DB", login, userName)) {
            UserStore.STORE.delete(userName, login);
            synchronized (session) {
                session.setAttribute("users", UserStore.STORE.getResult());
            }
            LOG.debug("delete user from database {}", userName);
        } else {
            synchronized (session) {
                session.setAttribute("errorInDelete", "There is no such user! Please check your data");
                LOG.debug("User is not deleted {}", "Trying  to delete user but there is not such user in the system");
            }
        }
        this.doNotCashData(resp);
        this.printContent(req, resp);
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
        ServletContext context = req.getSession().getServletContext();
        context.getRequestDispatcher("/WEB-INF/views/delete.jsp").forward(req, resp);
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
