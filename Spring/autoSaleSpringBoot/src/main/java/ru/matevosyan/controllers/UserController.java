package ru.matevosyan.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.matevosyan.entity.User;
import ru.matevosyan.services.UserService;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Controller for actions wit user model.
 */
@Controller
@MultipartConfig
public class UserController {
    private final UserService<User> service;
    private final PasswordEncoder bCryptPasswordEncoder;
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class.getName());

    /**
     * UserController constructor.
     *
     * @param service object.
     * @param bCryptPasswordEncoder object.
     */
    @Autowired
    public UserController(UserService<User> service, PasswordEncoder bCryptPasswordEncoder) {
        this.service = service;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    /**
     * Sign up the user.
     *
     * @param user model.
     * @return signIn view.
     */
    @PostMapping(value = "/signUp")
    public String signUp(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        this.service.save(user);
        LOG.info("Sign up User in the Database {}", user);
        return "signIn";
    }

    /**
     * Filtering to which view show.
     *
     * @param modelAndView modelAndView object.
     * @param httpSession http session.
     * @return view.
     */
    @GetMapping(value = "/")
    public ModelAndView filtering(ModelAndView modelAndView, HttpSession httpSession) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Set<String> roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        if (roles.contains("ROLE_USER")) {
            modelAndView.setViewName("user");
            httpSession.setAttribute("currentUser", this.service.findByName(auth.getName()));
            return modelAndView;
        } else if (roles.contains("ROLE_ADMIN")) {
            modelAndView.setViewName("admin");
            httpSession.setAttribute("currentUser", this.service.findByName(auth.getName()));
            return modelAndView;
        }  else {
            modelAndView.setViewName("anonymous");
            return modelAndView;
        }
    }

    /**
     * Sign in the user.
     *
     * @param model  model.
     * @param error  error.
     * @param logout logout.
     * @return the view.
     */
    @GetMapping("/signIn")
    public String auth(@RequestParam(value = "error", required = false) String error,
                          @RequestParam(value = "logout", required = false) String logout,
                          Model model) {
        if (error != null) {
            model.addAttribute("error", "Your user name or password is invalid");
        }
        if (logout != null) {
            model.addAttribute("logout", "You have been logged out successfully");
        }
        return "signIn";
    }
}

