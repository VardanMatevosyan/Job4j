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
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User repository test for testing UserRepository class.
 */
public class UserRepositoryTest {
    private static final UserRepository USER_REPOSITORY = new UserRepository();
    private static final RoleRepository ROLE_REPOSITORY = new RoleRepository();
    private static final OfferRepository OFFER_REPOSITORY = new OfferRepository();
    private static final SessionManager SESSION_MANAGER = SessionManager.TRANSACTION;
    private final Offer offer1 = new Offer();
    private final Offer offer2 = new Offer();
    private final User user = new User();
    private String expectedImagePath;

    /**
     * add user role to the database.
     */
    @Before
    public void setUp() {
        ROLE_REPOSITORY.add();

        this.expectedImagePath = "C:\\dir1\\dir2\\";
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

        this.offer1.setPicture("C:\\RootPath");
        this.offer1.setSoldState(true);
        this.offer1.setPrice(234);
        this.offer1.setPostingDate(new Timestamp(System.currentTimeMillis()  - 85000));
        this.offer1.setDescription("desc1");
        this.offer1.setCar(car1);
        this.offer1.setAddress("addr1");
        this.offer1.setTittle("tittle1");
        this.offer1.setUser(user);

        Car car2 = new Car();
        car2.setYearOfManufacture(new Timestamp(System.currentTimeMillis()));
        car2.setModelVehicle("s600");
        car2.setBrand("Mersedes");
        car2.setGearBox("auto");
        car2.setBodyType("sedan");
        car2.setEngineCapacity(1.9F);

        this.offer2.setPicture(expectedImagePath);
        this.offer2.setSoldState(true);
        this.offer2.setPrice(2334);
        this.offer2.setPostingDate(new Timestamp(System.currentTimeMillis()));
        this.offer2.setDescription("desc2");
        this.offer2.setCar(car2);
        this.offer2.setAddress("addr2");
        this.offer2.setTittle("tittle2");
        this.offer2.setUser(user);
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
     * Add user to the database and then get it from DB and check if it is the same user.
     */
    @Test
    public void whenUserSignInThenGetThatUserFromTheDatabase() {
        String name = "user1";
        String password = "password1";
        Role userRole = new Role();
        userRole.setId(2);
        userRole.setName("admin");

        User notExistUser = new User();
        notExistUser.setName("notExistUserName");
        notExistUser.setPassword("notExistUserPassword");
        notExistUser.setRole(userRole);

        USER_REPOSITORY.add(this.user);
        Optional<User> currentUser = USER_REPOSITORY.loadCurrentUser(name);
        User actualUser = currentUser.orElse(notExistUser);

        assertThat(actualUser.getName(), is(this.user.getName()));
    }

    /**
     * invoke userCredential without adding user to the database and get expected false value.
     */
    @Test
    public void whenUserTryToEnterToTheSystemWithoutSignUp() {
        String name = "SimpleUser";
        String password = "Password";

        boolean actualCredential = USER_REPOSITORY.userCredential(name, password);

        assertThat(actualCredential, is(false));
    }


    /**
     * add root user to the database, after get from DB and check whether or not if is the root user.
     */
    @Test
    public void whenAddRootUserToTheDatabaseThenCheckIfItIsRootUser() {
        USER_REPOSITORY.addRoot();
        String expectedName = "root";
        User notExistUser = new User();
        notExistUser.setName("notExistUserName");
        notExistUser.setPassword("notExistUserPassword");

        Optional<User> rootUser = USER_REPOSITORY.loadCurrentUser(expectedName);
        User actualUser = rootUser.orElse(notExistUser);

        assertThat(actualUser.getName(), is(expectedName));
    }

    /**
     * Add user and 2 offers to the DB. Then, when get last added offer image path, check.
     * if last added offer image path is equal to the expected string value.
     */
    @Test
    public void whenGetLastAddedOfferImagePathThenCheckIfItIsLast() {

        USER_REPOSITORY.add(user);
        OFFER_REPOSITORY.add(offer1);
        OFFER_REPOSITORY.add(offer2);
        String actualImagePath = USER_REPOSITORY.getLastAddedOfferImagePath();

        assertThat(this.expectedImagePath, is(actualImagePath));
    }

    /**
     * Add 2 user to the DB. Load one of them by name, after check if user object have the same expected value.
     */
    @Test
    public void whenLoadUserByNameCheckIfItIsTheSameUser() {
        String name = "user1";
        String password = "password1";
        Role userRole = new Role();
        userRole.setId(2);
        userRole.setName("admin");
        User expectedUser = new User();
        expectedUser.setName(name);
        expectedUser.setPassword(password);
        expectedUser.setRole(userRole);
        expectedUser.setPhoneNumber("89098998998");
        expectedUser.setCity("NY");
        User user2 = new User();
        user2.setName("user2");
        user2.setPassword("user2Password");
        user2.setRole(userRole);
        user2.setPhoneNumber("89098998998");
        user2.setCity("LA");

        USER_REPOSITORY.add(expectedUser);
        USER_REPOSITORY.add(user2);
        Optional<User> currentUser = USER_REPOSITORY.loadCurrentUser(name);
        User actualUser = currentUser.orElse(user2);

        assertThat(expectedUser.getName(), is(actualUser.getName()));
    }
}