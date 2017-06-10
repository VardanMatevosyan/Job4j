package ru.matevosyan;

import java.util.Calendar;

/**
 * User class.
 * Created on 07.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class WithoutOverridingUser {


    private String name;
    private int children;
    private Calendar birthday;

    /**
     * Constructor.
     */

    public WithoutOverridingUser (String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    
}