package ru.matevosyan.persistens.repository;

import org.hibernate.query.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.matevosyan.controllers.crud.AddOffer;
import ru.matevosyan.entity.Offer;
import ru.matevosyan.servise.SessionManager;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Offer repository.
 * contains all operation with offer object.
 */
public class OfferRepository implements IOffer {
    private static final Logger LOG = LoggerFactory.getLogger(AddOffer.class.getName());
    private static final SessionManager SESSION_MANAGER = SessionManager.TRANSACTION;

    /**
     * OfferRepository class default constructor.
     */
    public OfferRepository() {
    }

    @Override
    public void add(Offer offer) throws RepositoryException {
        SESSION_MANAGER.useAndReturn(session -> session.save(offer));
    }

    @Override
    public List getOffers()  throws RepositoryException {
        return SESSION_MANAGER.useAndReturn(session ->
                session.createQuery("from Offer as o "
                        + " inner join fetch o.user as u "
                        + " inner join fetch o.car as c").list());

    }

    @Override
    public String getImagePath()throws RepositoryException {
        return SESSION_MANAGER.useAndReturn(session ->
                (String) session.createQuery("select picture from Offer ")
                        .getSingleResult()
        );
    }

    @Override
    public void changeSellState(Boolean status, Integer offerId) {
        SESSION_MANAGER.useAndReturn(session -> {
            Query query = session.createQuery("UPDATE Offer SET soldState=:state WHERE id=:offerId");
            query.setParameter("state", status);
            query.setParameter("offerId", offerId);
            return query.executeUpdate();
        });
    }

    @Override
    public List<Offer> getLastDayOffers() {
        return SESSION_MANAGER.useAndReturn(session -> {
            Query query = session.createQuery("FROM Offer as o WHERE year(o.postingDate) = year(:year) "
                    + "and month(o.postingDate) = month(:month) "
                    + "and day(o.postingDate) = day(:day)");

            Calendar date = new GregorianCalendar();
            int year = date.get(Calendar.YEAR);
            int month = date.get(Calendar.MONTH);
            int day = date.get(Calendar.DAY_OF_MONTH);

            query.setParameter("year", year);
            query.setParameter("month", month);
            query.setParameter("day", day);
            return (List<Offer>) query.list();
        });
    }

    @Override
    public List<Offer> getOfferWithPhoto() {
        return SESSION_MANAGER.useAndReturn(session -> {
            String defaultPhotoPatternName = "default.";
            Query query = session.createQuery("FROM Offer as o WHERE o.picture like :defaultPhotoPatternName");
            query.setParameter("defaultPhotoPatternName", String.format("%s%s%s", "%", defaultPhotoPatternName, "%s"));
            return (List<Offer>) query.list();
        });
    }

    @Override
    public List<Offer> getOffersByBrand(String brand) {
        return SESSION_MANAGER.useAndReturn(session -> {
            Query query = session.createQuery("FROM Offer as o INNER JOIN Car as c WHERE c.brand=:brand");
            query.setParameter("brand", brand);
            return (List<Offer>) query.list();
        });
    }
}
