package ru.matevosyan.exceptions;

/**
 * Created OccupiedWayException for throwing exception when way is occupied.
 * Created on 29.01.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class OccupiedWayException extends Exception {

    /**
     * OccupiedWayException constructor.
     * @param message inform user if catch exception.
     */

    public OccupiedWayException(String message) {
        super(message);
    }
}
