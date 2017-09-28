package ru.matevosyan;

import org.hamcrest.EasyMock2Matchers;
import org.junit.Test;
import ru.matevosyan.exception.NotEnoughMoney;
import ru.matevosyan.exception.UserDoesNotExist;

import javax.jws.soap.SOAPBinding;

import java.util.Map;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * To test UserStorage class.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 28.09.2017
 */

public class UserStorageTest {

    /**
     * Start amount:
     * userFirst = 100.
     * userSecond = 200.
     *
     * Subtract 50 from first ro second account and in another thread.\
     * subtract from the second account to first 100.
     * It should be the same 150 amount in both account.
     *
     * @throws NotEnoughMoney if users account balance is negative.
     * @throws UserDoesNotExist if user doesn't exist in the storage.
     * @throws InterruptedException get if method join throw exception.
     */

    @Test
    public void WhenSubFromFirstThreadToSecondAndBackwardsThanBothGetOneHundredFifty()
            throws UserDoesNotExist, NotEnoughMoney, InterruptedException {

        UserStorage storage = new UserStorage();

        User userFirst = new User(1, 100);
        User userSecond = new User(2, 200);

        storage.add(userFirst);
        storage.add(userSecond);

        Thread threadFirst = new Thread(new Runnable() {
            @Override
            public void run() {
                storage.transfer(1, 2, 50);
            }
        });

        Thread threadSecond = new Thread(new Runnable() {
            @Override
            public void run() {
                storage.transfer(2, 1, 100);
            }
        });

        threadFirst.start();
        threadSecond.start();

        threadFirst.join();
        threadSecond.join();


        assertThat(storage.getTotalAmount(), is(300));
        assertThat(storage.getUserList().get(1).getAmount(), is(150));
        assertThat(storage.getUserList().get(2).getAmount(), is(150));

    }

    /**
     * Start amount:
     *  userFirst = 40.
     *  userSecond = 200.
     * When subtract more (50) than it should be in the userFirst account than get RuntimeException.
     */

    @Test
    public void WhenTryToSubMoreThanItShouldBeAmountFromTheFirstUserThanGetNotEnoughMoney()
            throws NotEnoughMoney {

        Throwable e = null;
        UserStorage storage = new UserStorage();

        User userFirst = new User(1, 40);
        User userSecond = new User(2, 200);

        storage.add(userFirst);
        storage.add(userSecond);

        try {
            storage.transfer(1, 2, 50);
        } catch (Throwable ex) {
            e = ex;
        }

        assertTrue(e instanceof NotEnoughMoney);
    }

    /**
     * Start amount:
     *  userFirst = 40.
     *  userSecond = 200.
     *
     * Try to add twice user with the same id to the storage.
     * than if the id is the same get UserDoesNotExist exception.
     *
     */

    @Test
    public void WhenTryToAddExistUserToTheStorageThanGetUserDoesNotExist() {

        Throwable e = null;
        UserStorage storage = new UserStorage();

        User userFirst = new User(1, 40);
        User userSecond = new User(1, 200);

        try {
            storage.add(userFirst);
            storage.add(userSecond);
        } catch (Throwable ex) {
            e = ex;
        }

        assertTrue(e instanceof UserDoesNotExist);
    }

    /**
     * Start amount:
     *  user= 40.
     *
     * Try to delete not exist user.
     * than if the user is not exist get UserDoesNotExist exception.
     *
     */

    @Test
    public void WhenTryToDeleteNotExistUserToTheStorageThanGetUserDoesNotExist() {

        Throwable e = null;
        UserStorage storage = new UserStorage();

        User user = new User(1, 40);

        try {
            storage.delete(user);
        } catch (Throwable ex) {
            e = ex;
        }

        assertTrue(e instanceof UserDoesNotExist);
    }

    /**
     * Start amount:
     *  user = 40.
     *
     * Try to update not exist user.
     * than if the user is not exist get UserDoesNotExist exception.
     *
     */

