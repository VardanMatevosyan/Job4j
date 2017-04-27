package ru.matevosyan;

public class Account {

    private double value;
    private String requisites;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setRequisites(String requisites) {
        this.requisites = requisites;
    }

    public String getRequisites() {
        return requisites;
    }

    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

}
