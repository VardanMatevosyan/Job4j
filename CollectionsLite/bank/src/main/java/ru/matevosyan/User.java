package ru.matevosyan;

/**
 * User class for bank project.
 * Created on 28.04.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class User {

    private final String name;
    private final String passport;

    /**
     * User class constructor.
     * @param name User name.
     * @param passport User passport.
     */

    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    /**
     * Getter to get User name.
     * @return User name.
     */

    public String getName() {
        return name;
    }

    /**
     * Getter to get User passport.
     * @return User passport.
     */

    public String getPassport() {
        return passport;
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

        User user = (User) o;

        if (name != null ? !name.equals(user.name) : user.name != null) {
            return false;
        }

        return passport != null ? passport.equals(user.passport) : user.passport == null;

    }

    /**
     * Override hashCode method to compute hash value to identify  object.
     * @return object's hash code.
     */

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (passport != null ? passport.hashCode() : 0);
        return result;
    }


}
