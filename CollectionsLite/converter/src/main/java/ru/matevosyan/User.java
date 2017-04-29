package ru.matevosyan;


/**
 * User class for converter project.
 * Created on 28.04.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class User {

    private final int id;
    private final String name;
    private final String city;

    /**
     * User class constructor.
     * @param id User id.
     * @param name User name.
     * @param city User city.
     */

    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    /**
     * Getter to get User id.
     * @return User id.
     */

    public int getId() {
        return id;
    }

    /**
     * Getter to get User name.
     * @return User name.
     */

    public String getName() {
        return name;
    }

    /**
     * Getter to get User city.
     * @return User city.
     */

    public String getCity() {
        return city;
    }

}
