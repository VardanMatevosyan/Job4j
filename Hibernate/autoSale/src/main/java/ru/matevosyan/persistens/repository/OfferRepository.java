package ru.matevosyan.persistens.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.matevosyan.controllers.crud.AddOffer;
import ru.matevosyan.entity.Offer;
import ru.matevosyan.servise.HiberFactory;

import java.util.List;

/**
 * Offer repository.
 * contains all operation with offer object.
 */
public class OfferRepository implements IOffer {
    private static final Logger LOG = LoggerFactory.getLogger(AddOffer.class.getName());

    /**
     * OfferRepository class default constructor.
     */
    public OfferRepository() {
    }

    @Override
    public void add(Offer offer) throws RepositoryException {
        Session session =  HiberFactory.HIBERNT.getFactory().openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(offer);
            session.getTransaction().commit();
        } catch (RepositoryException repoExp) {
            LOG.error("Problem with adding offer obj to the database in OfferRepository class {}", repoExp);
            session.getTransaction().rollback();
            throw repoExp;
        } finally {
            session.close();
        }
    }

    @Override
    public List getOffers()  throws RepositoryException {
        List offers;
        Session session =  HiberFactory.HIBERNT.getFactory().openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Offer as o"
                    + " inner join fetch o.user as u"
                    + " inner join fetch o.car as c");
            offers = query.list();
            session.getTransaction().commit();
        } catch (RepositoryException repoExp) {
            LOG.error("Problem with getting offer obj from the database in OfferRepository class {}", repoExp);
            session.getTransaction().rollback();
            throw repoExp;
        } finally {
            session.close();
        }
        return offers;
    }

    @Override
    public String getImagePath()throws RepositoryException {
        String path;
        Session session = HiberFactory.HIBERNT.getFactory().openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select picture from Offer");
            path = (String) query.getSingleResult();
            session.getTransaction().commit();
        } catch (RepositoryException repoExp) {
            LOG.error("Problem with getting pcture from offer table from the database in OfferRepository class {}", repoExp);
            session.getTransaction().rollback();
            throw repoExp;
        } finally {
            session.close();
        }
        return path;
    }

    @Override
    public void changeSellState(Boolean status, Integer offerId) {
        Session session =  HiberFactory.HIBERNT.getFactory().openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("UPDATE Offer SET soldState=:state WHERE id=:offerId");
            query.setParameter("offerId", offerId);
            query.setParameter("state", status);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (RepositoryException repoExp) {
            LOG.error("Problem with change sell status obj to the database in OfferRepository class {}", repoExp);
            session.getTransaction().rollback();
            throw repoExp;
        } finally {
            session.close();
        }
    }
}
