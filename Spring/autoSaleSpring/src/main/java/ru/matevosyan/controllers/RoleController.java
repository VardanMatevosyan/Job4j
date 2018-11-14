package ru.matevosyan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.matevosyan.entity.Role;
import ru.matevosyan.repository.RoleDataRepository;
import javax.annotation.PostConstruct;

/**
 * Controller for role entity.
 */
@Controller
public class RoleController {
    private final RoleDataRepository<Role> roleDataRepository;
    private static final String USER = "user";
    private static final String ADMIN = "admin";

    /**
     * RoleController constructor.
     * @param roleDataRepository object.
     */
    @Autowired
    public RoleController(final RoleDataRepository<Role> roleDataRepository) {
        this.roleDataRepository = roleDataRepository;
    }

    /**
     * Add roles to the database.
     */
    @PostConstruct
    public void addRoles() {
        Role user = new Role();
        user.setName(USER);
        Role admin = new Role();
        admin.setName(ADMIN);

        int size = this.roleDataRepository.findAll().size();
        if (size == 0) {
            this.roleDataRepository.save(admin);
            this.roleDataRepository.save(user);
        }

    }
}
