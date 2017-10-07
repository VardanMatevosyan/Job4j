package ru.matevosyan.exception;


/**
 * NotEnoughMoney class for exceptions when in user account is not enough money to transfer.
 * @author Matevosyan Vardan.
 * @version 1.0.
 * created on 28.09.2017.
 */


public class NotEnoughMoney extends Exception {

    /**
     * NotEnoughMoney use for send message to user if method catch exception.
     * @param msg to send message to Exception constructor.
     */

    public NotEnoughMoney(String msg) {
        super(msg);
    }
}
