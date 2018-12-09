package ru.matevosyan.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.matevosyan.entity.Role;
import ru.matevosyan.repository.UserDataRepository;
import ru.matevosyan.entity.User;
import javax.annotation.PostConstruct;
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
    private final UserDataRepository<User> userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class.getName());

    /**
     * UserController constructor.
     *
     * @param userRepository object.
     * @param bCryptPasswordEncoder object.
     */
    @Autowired
    public UserController(UserDataRepository<User> userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * Add root user with admin role to the database.
     */
    @PostConstruct
    public void addUserRoot() {
        String name = "root";
        Role role = new Role();
        role.setId(1);
        role.setName("ROLE_ADMIN");
        User root = new User();
        root.setRole(role);
        root.setName("root");
        root.setPassword(bCryptPasswordEncoder.encode("root"));
        root.setCity("Moscow");
        root.setPhoneNumber("89261234567");
        root.setEnabled(true);

        int size = this.userRepository.findAllUserByName(name).size();
        if (size == 0) {
            this.userRepository.save(root);
        }
        LOG.info("Add ROOT User to the Database {}", root);
    }

    /**
     * Sign up the user.
     *
     * @param user model.
     * @return signIn view.
     */
    @PostMapping(value = "/signUp")
    protected String signUp(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        this.userRepository.save(user);
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
    protected ModelAndView filtering(ModelAndView modelAndView, HttpSession httpSession) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Set<String> roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        if (roles.contains("ROLE_USER")) {
            modelAndView.setViewName("user");
            httpSession.setAttribute("currentUser", this.userRepository.findUserByName(auth.getName()));
            return modelAndView;
        } else if (roles.contains("ROLE_ADMIN")) {
            modelAndView.setViewName("admin");
            httpSession.setAttribute("currentUser", this.userRepository.findUserByName(auth.getName()));
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
    protected String auth(@RequestParam(value = "error", required = false) String error,
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
