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
 * ResultServlet class.
 * Created on 13.03.2018.
 * @since 1.0
 * @author Matevosyan Vardan.
 * @version 1.0
 */

public class ResultServlet extends HttpServlet {
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
     * doGet method.
     * @param req request.
     * @param resp response.
     * @throws ServletException servlet exception.
     * @throws IOException input output exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("/forms/get.jsp").forward(req, resp);
//        this.getResultToPage(req, resp);
    }
//
//    /**
//     * Get result to the page if user click on the get button to get user from the database.
//     * @param req request.
//     * @param resp response.
//     * @throws ServletException exp.
//     * @throws IOException exp.
//     */
//    private void getResultToPage(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
//        CopyOnWriteArrayList<User> storeResult = userStore.getResult();
//        StringBuilder info = new StringBuilder();
//        if (!storeResult.isEmpty()) {
//            for (User user : storeResult) {
//                info.append(String.format("<div><pre><b>Name:</b> %s\n<b>Login:</b> %s \n<b>Date:</b> %s\n</pre></div>",
//                        user.getName(), user.getLogin(), user.getCreateDate()));
//            }
//        } else {
//            info.append("<pre><b>Can't find any users</b></pre>");
//        }
//        String page = userStore.getSqlScript("get.html");
//        String form = page.substring(page.indexOf("123"), page.indexOf("<hr>"));
//        String newPage = page.replace(form, info.toString());
//        PrintWriter writer = resp.getWriter();
//        writer.write(newPage);
//        writer.close();
//    }
}
