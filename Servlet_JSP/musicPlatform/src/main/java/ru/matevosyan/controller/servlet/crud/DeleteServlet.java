package ru.matevosyan.controller.servlet.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.controller.servlet.control.UsersServletController;
import ru.matevosyan.dao.DaoException;
import ru.matevosyan.dao.Users;
import ru.matevosyan.entity.Address;
import ru.matevosyan.entity.MusicType;
import ru.matevosyan.entity.User;
import ru.matevosyan.entity.UserRole;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * DeleteServlet class.
 * Created on 13.03.2018.
 * @since 1.0
 * @author Matevosyan Vardan.
 * @version 1.0
 */

public class DeleteServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UsersServletController.class.getName());
    private static final Users USER_DAO = new Users();

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
            try {
                CopyOnWriteArrayList<User> users = USER_DAO.getAll();
                session.setAttribute("users", users);
            } catch (DaoException daoExp) {
                LOG.error("problem with getting all users from dao users class {}", daoExp);
            }
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
        HttpSession session = req.getSession();
        synchronized (session) {
            String userName = req.getParameter("user");
            String login = req.getParameter("login");
            Map usersFromAttr = (Map) session.getAttribute("usersMap");
            boolean containsKey = usersFromAttr.containsKey(userName + login);
            if (containsKey) {
                User userParaVal = (User) usersFromAttr.get(userName + login);
                int id = userParaVal.getId();
                String password = userParaVal.getPassword();
                String email = userParaVal.getEmail();
                UserRole role = userParaVal.getRole();
                Address address = userParaVal.getAddress();
                List<MusicType> musicType = userParaVal.getMusicType();

                User user = new User(id, userName, login, password, email, role, address, musicType);
                try {
                    USER_DAO.delete(user);
                    usersFromAttr.remove(userName + login);
                    CopyOnWriteArrayList<User> usersList = USER_DAO.getAll();
                    session.setAttribute("usersMap", usersFromAttr);
                    session.setAttribute("users", usersList);
                } catch (DaoException daoExp) {
                    LOG.error("problem with getting all users from dao users class {}", daoExp);
                }
                LOG.debug("delete user from database {}", userName);
            } else {
                req.setAttribute("errorInDelete", "There is no such user! Please check your data");
                LOG.debug("User is not deleted {}", "Trying to delete user but there is not such user in the system");
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
