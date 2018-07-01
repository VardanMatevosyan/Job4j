package ru.matevosyan.controller.servlet.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.dao.DaoException;
import ru.matevosyan.dao.Users;
import ru.matevosyan.entity.Address;
import ru.matevosyan.entity.MusicType;
import ru.matevosyan.entity.User;
import ru.matevosyan.entity.UserRole;

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
 * UpdateServlet class.
 * Created on 13.03.2018.
 * @since 1.0
 * @author Matevosyan Vardan.
 * @version 1.0
 */

public class UpdateServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UpdateServlet.class.getName());
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
                session.setAttribute("errorInUpdate", "");
            } catch (DaoException expDao) {
                LOG.error("Problem with getting all users from updateServlet class {}", expDao);
            }
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
        HttpSession session = req.getSession();
        synchronized (session) {
            String userName = req.getParameter("user");
            String login = req.getParameter("login");
            if (userReceivedValuesIsNotEmpty(userName, login)) {
                Map<String, User> usersFromAttr = (Map) session.getAttribute("usersMap");
                boolean containsKey = usersFromAttr.containsKey(userName + login);
                if (containsKey) {
                    User userAttr = usersFromAttr.get(userName + login);
                    int id = userAttr.getId();
                    String newUserName = req.getParameter("newUserName");
                    String newUserLogin = req.getParameter("newUserLogin");
                    String oldPassword = userAttr.getPassword();
                    String oldEmail = userAttr.getEmail();
                    UserRole newUserRole = new UserRole(req.getParameter("newUserRole"));
                    Address oldAddress = userAttr.getAddress();
                    List<MusicType> oldMusicType = userAttr.getMusicType();
                    User newUser = new User(id, newUserName, newUserLogin, oldPassword, oldEmail, newUserRole, oldAddress,
                            oldMusicType);
                    try {
                        USER_DAO.update(newUser);
                        usersFromAttr.remove(userName + login);
                        usersFromAttr.put(newUser.getName() + newUser.getLogin(), newUser);
                        session.setAttribute("usersMap", usersFromAttr);
                        CopyOnWriteArrayList<User> users = USER_DAO.getAll();
                        session.setAttribute("users", users);
                    } catch (DaoException daoExp) {
                        LOG.error("Problem with updating by calling update on dao user class method update {}", daoExp);
                    }
                    LOG.debug("Update user from the  database with name {} and login {}", userName, login);

                } else {
                    req.setAttribute("errorInUpdate", "User are not exist!");
                }
            } else {
                req.setAttribute("errorInUpdate", "Please, fill in all data or check if they are correct");
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
