package ru.matevosyan.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;


/**
 * Role entity.
 */
@Component
@Entity(name = "Role")
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;

    /**
     * Role default constructor.
     */
    public Role() {
    }

    /**
     * Role constructor with id.
     * @param id role id.
     */
    public Role(Integer id) {
        this.id = id;
    }

    /**
     * Getter for role id.
     * @return role id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter for role id.
     * @param id id role id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter for role name.
     * @return role name.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for role name.
     * @param name id role name.
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
        Role role = (Role) o;
        return (id != null ? id.equals(role.id) : role.id == null)
                && (name != null ? name.equals(role.name) : role.name == null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
