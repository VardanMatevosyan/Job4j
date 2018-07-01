package ru.matevosyan.controller.servlet.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.matevosyan.controller.servlet.control.UsersServletController;
import ru.matevosyan.dao.DaoException;
import ru.matevosyan.dao.Roles;
import ru.matevosyan.dao.Users;
import ru.matevosyan.database.UserStore;
import ru.matevosyan.entity.Address;
import ru.matevosyan.entity.MusicType;
import ru.matevosyan.entity.User;
import ru.matevosyan.entity.UserRole;
import ru.matevosyan.repository.UserRepository;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;


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
    private static final Users USER_DAO = new Users();
    private static final Roles ROLE_DAO = new Roles();
    private static final UserRepository USER_REPOSITORY = new UserRepository();

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
        HttpSession session = req.getSession();
        synchronized (session) {
            Map<String, User> usersFromAttr = (Map) session.getAttribute("usersMap");
            User user;
            int id = -1;
            UserRole role;
            String roleName = req.getParameter("userRole");
            int roleId = ROLE_DAO.getRoleIdByName(roleName);
            role = new UserRole(roleId, roleName);
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            String email = req.getParameter("email");
            String name = req.getParameter("user");
            String country = String.valueOf(((ConcurrentHashMap) session.getAttribute("countriesMap"))
                    .get(req.getParameter("countrySelect")));
            String city = String.valueOf(((ConcurrentHashMap) session.getAttribute("citiesMap"))
                    .get(req.getParameter("citySelect")));
            Address address = new Address();
            address.setCountry(country);
            address.setCity(city);
            address.setStreet(req.getParameter("streetSelect"));
            address.setNumber(Integer.parseInt(req.getParameter("homeNumberSelect")));
            String[] musicTypes = req.getParameterValues("musicTypesSelect");
            List<MusicType> listOfMusicTypes = new ArrayList<>();
            ConcurrentHashMap<Integer, String> mapOfMusicType = (ConcurrentHashMap) session.getAttribute("mapOfMusicType");
            for (String musicTypeValue : musicTypes) {
                MusicType musicType = new MusicType();
                musicType.setName(mapOfMusicType.get(Integer.parseInt(musicTypeValue)));
                musicType.setId(Integer.parseInt(musicTypeValue));
                listOfMusicTypes.add(musicType);
            }
            if (!(musicTypes.length > 0)) {
                MusicType musicType = new MusicType();
                musicType.setName("");
                listOfMusicTypes.add(musicType);
            }

            if (userReceivedValuesIsNotEmpty(name, login, password, email)) {
                if (UserStore.STORE.validateAllFieldsForUserCreation(login, name, email)) {
                    user = new User(id, name, login, password, email, role, address, listOfMusicTypes);
                    try {
                        USER_DAO.insert(user);
                        int realIdFromDB = UserStore.STORE.getUserId(name, password);
                        User userForMap = new User(realIdFromDB, name, login, password, email,
                                role, address, listOfMusicTypes);
                        usersFromAttr.put(userForMap.getName() + userForMap.getLogin(), userForMap);
                        session.setAttribute("usersMap", usersFromAttr);
                        session.setAttribute("validateError", "");
                        CopyOnWriteArrayList<User> users = USER_DAO.getAll();
                        session.setAttribute("users", users);
                    } catch (DaoException daoExp) {
                        LOG.error("Problem with user insertion in insert servlet class {}", daoExp);
                    }
                    req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
                    LOG.debug("Add user to database with name {} and login {}", user.getName(), user.getLogin());
                } else {
                    session.setAttribute("validateError", "User are already exist! Or invalidate data");
                }
                req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
            }
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
