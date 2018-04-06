package ru.matevosyan.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.database.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
//    private static UserStore userStore;
//
//    /**
//     * Get UserStore object to manipulate with database.
//     * @throws ServletException exp.
//     */
//    @Override
//    public void init() throws ServletException {
//        userStore = UserStore.getInstance();
//    }

    /**
     * doGet method.
     * @param req request.
     * @param resp response.
     * @throws ServletException servlet exception.
     * @throws IOException input output exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", UserStore.STORE.getResult());
        this.doNotCashData(resp);
        printContent(req, resp);
    }

    /**
     * doPut method.
     * @param req request.
     * @param resp response.
     * @throws ServletException servlet exception.
     * @throws IOException input output exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getParameter("user").equals("") && !req.getParameter("login").equals("")) {
            UserStore.STORE.update(req.getParameter("user"), req.getParameter("login"),
                            req.getParameter("newUserName"),
                            req.getParameter("newUserLogin"),
                            req.getParameter("newUserRole"));
        }
        LOG.debug("Update user from the  database with name {} and login {}",
                req.getParameter("user"), req.getParameter("login"));
        req.setAttribute("users", UserStore.STORE.getResult());
        this.doNotCashData(resp);
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
