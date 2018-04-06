package ru.matevosyan.model;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * User class.
 * @author Matevosyan Vardan.
 * @version 1.0
 * created 13.03.2018
 */
public class User {
    private final String name;
    private final String login;
    private final String password;
    private final String email;
    private Timestamp createDate;
    private UserRole role;

    /**
     * Constructor.
     * @param name user name.
     * @param login user login.
     * @param password user password.
     * @param email user email.
     * @param role user role.
     */
    public User(final String name, final String login, final String password, final String email, final UserRole role) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.createDate = new Timestamp(System.currentTimeMillis());
        this.role = role;
    }

    /**
     * Don't use for create to insert, only if you use to get from the database.
     * Constructor for get result.
     * @param name user name.
     * @param login user login.
     * @param createDate user created date.
     * @param password user password.
     * @param email user email.
     * @param role user role.
     */
    public User(final String name, final String login, final Timestamp createDate,
                final String password, final String email, UserRole role) {
        this.name = name;
        this.login = login;
        this.createDate = createDate;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    /**
     * Getter for user name.
     * @return user name.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for user create date.
     * @return user create date.
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * Getter for user login.
     * @return user login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Getter for user email.
     * @return user email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter for user password.
     * @return user password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter for user role.
     * @return user role.
     */
    public UserRole getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name)
                && Objects.equals(login, user.login)
                && Objects.equals(password, user.password)
                && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, login, password, email);
    }
}
