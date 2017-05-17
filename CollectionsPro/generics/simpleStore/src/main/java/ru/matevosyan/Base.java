package ru.matevosyan;

/**
 * abstract class Base.
 * Created on 16.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public abstract class Base {

    /**
     * Base id.
     */

    private String id;

    /**
     * Getter for id field.
     * @return base id.
     */

    String getId() {
        return this.id;
    }

    /**
     * Setter for id field.
     * @param id base id.
     */

    void setId(String id) {
        this.id = id;
    }
}
