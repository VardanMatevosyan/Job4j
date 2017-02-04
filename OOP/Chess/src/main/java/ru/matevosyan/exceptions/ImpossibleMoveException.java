package ru.matevosyan.exceptions;

/**
 * Created ImpossibleMoveException for throwing exception when figure movement is impossible.
 * Created on 29.01.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class ImpossibleMoveException extends Exception {
    public ImpossibleMoveException(String message) {
        super(message);
    }
}
