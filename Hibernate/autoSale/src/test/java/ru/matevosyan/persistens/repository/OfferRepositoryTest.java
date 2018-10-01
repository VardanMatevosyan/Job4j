package ru.matevosyan.persistens.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.matevosyan.entity.Car;
import ru.matevosyan.entity.Offer;
import ru.matevosyan.entity.Role;
import ru.matevosyan.entity.User;
import ru.matevosyan.servise.SessionManager;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Offer repository test for testing OfferRepository class.
 */
public class OfferRepositoryTest {
    private static final UserRepository USER_REPOSITORY = new UserRepository();
    private static final RoleRepository ROLE_REPOSITORY = new RoleRepository();
    private static final OfferRepository OFFER_REPOSITORY = new OfferRepository();
    private static final SessionManager SESSION_MANAGER = SessionManager.TRANSACTION;
    private final Offer offer1 = new Offer();
    private final Offer offer2 = new Offer();
    private final User user = new User();

    /**
     * Prepare two offer and add role and one user to the database.
     */
    @Before
    public void setUp() {
        String expectedImagePath = "C:\\dir1\\dir2\\";
        String name = "user1";
        String password = "password1";
        Role userRole = new Role();
        userRole.setId(2);
        userRole.setName("admin");

        this.user.setName(name);
        this.user.setPassword(password);
        this.user.setRole(userRole);
        this.user.setPhoneNumber("89098998998");
        this.user.setCity("NY");

        Car car1 = new Car();
        car1.setYearOfManufacture(new Timestamp(System.currentTimeMillis()));
        car1.setModelVehicle("X500");
        car1.setBrand("BMW");
        car1.setGearBox("auto");
        car1.setBodyType("sedan");
        car1.setEngineCapacity(1.9F);


        offer1.setPicture("C:\\default.jpeg");
        offer1.setSoldState(true);
        offer1.setPrice(234);
        offer1.setPostingDate(new Timestamp(System.currentTimeMillis()  - 85000000));
        offer1.setDescription("desc1");
        offer1.setCar(car1);
        offer1.setAddress("addr1");
        offer1.setTittle("tittle1");
        offer1.setUser(this.user);

        Car car2 = new Car();
        car2.setYearOfManufacture(new Timestamp(System.currentTimeMillis()));
        car2.setModelVehicle("s600");
        car2.setBrand("Mercedes");
        car2.setGearBox("auto");
        car2.setBodyType("sedan");
        car2.setEngineCapacity(1.9F);


        offer2.setPicture(expectedImagePath);
        offer2.setSoldState(true);
        offer2.setPrice(2334);
        offer2.setPostingDate(new Timestamp(System.currentTimeMillis()));
        offer2.setDescription("desc2");
        offer2.setCar(car2);
        offer2.setAddress("addr2");
        offer2.setTittle("tittle2");
        offer2.setUser(this.user);

        ROLE_REPOSITORY.add();
        USER_REPOSITORY.add(this.user);
    }

    /**
     * Delete two offer object and one user from the database.
     */
    @After
    public void tearDown() {
        SESSION_MANAGER.useAndReturn(session -> {
            session.delete(this.offer1);
            return this.offer1;
        });

        SESSION_MANAGER.useAndReturn(session -> {
            session.delete(this.offer2);
            return this.offer2;
        });

        SESSION_MANAGER.useAndReturn(session -> {
            session.delete(this.offer1.getUser());
            return this.offer1.getUser();
        });
    }

    /**
     * Add one offer object to the database the get Offer from the DB.
     * Finally check if Offer tittle is equal to the expected tittle.
     */
    @Test
    public void whenAddOfferThenCheckIfItIsInTheDB() {
        OFFER_REPOSITORY.add(this.offer1);
        List offers = OFFER_REPOSITORY.getOffers();
        Offer actualOffer = (Offer) offers.get(0);

        assertThat(this.offer1.getTittle(), is(actualOffer.getTittle()));
    }

