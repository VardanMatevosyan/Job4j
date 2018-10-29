package ru.matevosyan.persistens.repository;

import org.hibernate.query.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;
import ru.matevosyan.entity.Offer;
import ru.matevosyan.utils.SessionManager;

import java.util.List;

/**
 * Offer repository.
 * contains all operation with offer object.
 */
@Repository
public class OfferRepository implements IOffer<Offer> {
    private static final Logger LOG = LoggerFactory.getLogger(OfferRepository.class.getName());
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
            Query query = session.createQuery("FROM Offer AS o "
                    + " inner join fetch o.user AS u "
                    + " inner join fetch o.car AS c "
                    + " WHERE o.postingDate = current_date() ");
            return (List<Offer>) query.list();
        });
    }

    @Override
    public List<Offer> getOffersWithPhoto() {
        return SESSION_MANAGER.useAndReturn(session -> {
            Query query = session.createQuery("FROM Offer AS o "
                    + " inner join fetch o.user AS u "
                    + " inner join fetch o.car AS c "
                    + " WHERE o.picture not like '%default.jpeg'");
            return (List<Offer>) query.list();
        });
    }

    @Override
    public List<Offer> getOffersByBrand(List brands) {
        return SESSION_MANAGER.useAndReturn(session -> {
            Query query = session.createQuery("FROM Offer AS o "
                    + " inner join fetch o.user AS u "
                    + " inner join fetch o.car AS c "
                    + " WHERE c.brand IN (:brands)");
            query.setParameterList("brands", brands);
            return (List<Offer>) query.list();
        });
    }
}
