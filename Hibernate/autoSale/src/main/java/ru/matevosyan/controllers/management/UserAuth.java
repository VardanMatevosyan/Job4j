package ru.matevosyan.controllers.management;

import ru.matevosyan.entity.User;
import ru.matevosyan.persistens.repository.OfferRepository;
import ru.matevosyan.persistens.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * UserAuth class is the servlet that manage the path to with the user should be forwarded.
 */
public class UserAuth extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        boolean pass = false;
        Optional<User> userObj = Optional.empty();
        String name = req.getParameter("login");
        String password = req.getParameter("password");

        if ((name == null || password == null) && req.getSession().getAttribute("currentUser") == null) {
            req.getRequestDispatcher("/WEB-INF/views/signIn.jsp").forward(req, resp);
        } else {
            UserRepository userRepository = new UserRepository();
            pass = userRepository.userCredential(name, password);

            if (pass) {
                userObj = userRepository.loadCurrentUser(name);
            } else {
                req.setAttribute("userCredential", "Please, sign up first!");
                req.getRequestDispatcher("/WEB-INF/views/signIn.jsp").forward(req, resp);
            }
        }


        if (userObj.isPresent()) {
            OfferRepository offerRepository = new OfferRepository();
            User user = userObj.get();
            req.getSession().setAttribute("currentUser", user);
            req.getSession().setAttribute("offers", offerRepository.getOffers());
            if (pass && user.getRole().getName().equals("user")) {
                req.getRequestDispatcher("/WEB-INF/views/user.jsp").forward(req, resp);
            } else if (pass && user.getRole().getName().equals("admin")) {
                req.getRequestDispatcher("/WEB-INF/views/admin.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/WEB-INF/views/signIn.jsp").forward(req, resp);
            }
        } else {
            req.getRequestDispatcher("/WEB-INF/views/signIn.jsp").forward(req, resp);
        }

    }
}
