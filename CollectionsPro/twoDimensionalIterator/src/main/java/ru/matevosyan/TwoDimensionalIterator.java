package ru.matevosyan;

/**
 * TwoDementionIterator class.
 * Created on 28.04.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class TwoDimensionalIterator {

    private final String name;
    private final int age;

    /**
     * User class constructor.
     * @param name User name.
     * @param age User age.
     */

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Getter to get User name.
     * @return User name.
     */

    public String getName() {
        return name;
    }

    /**
     * Getter to get User age.
     * @return User name.
     */

    public int getAge() {
        return age;
    }

}
