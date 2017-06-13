package ru.matevosyan;

import java.util.Calendar;

/**
 * OverrideHashcodeUser class.
 * Created on 07.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class OverrideHashcodeUser {


    private final String name;
    private final int children;
    private final Calendar birthday;

    /**
     * Constructor.
     */

    public OverrideHashcodeUser(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }


    @Override
    public int hashCode() {
        int code = 17;
        int result = code * 31 * (this.name != null ? this.name.hashCode() : 0) + children;
        result = result * code * 31 + (this.birthday != null ? this.birthday.hashCode() : 0);
        return result;
    }

}