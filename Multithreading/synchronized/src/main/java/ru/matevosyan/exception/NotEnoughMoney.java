package ru.matevosyan.exception;


/**
 * NotEnoughMoney class for exceptions when in user account is not enough money to transfer.
 * @author Matevosyan Vardan.
 * @version 1.0.
 * created on 28.09.2017.
 */

<<<<<<< HEAD
public class NotEnoughMoney extends RuntimeException {

    /**
     * NotEnoughMoney use for send message to user if method catch exception.
     * @param msg to send message to RuntimeException constructor.
=======
public class NotEnoughMoney extends Exception {

    /**
     * NotEnoughMoney use for send message to user if method catch exception.
     * @param msg to send message to Exception constructor.
>>>>>>> origin/master
     */

    public NotEnoughMoney(String msg) {
        super(msg);
    }
}
