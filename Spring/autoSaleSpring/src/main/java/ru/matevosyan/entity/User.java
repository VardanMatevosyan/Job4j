package ru.matevosyan.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;

import java.util.Set;

/**
 * User entity.
 */
@Component
@Entity(name = "User")
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String password;

    private String city;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_role_id")
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user_id")
    private Set<Offer> offers;

    /**
     * inject role object.
     * @param role obj.
     */
    @Autowired
    public User(Role role) {
        this.role = role;
    }
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
     * Getter user password.
     * @return user password.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Setter user password.
     * @param password is user password.
     */
    public void setPassword(String password) {
        this.password = password;
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
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * Setter user phoneNumber.
     * @param phoneNumber user phone number.
     */
    public void setPhoneNumber(String phoneNumber) {
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

    /**
     * Getter user offers.
     * @return user offers.
     */
    public Set<Offer> getOffers() {
        return offers;
    }

    /**
     * Setter user offers.
     * @param offers is user offers.
     */
    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
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
                && (password != null ? password.equals(user.password) : user.password == null)
                && (city != null ? city.equals(user.city) : user.city == null)
                && (phoneNumber != null ? phoneNumber.equals(user.phoneNumber) : user.phoneNumber == null)
                && (role != null ? role.equals(user.role) : user.role == null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
