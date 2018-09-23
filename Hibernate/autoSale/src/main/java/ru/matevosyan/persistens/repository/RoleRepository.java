package ru.matevosyan.persistens.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.matevosyan.controllers.crud.AddOffer;
import ru.matevosyan.entity.Role;
import ru.matevosyan.servise.SessionManager;

import java.util.List;

/**
 * Role repository class.
 * contains all method deal with role object.
 */
public class RoleRepository implements IRole {
    private static final SessionManager SESSION_MANAGER = SessionManager.TRANSACTION;
    private static final Logger LOG = LoggerFactory.getLogger(AddOffer.class.getName());
    private static final String USER = "user";
    private static final String ADMIN = "admin";

    @Override
    public void add() throws RepositoryException {
        Role user = new Role();
        user.setName(USER);
        Role admin = new Role();
        admin.setName(ADMIN);

        SESSION_MANAGER.useAndReturn(session -> {
            List list = session.createQuery("from Role").list();
            if (list.size() == 0) {
                session.save(user);
                session.save(admin);
            }
            return list;
        });

    }
}
