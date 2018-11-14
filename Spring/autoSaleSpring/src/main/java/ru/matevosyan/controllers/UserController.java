package ru.matevosyan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.matevosyan.entity.Role;
import ru.matevosyan.repository.UserDataRepository;
import ru.matevosyan.entity.User;

import javax.annotation.PostConstruct;
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
    private final UserDataRepository<User> userRepository;

    /**
     * UserController constructor.
     * @param userRepository object.
     */
    @Autowired
    public UserController(UserDataRepository<User> userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Add root user with admin role to the database.
     */
    @PostConstruct
    public void addUserRoot() {
        String name = "root";
        Role role = new Role();
        role.setId(1);
        role.setName("admin");
        User root = new User();
        root.setRole(role);
        root.setName("root");
        root.setPassword("root");
        root.setCity("Moscow");
        root.setPhoneNumber("89261234567");

        int size = this.userRepository.findAllUserByName(name).size();
        if (size == 0) {
            this.userRepository.save(root);
        }
    }

    /**
     * Sign up the user.
     * @param user model.
     * @return signIn view.
     */
    @PostMapping(value = "/signUp")
    protected String signUp(@RequestBody User user) {
        this.userRepository.save(user);
        return "signIn";
    }

//    /**
//     * Sign out user from the system.
//     * @param request HttpServletRequest for removing attributes.
//     * @return signIn view.
//     */
//    @GetMapping(value = "/signOut")
//    protected String signOut(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        session.removeAttribute("offers");
//        session.removeAttribute("currentUser");
//        session.invalidate();
//        return "signIn";
//    }

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
        } else if (request.getSession().getAttribute("currentUser") != null) {
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
        Optional<User> userObj;
        String name = req.getParameter("login");
        String password = req.getParameter("password");
        if ((name == null || password == null) && req.getSession().getAttribute("currentUser") == null) {
            return "signIn";
        } else {
            userObj = this.userRepository.findUserByNameAndPassword(name, password);
            if (userObj.isPresent()) {
                User user = userObj.get();
                req.getSession().setAttribute("currentUser", user);
                if (user.getRole().getName().equals("user")) {
                    return "user";
                } else {
                    return "admin";
                }
            } else {
                req.setAttribute("userCredential", "Please, sign up first!");
                return "signIn";
            }
        }
    }

}