    /**
     * Testing getOffers method.
     * Add two offer object to the database the get all Offers from the DB.
     * Finally check if Offer tittle is equal to the expected tittle.
     */
    @Test
    public void whenAddTwoOffersThenGetAllTwoOffers() {
        OFFER_REPOSITORY.add(this.offer1);
        OFFER_REPOSITORY.add(this.offer2);
        List offers = OFFER_REPOSITORY.getOffers();
        Offer actualOffer1 = (Offer) offers.get(0);
        Offer actualOffer2 = (Offer) offers.get(1);

        assertThat(this.offer1.getTittle(), is(actualOffer1.getTittle()));
        assertThat(this.offer2.getTittle(), is(actualOffer2.getTittle()));
    }

    /**
     * Testing changeSellState method.
     * Add one offer object to the database the get Offer from the DB and get id.
     * Finally, check if Offers sold state was changed.
     */
    @Test
    public void whenAddOfferThenChangeSoldStatusAfterCheckResultShouldChanged() {
        OFFER_REPOSITORY.add(this.offer1);
        Offer offer = (Offer) OFFER_REPOSITORY.getOffers().get(0);
        Integer id = offer.getId();
        OFFER_REPOSITORY.changeSellState(false, id);
        Offer afterChangeStateOfferObj = (Offer) OFFER_REPOSITORY.getOffers().get(0);

        assertThat(false, is(afterChangeStateOfferObj.getSoldState()));
    }

    /**
     * Testing getLastDayOffers method.
     * Add two offer object to the database then get list Offers from the DB invoking getLastDayOffers method.
     * Finally get the offer obj and check if Offer object is the last added object.
     */
    @Test
    public void whenAddTwoOffersThenGetTheLastOne() {
        String expectedTittle = "tittle2";
        String expectedCarBrand = "Mercedes";

        OFFER_REPOSITORY.add(this.offer1);
        OFFER_REPOSITORY.add(this.offer2);
        List<Offer> lastDayOffers = OFFER_REPOSITORY.getLastDayOffers();
        Iterator<Offer> iterator = lastDayOffers.iterator();
        Offer lastAddedOffer = (Offer) iterator.next();

        assertThat(expectedTittle, is(lastAddedOffer.getTittle()));
        assertThat(expectedCarBrand, is(lastAddedOffer.getCar().getBrand()));
    }

    /**
     * Testing getOffersWithPhoto method.
     * Add two offer object to the database then get list Offers from the DB invoking getOffersWithPhoto method.
     * Finally get the offer obj and check if Offer object do not contain default.jpeg substring.
     */
    @Test
    public void whenGettingOffersWithPhotoThenCheckIfItIsTrue() {
        int expectedSize = 1;
        OFFER_REPOSITORY.add(this.offer1);
        OFFER_REPOSITORY.add(this.offer2);
        List<Offer> offersWithPhoto = OFFER_REPOSITORY.getOffersWithPhoto();
        Offer offer = (Offer) offersWithPhoto.iterator().next();

        assertThat(expectedSize, is(offersWithPhoto.size()));
        assertFalse(offer.getPicture().contains("default.jpeg"));
    }

    /**
     * Testing getOffersByBrand method.
     * Add two offer object (one with BMW brand name) to the database.
     * then get list Offers from the DB invoking getOffersByBrand method.
     * Finally check if Offer object equal to the expected size and expected brand.
     */
    @Test
    public void whenAddTwoOffersThenGetOffersByListOfBrandsWithOneSpecialBrandAndCheckTheSpecialOne() {
        int expectedSize = 1;
        List<String> brands = new ArrayList<>();
        brands.add("BMW");
        String expectedBrand = brands.iterator().next();

        OFFER_REPOSITORY.add(this.offer1);
        OFFER_REPOSITORY.add(this.offer2);
        List<Offer> byBrandOffer = OFFER_REPOSITORY.getOffersByBrand(brands);
        Offer offer = (Offer) byBrandOffer.iterator().next();

        assertThat(expectedSize, is(byBrandOffer.size()));
        assertThat(expectedBrand, is(offer.getCar().getBrand()));
    }
}