package ru.matevosyan.exception;

/**
 * UserDoesNotExist class for exceptions when in user doesn't exist.
 * @author Matevosyan Vardan.
 * @version 1.0.
 * created on 28.09.2017.
 */

public class UserDoesNotExist extends  RuntimeException {
    /**
     * UserDoesNotExist use for send message to user if method catch exception.
     * @param msg to send message to RuntimeException constructor.
     */

    public UserDoesNotExist(String msg) {
        super(msg);
    }
}
