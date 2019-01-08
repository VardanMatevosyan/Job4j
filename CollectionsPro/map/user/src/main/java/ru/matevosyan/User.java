package ru.matevosyan;

import java.util.Calendar;

/**
 * User class.
 * Created on 07.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class User {
    private final String name;
    private final int children;
    private final Calendar birthday;

    /**
     * Constructor.
     * @param name user name.
     * @param children user children.
     * @param birthday user birthday.
     */

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
}