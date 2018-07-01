package ru.matevosyan.entity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 * User class.
 * @author Matevosyan Vardan.
 * @version 1.0
 * created 13.03.2018
 */
public class User {
    private final int id;
    private final String name;
    private final String login;
    private final String password;
    private Timestamp createDate;
    private String email;
    private UserRole role;
    private Address address;
    private List<MusicType> musicTypes;

    /**
     * Constructor.
     * @param id  user id.
     * @param name user name.
     * @param login user login.
     * @param password user password.
     * @param email user email.
     * @param role user role.
     * @param address user address.
     * @param musicTypes list of user music types.
     */

    public User(final int id, final String name, final String login, final String password, final String email,
                final UserRole role, final Address address, final List<MusicType> musicTypes) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.createDate = new Timestamp(System.currentTimeMillis());
        this.email = email;
        this.role = role;
        this.address = address;
        this.musicTypes = musicTypes;
    }


    /**
     * Don't use for create to insert, only if you use to get from the database.
     * Constructor for get result.
     * @param id user id.
     * @param name user name.
     * @param login user login.
     * @param createDate user created date.
     * @param password user password.
     * @param email user email.
     * @param role user role.
     * @param address user address.
     * @param musicTypes user music types.
     */
    public User(final int id, final String name, final String login, final String password, final Timestamp createDate,
                final String email, final UserRole role, final Address address, final List<MusicType> musicTypes) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.createDate = createDate;
        this.email = email;
        this.role = role;
        this.address = address;
        this.musicTypes = musicTypes;
    }

    /**
     * Getter for user id.
     * @return user id.
     */

    public int getId() {
        return id;
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

    /**
     * Getter for user address.
     * @return user address.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Getter for user music type.
     * @return user music type.
     */
    public List<MusicType> getMusicType() {
        return musicTypes;
    }

    /**
     * Getter for user email.
     * @return user email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for user email.
     * @param email user email.
     */
    public void setEmail(String email) {
        this.email = email;
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
        boolean musicEq = false;
        for (int i = 0; i < user.musicTypes.size(); i++) {
            musicEq = user.getMusicType().get(i) == this.musicTypes.get(i);
        }
        return Objects.equals(name, user.name)
                && Objects.equals(login, user.login)
                && Objects.equals(password, user.password)
                && Objects.equals(address, user.address) && musicEq;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, login, password,
                address, musicTypes);
    }
}
