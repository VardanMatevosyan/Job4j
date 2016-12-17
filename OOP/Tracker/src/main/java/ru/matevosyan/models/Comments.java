package ru.matevosyan.models;

/**
 * This class was created for Comments, that's hold all comments together inside in item.
 * Created on 15.11.2016.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 *
 */

public class Comments extends Item {

    /**
     * Instance variable comments for saving an instance to every single typing comment.
     */

    private String comment;

    /**
     * default constructor.
     */

    public Comments() {

    }

    /**
     * Constructor for assign every single user comments.
     * @param comment it is the variable that pass comment to constructor
     */

    public Comments(String comment) {
        this.comment = comment;
    }

    public String getCommentName() {
        return this.comment;
    }
    /**
     * override toString method for print out formatting comments.
     * @return the formatting line
     */

    @Override
    public String toString() {
        return String.format("%s", this.comment);
    }
}
