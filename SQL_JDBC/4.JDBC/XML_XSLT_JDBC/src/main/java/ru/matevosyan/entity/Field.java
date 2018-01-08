package ru.matevosyan.entity;

/**
 * Entity Field in the table .
 * Created on 02.01.2018
 * @author Matevosyan Vardan.
 * @version 1.0.
 */

public class Field {
    private int value;

    /**
     * Default constructor.
     */
    public Field() {
    }

    /**
     * Setter for N.
     * @param value for assign.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Getter for values.
     * @return values.
     */

    public int getValues() {
        return this.value;
    }
}
