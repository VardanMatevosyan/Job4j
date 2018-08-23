package ru.matevosyan.persistens.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.matevosyan.entity.Role;
import ru.matevosyan.entity.User;
import ru.matevosyan.servise.HiberFactory;

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

    /**
     * UserRepository default constructor.
     */
    public UserRepository() {

    }

    @Override
    public boolean userCredential(String name, String password) {
        Session session =  HiberFactory.HIBERNT.getFactory().openSession();
        boolean isCredential = true;
        String getUserHql = String.format("%s", "from User as u where u.name=:name and password=:password");
        try {
            session.beginTransaction();
            Query query = session.createQuery(getUserHql);
            query.setParameter("name", name);
            query.setParameter("password", password);
            session.getTransaction().commit();
            User uniqueUser = (User) query.uniqueResult();
            if (uniqueUser == null) {
                isCredential = false;
            }
        } catch (RepositoryException repoExp) {
            LOG.error("Problem with adding offer obj to the database in OfferRepository class {}", repoExp);
            session.getTransaction().rollback();
            throw repoExp;
        }  finally {
            session.close();
        }
        return isCredential;
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
        Session session = HiberFactory.HIBERNT.getFactory().openSession();
        Role role = new Role();
        role.setId(2);
        role.setName("admin");

        User root = new User();
        root.setRole(role);
        root.setName("root");
        root.setPassword("root");
        root.setCity("Moscow");
        root.setPhoneNumber("89261234567");

        try {
            List list = session.createQuery("SELECT u.name FROM User AS u WHERE u.name='root'").list();
            if (list.size() == 0) {
                session.beginTransaction();
                session.saveOrUpdate(root);
                session.getTransaction().commit();
            }
        } catch (RepositoryException repoExp) {
            LOG.error("Problem with adding role values to the database in RoleRepository class {} ", repoExp);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void add(User user) throws RepositoryException {
        Session session = HiberFactory.HIBERNT.getFactory().openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (RepositoryException repoExp) {
            LOG.error("Problem with adding user values to the database in UserRepository class {} ", repoExp);
            session.getTransaction().rollback();
            throw repoExp;
        } finally {
            session.close();
        }
    }

    @Override
    public  String getLastAddedOfferImagePath() {
        String path;
        Session session = HiberFactory.HIBERNT.getFactory().openSession();
        String getOfferImagePath = String.format("%s", "SELECT offer.picture FROM Offer AS offer order by postingDate desc");
        try {
            session.beginTransaction();
            List list = session.createQuery(getOfferImagePath).list();
            session.getTransaction().commit();
            path = (String) list.get(0);
        } catch (RepositoryException repoExp) {
            LOG.error("Problem with adding offer obj to the database in OfferRepository class {}", repoExp);
            session.getTransaction().rollback();
            throw repoExp;
        }  finally {
            session.close();
        }

     return path;
    }
}
