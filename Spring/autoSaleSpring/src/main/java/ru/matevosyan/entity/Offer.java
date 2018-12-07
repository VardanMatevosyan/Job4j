package ru.matevosyan.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;

import java.sql.Timestamp;

/**
 * Offer entity.
 */
@Component
@Entity(name = "Offer")
@Table(name = "offers", uniqueConstraints={
        @UniqueConstraint(columnNames={"tittle", "sold_state"}),
})
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tittle;
    private String description;
    private String picture;
    private Integer price;
    private String address;

    @Column(name = "sold_state")
    private Boolean soldState;

    @Column(name = "posting_date")
    private Timestamp postingDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_car_id")
    private Car car;

    /**
     * Inject user and car object.
     * @param user user object.
     * @param car car object.
     */
    @Autowired
    public Offer(User user, Car car) {
        this.user = user;
        this.car = car;
    }

    /**
     * Offer default constructor.
     */
    public Offer() {
    }

    /**
     * Offer constructor for offer id.
     * @param id offer id.
     */
    public Offer(Integer id) {
        this.id = id;
    }

    /**
     * Getter for offer id.
     * @return offer id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter for offer id.
     * @param id is offer id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter for offer tittle.
     * @return offer tittle.
     */
    public String getTittle() {
        return tittle;
    }

    /**
     * Setter for offer tittle.
     * @param tittle is offer tittle.
     */
    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    /**
     * Getter for offer description.
     * @return offer description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for offer description.
     * @param description is offer description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for offer soldState.
     * @return offer sold state.
     */
    public Boolean getSoldState() {
        return soldState;
    }

    /**
     * Setter for offer soldState.
     * @param soldState is sold state.
     */
    public void setSoldState(Boolean soldState) {
        this.soldState = soldState;
    }



    /**
     * Getter for offer picture.
     * @return offer picture.
     */
    public String getPicture() {
        return picture;
    }

    /**
     * Setter for offer picture.
     * @param picture is offer picture.
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * Getter for offer price.
     * @return offer price.
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * Setter for offer price.
     * @param price is offer price.
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * Getter for offer postingDate.
     * @return offer posting date.
     */
    public Timestamp getPostingDate() {
        return postingDate;
    }

    /**
     * Setter for offer postingDate.
     * @param postingDate is posting date.
     */
    public void setPostingDate(Timestamp postingDate) {
        this.postingDate = postingDate;
    }

    /**
     * Getter for offer address.
     * @return offer address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter for offer address.
     * @param address is offer address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter for offer user.
     * @return offer user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Setter for offer user.
     * @param user is offer user.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Getter for offer car.
     * @return offer car.
     */
    public Car getCar() {
        return car;
    }

    /**
     * Setter for offer car.
     * @param car is offer car.
     */
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Offer offer = (Offer) o;

        return (id != null ? id.equals(offer.id) : offer.id == null)
                && (tittle != null ? tittle.equals(offer.tittle) : offer.tittle == null)
                && (description != null ? description.equals(offer.description) : offer.description == null)
                && (soldState != null ? soldState.equals(offer.soldState) : offer.soldState == null)
                && (price != null ? price.equals(offer.price) : offer.price == null)
                && (postingDate != null ? postingDate.equals(offer.postingDate) : offer.postingDate == null)
                && (address != null ? address.equals(offer.address) : offer.address == null)
                && (user != null ? user.equals(offer.user) : offer.user == null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (tittle != null ? tittle.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (soldState != null ? soldState.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (postingDate != null ? postingDate.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