    @Test
    public void WhenTryToUpdateNotExistUserInTheStorageThanGetUserDoesNotExist() {

        Throwable e = null;
        UserStorage storage = new UserStorage();

        User user = new User(1, 40);

        try {
            storage.update(user);
        } catch (Throwable ex) {
            e = ex;
        }

        assertTrue(e instanceof UserDoesNotExist);
    }


    /**
     * Start amount:
     * userFirst = 100.
     *
     * Test {@link UserStorage#update(User)} and check the update user amount.
     *
     * @throws NotEnoughMoney if users account balance is negative.
     * @throws UserDoesNotExist if user doesn't exist in the storage.
     * @throws InterruptedException get if method join throw exception.
     */

    @Test
    public void WhenUpdateFirstUserAmountToTenAndUpdateInSecondThreadToFiveThanGetLastAmountFiveForFirstUser()
            throws UserDoesNotExist, NotEnoughMoney, InterruptedException {

        UserStorage storage = new UserStorage();

        User userFirst = new User(1, 100);
        User userToUpdate = new User(1, 10);
        User userToUpdate2 = new User(1, 5);

        storage.add(userFirst);

        Thread threadFirst = new Thread(new Runnable() {
            @Override
            public void run() {
                storage.update(userToUpdate);
            }
        });

        Thread threadSecond = new Thread(new Runnable() {
            @Override
            public void run() {
                storage.update(userToUpdate2);
            }
        });

        threadFirst.start();
        threadSecond.start();

        threadFirst.join();
        threadSecond.join();

        int actual = storage.getUserList().get(1).getAmount();

        assertTrue(actual != 100 & actual == 5 || actual == 10);
    }

    /**
     * Start amount:
     * userFirst = 100.
     * userSecond = 10.
     *
     * Test {@link UserStorage#delete(User)}  and check the less user id and amount.
     * user id should be 1 and amount 100
     *
     * @throws NotEnoughMoney if users account balance is negative.
     * @throws UserDoesNotExist if user doesn't exist in the storage.
     */

    @Test
    public void WhenAddTwoUsersThanDeleteFirstUserThanCheckTheStorageHoldOneUser()
            throws UserDoesNotExist, NotEnoughMoney {

        UserStorage storage = new UserStorage();

        User userFirst = new User(1, 100);
        User userSecond = new User(2, 10);


        storage.add(userFirst);
        storage.add(userSecond);

        storage.delete(userSecond);
        int actualUserId = storage.getUserList().get(1).getId();
        int acrualUserAmount = storage.getUserList().get(1).getAmount();

        int expectedUserId = 1;
        int expectedUserAmount = 100;

        assertThat(actualUserId, is(expectedUserId));
        assertThat(acrualUserAmount, is(expectedUserAmount));
    }

    /**
     * Start amount:
     * userFirst = 100.
     * userSecond = 10.
     *
     * Test {@link UserStorage#add(User)}  and check total user id and amount.
     * total user id should be 6 and amount 160
     *
     * @throws NotEnoughMoney if users account balance is negative.
     * @throws UserDoesNotExist if user doesn't exist in the storage.
     */

    @Test
    public void WhenAddThreeUsersThanCheckIfTheyAreInTheStorage()
            throws UserDoesNotExist, NotEnoughMoney {

        UserStorage storage = new UserStorage();

        User userFirst = new User(1, 100);
        User userSecond = new User(2, 10);
        User userThird = new User(3, 50);


        storage.add(userFirst);
        storage.add(userSecond);
        storage.add(userThird);

        int actualTotalUserId = 0;
        int actualTotalAmount = 0;

        int expectedTotalUserId = 6;
        int expectedTotalUserAmount = 160;

        for (Map.Entry<Integer, User> user : storage.getUserList().entrySet()) {
            actualTotalUserId += user.getKey();
            actualTotalAmount += user.getValue().getAmount();
        }

        assertThat(actualTotalUserId, is(expectedTotalUserId));
        assertThat(actualTotalAmount, is(expectedTotalUserAmount));
    }
}