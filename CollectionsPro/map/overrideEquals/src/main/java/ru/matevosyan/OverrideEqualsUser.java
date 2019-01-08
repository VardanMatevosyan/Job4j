package ru.matevosyan;

import java.util.Calendar;
import java.util.Random;

/**
 * OverrideEqualsUser class.
 * Created on 07.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class OverrideEqualsUser {
    private final String name;
    private final int children;
    private final Calendar birthday;

    /**
     * Constructor.
     * @param name user name.
     * @param children user children.
     * @param birthday user birthday.
     */

    public OverrideEqualsUser(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        OverrideEqualsUser that = (OverrideEqualsUser) o;

        if (this.name != null ? !this.name.equals(that.name) : that.name != null) {
            return false;
        }
        if (this.children != that.children) {
            return false;
        }
        return  birthday != null ? birthday.equals(that.birthday) : that.birthday == null;
    }

    @Override
    public int hashCode() {
        int result = new Random().nextInt(1_999_999_999);
        return result;
    }
}