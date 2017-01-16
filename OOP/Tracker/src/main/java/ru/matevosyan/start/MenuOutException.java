package ru.matevosyan.start;

/**
 * Created for valid input data.
 * Created on 27.12.2016.
 * @since 1.0
 * @version 1.0
 * @author Matevosyan Vardan
 */

public class MenuOutException extends RuntimeException {

    /**
     * MenuOutException use for send message to user if method catch exception.
     * @param msg to send message to RuntimeException constructor.
     */

    public MenuOutException(String msg) {
        super(msg);
    }

}
