package ru.matevosyan.models;

import org.springframework.stereotype.Component;

/**
 * User entity.
 */
@Component
public class User {
    private Integer id;
    private String name;

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
                && (name != null ? name.equals(user.name) : user.name == null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
