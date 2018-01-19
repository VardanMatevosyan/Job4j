package ru.matevosyan.model;

/**
 * This class was created for determine the type of Item, that's why we extends from Item.
 * Created on 15.11.2016.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */
public class Bug extends Item {

    /**
     * Created for override the constructor we have extends from the class Item.
     * Created on 15.11.2016.
     * @since 1.0
     * @param name which is the Bugs name
     * @param desc which is the Bugs description
     */

    public Bug(String name, String desc) {
        super(name, desc);
    }

}
