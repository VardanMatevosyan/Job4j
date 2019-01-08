package ru.matevosyan;

/**
 * User class.
 * Created on 12.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class User extends Base implements Comparable<User> {

    private final String name;
    private final int age;

    /**
     * User class constructor.
     * @param name User name.
     * @param age User age.
     * @param id User id.
     */

    public User(String name, int age, String id) {
        this.name = name;
        this.age = age;
        setId(id);
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

    /**
     * Override compareTo(User user) method to compare users by age.
     * @param user is User object.
     * @return int value.
     */

    @Override
    public int compareTo(User user) {
        return String.valueOf(this.age).compareTo(String.valueOf(user.getAge()));
    }
}
