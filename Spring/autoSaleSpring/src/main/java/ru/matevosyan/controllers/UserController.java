package ru.matevosyan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import ru.matevosyan.utils.FileUploader;
import ru.matevosyan.entity.Offer;
import ru.matevosyan.entity.User;
import ru.matevosyan.persistens.repository.IOffer;
import ru.matevosyan.persistens.repository.IUser;
import ru.matevosyan.persistens.repository.UserRepository;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Controller for actions wit user model.
 */
@Controller
@MultipartConfig
public class UserController {
    private final IOffer<Offer> offerRepository;
    private final IUser<User> userRepository;
    private final FileUploader uploader;

    /**
     * UserController constructor.
     * @param offerRepository object.
     * @param userRepository object.
     * @param uploader object.
     */
    @Autowired
    public UserController(IOffer<Offer> offerRepository, IUser<User> userRepository, FileUploader uploader) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.uploader = uploader;
    }

    /**
     * Sign up the user.
     * @param user model.
     * @return signIn view.
     */
    @PostMapping(value = "/signUp")
    protected String signUp(@RequestBody User user) {
        this.userRepository.add(user);
        return "signIn";
    }

    /**
     * Sign out user from the system.
     * @param request HttpServletRequest for removing attributes.
     * @return signIn view.
     */
    @GetMapping(value = "/signOut")
    protected String signOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        synchronized (session) {
            session.removeAttribute("offers");
            session.removeAttribute("currentUser");
            session.invalidate();
            return "signIn";
        }
    }

    /**
     * Filtering to which view show.
     * @param request HttpServletRequest.
     * @return view.
     */
    @GetMapping(value = "/")
    protected String filtering(HttpServletRequest request) {
        if (request.getRequestURI().contains(request.getContextPath())
                && request.getRequestURI().endsWith("/")
                && request.getSession().getAttribute("currentUser") == null) {
            return "signIn";
        }  else if (request.getSession().getAttribute("currentUser") != null) {
            User currentUser = (User) request.getSession().getAttribute("currentUser");

            if (currentUser.getRole().getName().equals("user")) {
                return "user";
            } else if (currentUser.getRole().getName().equals("admin")) {
                return "admin";
            }
        }

      return "signIn";
    }

    /**
     * Sign in the user.
     * @param req HttpServletRequest.
     * @return the view.
     */
    @GetMapping(value = "/signIn")
    protected String auth(HttpServletRequest req) {
        boolean pass = false;
        Optional<User> userObj = Optional.empty();
        String name = req.getParameter("login");
        String password = req.getParameter("password");

        if ((name == null || password == null) && req.getSession().getAttribute("currentUser") == null) {
            return "signIn";
        } else {
            UserRepository userRepository = new UserRepository();
            pass = userRepository.userCredential(name, password);

            if (pass) {
                userObj = userRepository.loadCurrentUser(name);
            } else {
                req.setAttribute("userCredential", "Please, sign up first!");
                return "signIn";
            }
        }

        if (userObj.isPresent()) {
            User user = userObj.get();
            req.getSession().setAttribute("currentUser", user);
            if (user.getRole().getName().equals("user")) {
                return "user";
            } else if (user.getRole().getName().equals("admin")) {
                return "admin";
            } else {
                return "signIn";
            }
        } else {
            return "signIn";
        }

    }

}
