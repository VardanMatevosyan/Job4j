package ru.matevosyan.entity;

/**
 * Address class.
 * @author Matevosyan Vardan.
 * @version 1.0
 * created 13.06.2018
 */
public class Address {
    private int id;
    private String country;
    private String city;
    private String street;
    private Integer number;
    /**
     * Getter for address id.
     * @return role id.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for address id.
     * @param id address id.
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Getter for address country.
     * @return address country.
     */
    public String getCountry() {
        return country;
    }
    /**
     * Setter for address country.
     * @param country address country.
     */
    public void setCountry(String country) {
        this.country = country;
    }
    /**
     * Getter for address city.
     * @return address city.
     */
    public String getCity() {
        return city;
    }
    /**
     * Setter for address city.
     * @param city address city.
     */
    public void setCity(String city) {
        this.city = city;
    }
    /**
     * Getter for address street.
     * @return address street.
     */
    public String getStreet() {
        return street;
    }
    /**
     * Setter for address street.
     * @param street address street.
     */
    public void setStreet(String street) {
        this.street = street;
    }
    /**
     * Getter for address number.
     * @return address number.
     */
    public Integer getNumber() {
        return number;
    }
    /**
     * Setter for address number.
     * @param number address number.
     */
    public void setNumber(Integer number) {
        this.number = number;
    }
}
