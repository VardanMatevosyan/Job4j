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

    /**
     * Override equals method {@link Object#equals(Object)}.
     * @param o passing object to check that all object value is the same.
     * @return boolean values: if equals true, else false.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Account account = (Account) o;

        if (Double.compare(account.value, value) != 0) {
            return false;
        }
        return requisites.equals(account.requisites);
    }

    /**
     * Override hashCode method to compute hash value to identify  object.
     * @return object's hash code.
     */

    @Override
    public int hashCode() {
        int hash;
        long temp;
        temp = Double.doubleToLongBits(value);
        hash = (int) (temp - (temp >>> 32));
        hash = 31 * hash + (this.requisites != null ? requisites.hashCode() : 0);
        return hash;

    }
}
