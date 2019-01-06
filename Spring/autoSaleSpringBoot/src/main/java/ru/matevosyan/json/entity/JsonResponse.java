package ru.matevosyan.json.entity;

import java.sql.Timestamp;

/**
 * JsonResponse convert list of JsonResponse from offer, user and car model.
 * Collected together to send to the client.
 */
public class JsonResponse {
    private Integer offerId;
    private String tittle;
    private String description;
    private String picture;
    private Boolean soldState;
    private Timestamp postingDate;
    private String address;
    private Integer price;
    private String name;
    private String phoneNumber;
    private String city;
    private Integer userId;
    private Integer roleId;
    private String role;
    private Timestamp yearOfManufacture;
    private String modelVehicle;
    private String gearBox;
    private Float engineCapacity;
    private String bodyType;
    private String brand;

    /**
     * JsonResponse constructor.
     */
    public JsonResponse() {
    }

    /**
     * Setter for offer id.
     * @param offerId Integer.
     */
    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    /**
     * Setter.
     * @param tittle offer.
     */
    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    /**
     * Setter.
     * @param description offer.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Setter.
     * @param picture offer.
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * Setter.
     * @param soldState offer.
     */
    public void setSoldState(Boolean soldState) {
        this.soldState = soldState;
    }

    /**
     * Setter.
     * @param postingDate offer.
     */
    public void setPostingDate(Timestamp postingDate) {
        this.postingDate = postingDate;
    }

    /**
     * Setter.
     * @param address offer.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Setter.
     * @param price offer.
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * Setter.
     * @param name user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter.
     * @param phoneNumber user.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Setter.
     * @param city user.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Setter.
     * @param userId user.
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Setter.
     * @param roleId user.
     */
    public void setRoleId(Integer role) {
        this.roleId = roleId;
    }

    /**
     * Setter.
     * @param role user.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Setter.
     * @param yearOfManufacture car.
     */
    public void setYearOfManufacture(Timestamp yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    /**
     * Setter.
     * @param modelVehicle car.
     */
    public void setModelVehicle(String modelVehicle) {
        this.modelVehicle = modelVehicle;
    }

    /**
     * Setter.
     * @param gearBox car.
     */
    public void setGearBox(String gearBox) {
        this.gearBox = gearBox;
    }

    /**
     * Setter.
     * @param engineCapacity car.
     */
    public void setEngineCapacity(Float engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    /**
     * Setter.
     * @param bodyType car.
     */
    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    /**
     * Setter.
     * @param brand car.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Getter.
     * @return offer id.
     */
    public Integer getOfferId() {
        return offerId;
    }

    /**
     * Getter.
     * @return offer tittle.
     */
    public String getTittle() {
        return tittle;
    }

    /**
     * Getter.
     * @return offer description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter.
     * @return offer picture.
     */
    public String getPicture() {
        return picture;
    }

    /**
     * Getter.
     * @return offer soldState.
     */
    public Boolean getSoldState() {
        return soldState;
    }

    /**
     * Getter.
     * @return offer posting Date.
     */
    public Timestamp getPostingDate() {
        return postingDate;
    }

    /**
     * Getter.
     * @return offer address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Getter.
     * @return offer price.
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * Getter.
     * @return user name.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter.
     * @return user phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Getter.
     * @return user city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Getter.
     * @return user id.
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Getter.
     * @return user roleId.
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * Getter.
     * @return user role.
     */
    public String getRole() {
        return role;
    }

    /**
     * Getter.
     * @return car year of manufacture.
     */
    public Timestamp getYearOfManufacture() {
        return yearOfManufacture;
    }

    /**
     * Getter.
     * @return car model vehicle.
     */
    public String getModelVehicle() {
        return modelVehicle;
    }

    /**
     * Getter.
     * @return car gearBox.
     */
    public String getGearBox() {
        return gearBox;
    }

    /**
     * Getter.
     * @return car engine capacity.
     */
    public Float getEngineCapacity() {
        return engineCapacity;
    }

    /**
     * Getter.
     * @return car body type.
     */
    public String getBodyType() {
        return bodyType;
    }

    /**
     * Getter.
     * @return car brand.
     */
    public String getBrand() {
        return brand;
    }

}

