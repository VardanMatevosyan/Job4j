package ru.matevosyan.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.matevosyan.entity.Offer;
import ru.matevosyan.repository.OfferDataRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * Offer service class.
 * @param <T> Offer type object.
 */
@Service
public class OfferService<T extends Offer> {
    private final OfferDataRepository<T> offerRepository;

    /**
     * Constructor.
     * @param offerRepository offer repository.
     */
    @Autowired
    public OfferService(final OfferDataRepository<T> offerRepository) {
        this.offerRepository = offerRepository;
    }

    /**
     * find all offers.
     * @return list of offers.
     */
    public List<Offer> findAllOffers() {
        return this.offerRepository.findAllOffers();
    }

    /**
     * Save or update offer object.
     * @param offer object.
     * @return offer obj.
     */
    public Offer save(final Offer offer) {
        return this.offerRepository.save(offer);
    }

    /**
     * Update sell status.
     * @param sellState sell or sold.
     * @param id id.
     * @return if changed return 1.
     */
    public Integer changeSellState(final Boolean sellState, final Integer id) {
        return this.offerRepository.changeSellState(sellState, id);
    }

    /**
     * Find offer for the last day.
     * @param start date time.
     * @param end date time
     * @return list of offers.
     */
    public List<Offer> findByPostingDate(final Timestamp start, final Timestamp end) {
        return this.offerRepository.findByPostingDateBetweenOrderByPostingDateDesc(start, end);
    }

    /**
     * find all offers by car brand.
     * @param brands list of car brands.
     * @return list of offers.
     */
    public List<Offer> findAllByCarBrand(final List<String> brands) {
        return this.offerRepository.findAllByCarBrandInOrderByPostingDateDesc(brands);
    }

    /**
     * find offers by picture.
     * @param name default picture.
     * @return list of offers.
     */
    public List<Offer> findByPicture(final String name) {
        return this.offerRepository.findByPictureNotContainingOrderByPostingDateDesc(name);
    }


    /**
     * Find offer by id.
     * @param id offer id.
     * @return offer obj.
     */
    public Optional<Offer> findById(final Integer id) {
        return this.offerRepository.findById(id);
    }

    /**
     * Delete offer by id.
     * @param id offer id.
     */
    public void deleteById(final Integer id) {
        this.offerRepository.deleteById(id);
    }

    /**
     * Get offer object by id.
     * @param id offer id.
     * @return offer object.
     */
    public Offer getOne(final Integer id) {
        return this.offerRepository.getOne(id);
    }
}

