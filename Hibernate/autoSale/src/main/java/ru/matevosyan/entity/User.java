package ru.matevosyan.entity;

/**
 * User entity.
 */
public class User {
    private Integer id;
    private String name;
    private String city;
    private Integer phoneNumber;
    private Role role;

    /**
     * Default User constructor.
     */
    public User() {
    }

    /**
     * User constructor.
     * @param id user id.
     */
    public User(final Integer id) {
        this.id = id;
    }

    /**
     * Getter user id.
     * @return user id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter user id.
     * @param id is user id.
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * Getter user name.
     * @return user name.
     */
    public String getName() {
        return name;
    }
    /**
     * Setter user name.
     * @param name is user name.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Getter user city.
     * @return user city.
     */
    public String getCity() {
        return city;
    }
    /**
     * Setter user city.
     * @param city is user city.
     */
    public void setCity(String city) {
        this.city = city;
    }
    /**
     * Getter user phoneNumber.
     * @return user phoneNumber.
     */
    public Integer getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * Setter user phoneNumber.
     * @param phoneNumber user phone number.
     */
    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * Getter user role.
     * @return user role.
     */
    public Role getRole() {
        return role;
    }
    /**
     * Setter user role.
     * @param role is user role.
     */
    public void setRole(Role role) {
        this.role = role;
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
        return (id != null ? id.equals(user.id) : user.id == null)
                && (name != null ? name.equals(user.name) : user.name == null)
                && (city != null ? city.equals(user.city) : user.city == null)
                && (phoneNumber != null ? phoneNumber.equals(user.phoneNumber) : user.phoneNumber == null)
                && (role != null ? role.equals(user.role) : user.role == null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
