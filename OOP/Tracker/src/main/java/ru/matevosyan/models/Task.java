package ru.matevosyan.models;

/**
 * this class was created for determine the type of Item, that's why we extends from Item.
 * Created on 15.11.2016.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class Task extends Item {

    /**
     * Created for override the constructor we have extends.
     * Created on 15.11.2016.
     * @since 1.0
     * @param name which is the Tasks name
     * @param desc which is the Tasks description
     */

    public Task(String name, String desc) {
        super(name, desc);
    }
}
