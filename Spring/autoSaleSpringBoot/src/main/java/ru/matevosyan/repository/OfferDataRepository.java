package ru.matevosyan.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.matevosyan.entity.Offer;

import java.sql.Timestamp;
import java.util.List;

/**
 * Repository for Offer entity.
 * @param <T> Offer and child.
 */
@Repository
public interface OfferDataRepository<T extends Offer> extends JpaRepository<Offer, Integer> {

    /**
     * Update sell status.
     * @param state sell or sold.
     * @param offerId id.
     * @return 1 if value changed.
     */
    @Modifying
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @Query(value = "UPDATE Offer SET soldState = :state WHERE id = :offerId")
    Integer changeSellState(@Param("state") Boolean state, @Param("offerId") Integer offerId);

    /**
     * Find offer for the last day.
     * @param start date time.
     * @param end date time
     * @return list of offers.
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    List<Offer> findByPostingDateBetweenOrderByPostingDateDesc(Timestamp start, Timestamp end);

    /**
     * find all offers by car brand.
     * @param brands list of car brands.
     * @return list of offers.
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    List<Offer> findAllByCarBrandInOrderByPostingDateDesc(List<String> brands);

    /**
     * find offers by picture.
     * @param name default picture.
     * @return list of offers.
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    List<Offer> findByPictureNotContainingOrderByPostingDateDesc(String name);

    /**
     * find all offers.
     * @return list of offers.
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @Query(value = "FROM Offer as o inner join fetch o.user as u inner join fetch o.car as c")
    List<Offer> findAllOffers();

}

