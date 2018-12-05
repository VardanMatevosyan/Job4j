package ru.matevosyan.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.matevosyan.entity.Role;
import ru.matevosyan.repository.UserDataRepository;
import ru.matevosyan.entity.User;
import javax.annotation.PostConstruct;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
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
        return "signIn";
    }

    /**
     * Filtering to which view show.
     *
     * @param request HttpServletRequest.
     * @return view.
     */
    @GetMapping(value = "/")
    protected String filtering(HttpServletRequest request) {
        Set<String> roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        if (roles.contains("ROLE_USER")) {
            return "user";
        } else if (roles.contains("ROLE_ADMIN")) {
            return "admin";
        }  else {
            return "anonymous";
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