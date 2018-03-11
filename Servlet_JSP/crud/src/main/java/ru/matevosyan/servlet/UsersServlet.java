package ru.matevosyan.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.database.DBConnection;
import ru.matevosyan.database.UserStore;
import ru.matevosyan.model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * UsersServlet class.
 * Created on 28.02.2018.
 * @since 1.0
 * @author Matevosyan Vardan.
 * @version 1.0
 */

public class UsersServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UsersServlet.class.getName());
    private final AtomicInteger id = new AtomicInteger(0);
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
     * doGet method.
     * @param req request.
     * @param resp response.
     * @throws ServletException servlet exception.
     * @throws IOException input output exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        getAndPostChecker(req, resp, "get");
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
        this.getAndPostChecker(req, resp, "post");

    }

    /**
     * doPut method.
     * @param req request.
     * @param resp response.
     * @throws ServletException servlet exception.
     * @throws IOException input output exception.
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printContent(req, resp, "/forms/update.html");
        userStore.update(req.getParameter("user"), req.getParameter("login"),
                req.getParameter("newUserName"), req.getParameter("newUserLogin"));
    }

    /**
     * doDelete method.
     * @param req request.
     * @param resp response.
     * @throws ServletException servlet exception.
     * @throws IOException input output exception.
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printContent(req, resp,  "/forms/delete.html");
        userStore.delete(req.getParameter("user"), req.getParameter("login"));
    }

    /**
     * printContent method.
     * @param req request.
     * @param resp response.
     * @param fileName file name.
     * @throws IOException input output exception.
     * @throws ServletException servlet exception.
     */
    private void printContent(final HttpServletRequest req, final HttpServletResponse resp,
                              final String fileName) throws IOException, ServletException {
        resp.setContentType("text/html; charset=utf-8");
        ServletContext context = req.getSession().getServletContext();
        context.getRequestDispatcher(fileName).forward(req, resp);
    }

    /**
     * Check if user send for GET or POST method.
     * And if it is true print content or redirect to other method.
     * @param req request.
     * @param resp response.
     * @param method is GET or POST.
     * @throws ServletException exp.
     * @throws IOException exp.
     */
    private void getAndPostChecker(HttpServletRequest req, HttpServletResponse resp, String method) throws ServletException, IOException {
        String parameter = req.getParameter("method");
        System.out.println("URL for request" + parameter);

        if (parameter != null) {
            if (parameter.equalsIgnoreCase("get")) {
                printContent(req, resp, "/forms/get.html");
            } else if (parameter.equalsIgnoreCase("result")) {
                getResultToPage(req, resp);
            } else if (parameter.equalsIgnoreCase("update")) {
                doPut(req, resp);
            } else if (parameter.equalsIgnoreCase("delete")) {
                doDelete(req, resp);
            } else if (parameter.equalsIgnoreCase("create")) {
                this.createIFPostReturnIfGet(req, resp, method);
            }
        } else {
            this.createIFPostReturnIfGet(req, resp, method);
        }
    }

    /**
     * Create user and insert to database or if method is get then return index page.
     * @param req request.
     * @param resp response.
     * @param method is GET or POST.
     * @throws ServletException exp.
     * @throws IOException exp.
     */
    private void createIFPostReturnIfGet(HttpServletRequest req, HttpServletResponse resp, String method)  throws ServletException, IOException {
        if (method.equalsIgnoreCase("get")) {
            printContent(req, resp, "/forms/index.html");
        } else if (method.equalsIgnoreCase("post")) {
            User user;
            if (!req.getParameter("user").equals("") && !req.getParameter("login").equals("")
                    && !req.getParameter("password").equals("") && !req.getParameter("email").equals("")) {
                user = new User(req.getParameter("user"),
                        req.getParameter("login"), req.getParameter("password"), req.getParameter("email"));

                LOG.debug("Add user to database with name {} and login {}", user.getName(), user.getLogin());
                req.getRequestDispatcher("/forms/index.html").forward(req, resp);
                userStore.insert(user);
            }
            req.getRequestDispatcher("/forms/index.html").forward(req, resp);
        }
    }

    /**
     * Get result to the page if user click on the get button to get user from the database.
     * @param req request.
     * @param resp response.
     * @throws ServletException exp.
     * @throws IOException exp.
     */
    private void getResultToPage(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
        CopyOnWriteArrayList<String> storeResult = userStore.getResult(req.getParameter("user"),
                req.getParameter("login"));
        String info;
        if (!storeResult.isEmpty()) {
            info = String.format("<div><pre><b>Name:</b> %s\n<b>Login:</b> %s \n<b>Date:</b> %s\n</pre></div>",
                    storeResult.get(0), storeResult.get(1), storeResult.get(2));
        } else {
            info = "<pre><b>Can't find any users</b></pre>";
        }
        String page = userStore.getSqlScript("get.html");
        String form = page.substring(page.indexOf("123"), page.indexOf("<hr>"));
        String newPage = page.replace(form, info);
        PrintWriter writer = resp.getWriter();
        writer.write(newPage);
        writer.close();
    }


    /**
     * close connection to the database.
     */
    @Override
    public void destroy() {
        try {
            if (DBConnection.isConnected()) {
                DBConnection.getDBConnection().close();
            }
        } catch (SQLException e) {
            LOG.warn("Problem with closing connection");
        }
    }
}
