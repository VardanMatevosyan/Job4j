package ru.matevosyan.exceptions;

/**
 * Created FigureNotFoundException for throwing exception when figure is not found.
 * Created on 29.01.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class FigureNotFoundException extends Exception {

    /**
     * FigureNotFoundException constructor.
     * @param msg inform user if catch exception.
     */

    public FigureNotFoundException(String msg) {
        super(msg);
    }
}
