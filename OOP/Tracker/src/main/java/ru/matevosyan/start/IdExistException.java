package ru.matevosyan.start;

/**
 * Created for valid input data.
 * Created on 27.12.2016.
 * @since 1.0
 * @version 1.0
 * @author Matevosyan Vardan
 */
public class IdExistException extends  RuntimeException{
    public IdExistException(String msg) {
        super(msg);
    }
}
