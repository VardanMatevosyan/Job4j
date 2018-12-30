package ru.matevosyan.entity;

import org.springframework.stereotype.Component;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.sql.Timestamp;

/**
 * Car entity.
 */
@Component
@Entity(name = "Car")
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "brand", unique = true)
    private String brand;

    @Column(name = "model_vehicle")
    private String modelVehicle;

    @Column(name = "year_of_manufacture")
    private Timestamp yearOfManufacture;
    @Column(name = "body_type")
    private String bodyType;

    @Column(name = "gear_box")
    private String gearBox;

    @Column(name = "engine_capacity")
    private Float engineCapacity;

    /**
     * Default constructor.
     */
    public Car() {
    }

    /**
     * Car constructor with id.
     * @param id car id.
     */
    public Car(Integer id) {
        this.id = id;
    }
    /**
     * Getter for car id.
     * @return car id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter for car id.
     * @param id is car id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter for car brand.
     * @return car brand.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Setter for car brand.
     * @param brand is car brand.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Getter for car modelVehicle.
     * @return car model vehicle.
     */
    public String getModelVehicle() {
        return modelVehicle;
    }

    /**
     * Setter for car modelVehicle.
     * @param modelVehicle is car modelVehicle.
     */
    public void setModelVehicle(String modelVehicle) {
        this.modelVehicle = modelVehicle;
    }

    /**
     * Getter for car yearOfManufacture.
     * @return car year of manufacture.
     */
    public Timestamp getYearOfManufacture() {
        return yearOfManufacture;
    }

    /**
     * Setter for car yearOfManufacture.
     * @param yearOfManufacture is year of manufacture.
     */
    public void setYearOfManufacture(Timestamp yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    /**
     * Getter for car bodyType.
     * @return car body type.
     */
    public String getBodyType() {
        return bodyType;
    }

    /**
     * Setter for car bodyType.
     * @param bodyType is body type.
     */
    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    /**
     * Getter for car gearBox.
     * @return car gear box.
     */
    public String getGearBox() {
        return gearBox;
    }

    /**
     * Setter for car gearBox.
     * @param gearBox is gear box.
     */
    public void setGearBox(String gearBox) {
        this.gearBox = gearBox;
    }
    /**
     * Getter for car engineCapacity.
     * @return car engine capacity.
     */
    public Float getEngineCapacity() {
        return engineCapacity;
    }

    /**
     * Setter for car engineCapacity.
     * @param engineCapacity is car engine сapacity.
     */
    public void setEngineCapacity(Float engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return (brand != null ? brand.equals(car.brand) : car.brand == null)
                && (modelVehicle != null ? modelVehicle.equals(car.modelVehicle) : car.modelVehicle == null)
                && (yearOfManufacture != null ? yearOfManufacture.equals(car.yearOfManufacture)
                : car.yearOfManufacture == null)
                && (bodyType != null ? bodyType.equals(car.bodyType) : car.bodyType == null)
                && (gearBox != null ? gearBox.equals(car.gearBox) : car.gearBox == null)
                && (engineCapacity != null ? engineCapacity.equals(car.engineCapacity) : car.engineCapacity == null);
    }

    @Override
    public int hashCode() {
        int result = brand != null ? brand.hashCode() : 0;
        result = 31 * result + (modelVehicle != null ? modelVehicle.hashCode() : 0);
        result = 31 * result + (yearOfManufacture != null ? yearOfManufacture.hashCode() : 0);
        result = 31 * result + (bodyType != null ? bodyType.hashCode() : 0);
        result = 31 * result + (gearBox != null ? gearBox.hashCode() : 0);
        result = 31 * result + (engineCapacity != null ? engineCapacity.hashCode() : 0);
        return result;
    }
}
