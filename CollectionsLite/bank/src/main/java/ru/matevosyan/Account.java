package ru.matevosyan;

/**
 * Created user account to be able to transfer amount ich other.
 * Created on 28.04.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class Account {

    private double value;
    private String requisites;

    /**
     * Created getValue() to get value if needed.
     * @return value is an amount.
     */

    public double getValue() {
        return value;
    }

    /**
     * Created setValue(double value) to be able to set value if needed.
     * @param value is an amount.
     */

    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Created setRequisites(String requisites) to get account requisites if needed.
     * @param requisites is an account requisites.
     */

    public void setRequisites(String requisites) {
        this.requisites = requisites;
    }

    /**
     * Created getRequisites() to be able to set requisites if needed.
     * @return  requisites is an account requisites.
     */

    public String getRequisites() {
        return requisites;
    }

    /**
     * Account constructor.
     * @param value is an amount.
     * @param requisites is an account requisites.
     */

    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

}
