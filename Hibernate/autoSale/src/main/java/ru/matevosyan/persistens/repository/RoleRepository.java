package ru.matevosyan.persistens.repository;

import org.hibernate.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.matevosyan.controllers.crud.AddOffer;
import ru.matevosyan.entity.Role;
import ru.matevosyan.servise.HiberFactory;

import java.util.List;

/**
 * Role repository class.
 * contains all method deal with role object.
 */
public class RoleRepository implements IRole {
    private static final Logger LOG = LoggerFactory.getLogger(AddOffer.class.getName());
    private static final String USER = "user";
    private static final String ADMIN = "admin";

    @Override
    public void add() throws RepositoryException {
        Session session = HiberFactory.HIBERNT.getFactory().openSession();
        try {
            session.beginTransaction();
            Role user = new Role();
            user.setName(USER);
            Role admin = new Role();
            admin.setName(ADMIN);
            List list = session.createQuery("from Role").list();
            if (list.size() == 0) {
                session.saveOrUpdate(user);
                session.saveOrUpdate(admin);
                session.getTransaction().commit();
            }
        } catch (RepositoryException repoExp) {
            LOG.error("Problem with adding role values to the database in RoleRepository class {}", repoExp);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }
}
