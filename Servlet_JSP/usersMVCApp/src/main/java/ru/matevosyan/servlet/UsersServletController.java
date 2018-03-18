package ru.matevosyan.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.database.DBConnection;
import ru.matevosyan.database.UserStore;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * UsersServlet class.
 * Created on 13.03.2018.
 * @since 1.0
 * @author Matevosyan Vardan.
 * @version 1.0
 */

public class UsersServletController extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UsersServletController.class.getName());
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
     * Getter for UserStore.
     * @return userStore.
     */
    public static UserStore getUserStore() {
        return userStore;
    }

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
        req.setAttribute("users", UserStore.getInstance().getResult());
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
        ServletContext context = req.getSession().getServletContext();
        context.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }

    /**
     * close connection to the database.
     */
    @Override
    public void destroy() {
        DBConnection.closePool();
        LOG.debug("close connection pool");
    }
}
