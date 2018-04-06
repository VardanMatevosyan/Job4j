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
    private static final Logger LOG = LoggerFactory.getLogger(UsersServletController.class.getName());
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("users", UserStore.STORE.getResult());
        req.getRequestDispatcher("/WEB-INF/views/get.jsp").forward(req, resp);
    }

}
