package ru.matevosyan.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class was created for items, that's determine specific and hole description and hold it together as item.
 * Created on 15.11.2016.
 * @since 1.0
 * @author Matevosyan Vardan.
 * @version 2.0 with database.
 *
 */

public class Item {

    /**
     * Instance variable id which is the Items identifier.
     */

    private String id;

    /**
     * Set date.
     * @param create creation date.
     */
    public void setCreate(Date create) {
        this.create = create;
    }

    /**
     * Instance variable name which is the Items name.
     */

    private String name;

    /**
     * Instance variable description which is the Items description.
     */

    private String description;

    /**
     * Instance variable create Items create date.
     */

    private Date create;

    /**
     * Created for override the default constructor.
     */

    public Item() {
    }

    /**
     * Created for override the parameters we have the constructor.
     * @param name which is the Items name
     * @param description which is the Items description
     */

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Create getter for field name.
     * @return  <code>name</code>
     */

    public String getName() {

        return this.name;
    }

    /**
     * Create getter for field description.
     * @return  <code>description</code>
     */

    public String getDescription() {
        return this.description;
    }

    /**
     * Create getter for field create.
     * @return  <code>create</code>
     */

    public String getCreate() {
        String formatDate = "yyyy-MM-dd";
        SimpleDateFormat sFormat = new SimpleDateFormat(formatDate);
        return sFormat.format(this.create);
    }

    /**
     * Create setter for field id.
     * @param id  value for set the Items id
     */

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Create getter for field create.
     * @return  <code>id</code>
     */

    public String getId() {
        return this.id;
    }

    /**
     * Override toString method for print out formatting items.
     * @return the formatting line
     */

       @Override
    public String toString() {

       return String.format("Id: %s; Name: %s; Description: %s; Date: %s", this.id, this.name,
               this.description, this.create);

    }

}
