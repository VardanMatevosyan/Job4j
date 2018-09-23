package ru.matevosyan.persistens.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.entity.Role;
import ru.matevosyan.entity.User;

import ru.matevosyan.servise.HiberFactory;
import ru.matevosyan.servise.SessionManager;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

/**
 * User repository class.
 * contains all method that deal with user object.
 */
public class UserRepository implements IUser<User> {
    private static final Logger LOG = LoggerFactory.getLogger(UserRepository.class.getName());
    private static final SessionManager SESSION_MANAGER = SessionManager.TRANSACTION;

    /**
     * UserRepository default constructor.
     */
    public UserRepository() {

    }

    @Override
    public boolean userCredential(String name, String password) {
        return SESSION_MANAGER.useAndReturn(session -> {
            Query queryCredential = session.createQuery("from User as u where u.name=:name and password=:password");
            queryCredential.setParameter("name", name);
            queryCredential.setParameter("password", password);
            return queryCredential.uniqueResult() != null;
        });

    }

    @Override
    public Optional<User> loadCurrentUser(String name) {
        Session session = HiberFactory.HIBERNT.getFactory().openSession();
        User user = null;
        try {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> userRoot = criteriaQuery.from(User.class);
            criteriaQuery.select(userRoot).where(criteriaBuilder.equal(userRoot.get("name"), name));
            Query<User> query = session.createQuery(criteriaQuery);
            user = query.getSingleResult();

            session.getTransaction().commit();
        } catch (RepositoryException repoExp) {
            LOG.error("Problem with adding offer obj to the database in OfferRepository class {}", repoExp);
            session.getTransaction().rollback();
            throw repoExp;
        }
        return Optional.ofNullable(user);
    }

    @Override
    public void addRoot() throws RepositoryException {
        Role role = new Role();
        role.setId(2);
        role.setName("admin");

        User root = new User();
        root.setRole(role);
        root.setName("root");
        root.setPassword("root");
        root.setCity("Moscow");
        root.setPhoneNumber("89261234567");

        SESSION_MANAGER.useAndReturn(session -> {
            if (session.createQuery("SELECT u.name FROM User AS u WHERE u.name='root'").list().size() == 0) {
                SESSION_MANAGER.useAndReturn(sessionToSave -> sessionToSave.save(root));
            }
            return root;
        });
    }

    @Override
    public void add(User user) throws RepositoryException {
        SESSION_MANAGER.useAndReturn(session -> session.save(user));
    }

    @Override
    public  String getLastAddedOfferImagePath() {
        return SESSION_MANAGER.useAndReturn(session -> {
            Query query = session.createQuery("SELECT offer.picture FROM Offer AS offer order by postingDate desc");
            List list = query.list();
            return (String) list.get(0);
        });
    }
}
