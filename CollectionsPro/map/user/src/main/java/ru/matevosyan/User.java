package ru.matevosyan;

import java.util.Calendar;

/**
 * User class.
 * Created on 07.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class User {


    private String name;
    private int children;
    private Calendar birthday;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (children != user.children) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return birthday != null ? birthday.equals(user.birthday) : user.birthday == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + children;
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }

    /**
     * Constructor.
     */


    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    
}