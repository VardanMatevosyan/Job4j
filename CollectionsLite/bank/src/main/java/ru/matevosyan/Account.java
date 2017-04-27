package ru.matevosyan;

import java.util.*;

public class Account {

    private final int value;
    private final String requisites;

    public int getValue() {
        return value;
    }

    public String getRequisites() {
        return requisites;
    }

    public Account(int value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

}
