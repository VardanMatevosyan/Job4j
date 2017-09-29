package ru.matevosyan;

import org.hamcrest.EasyMock2Matchers;
import org.junit.Test;
import ru.matevosyan.exception.NotEnoughMoney;
import ru.matevosyan.exception.UserDoesNotExist;

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
     * @throws RuntimeException get if method join throw exception.
     */

    @Test
    public void WhenTryToSubMoreThanItShouldBeAmountFromTheFirstUserThanGetRuntimeException()
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

}