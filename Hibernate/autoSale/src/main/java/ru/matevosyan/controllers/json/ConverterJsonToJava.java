package ru.matevosyan.controllers.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.matevosyan.controllers.crud.AddOffer;
import ru.matevosyan.entity.Car;
import ru.matevosyan.entity.Offer;
import ru.matevosyan.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.Timestamp;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Optional;

/**
 * ConverterJsonToJava class convert recived json object to java object to used in the app.
 */
public class ConverterJsonToJava {
    private static final Logger LOG = LoggerFactory.getLogger(AddOffer.class.getName());
    private static final String IMAGE_PACKAGE = "images";
    private static final String SEPARATOR = System.getProperty("file.separator");

    /**
     * Default constructor to use new ConverterJsonToJava() without param.
     */
    public ConverterJsonToJava() {
    }

    /**
     * Convert json to java object.
     * @param request user request.
     * @return Offer object.
     * @throws JsonConvertException when we have exception we have problem during the converting json object.
     */
    private Offer convert(HttpServletRequest request) throws JsonConvertException {
        Offer offer = null;
        boolean soldStateDefault = false;
        String tittle =  request.getParameter("tittle");
        String description =  request.getParameter("offerDescription");
        String address =  request.getParameter("offerAddress");
        Integer price = Integer.parseInt(request.getParameter("price"));
        String bodyType =  request.getParameter("carBodyType");
        String brand =  request.getParameter("carBrand");
        Float engineCapacity = Float.parseFloat(request.getParameter("carEngineCapacity"));
        String gearBox =  request.getParameter("carGearBox");
        String modelVehicle =  request.getParameter("carModelVehicle");
        Timestamp year = null;
        String imageFileName =  request.getParameter("image_preview_addOffer");

        try {
            year = dateTransformation(request.getParameter("carYearOfManufacture"));
        } catch (ParseException parsExp) {
            LOG.error("Problem with date parsing in convert method {} ", parsExp);
        }

        if (tittle.equals(description)) {
            throw new JsonConvertException("Tittle and Descrioption are the same");
        }

        if (isNotEmpty(tittle, description, address, price)) {
            Car car = new Car();
            car.setBodyType(bodyType);
            car.setBrand(brand);
            car.setEngineCapacity(engineCapacity);
            car.setGearBox(gearBox);
            car.setModelVehicle(modelVehicle);
            car.setYearOfManufacture(year);

            User user = null;
            HttpSession session = request.getSession();
            LOG.info("CurrentUser name is " + ((User) session.getAttribute("currentUser")).getName());
            Optional<User> currentUser = Optional.ofNullable((User) session.getAttribute("currentUser"));

            if (currentUser.isPresent()) {
                LOG.info("user name is " + currentUser.get().getName());
                user = currentUser.get();
            } else {
                LOG.error("Value is not present in convert method in ConvertToJava class");
            }

            offer = new Offer();
            offer.setTittle(tittle);
            offer.setDescription(description);
            offer.setAddress(address);
            offer.setPostingDate(new Timestamp(System.currentTimeMillis()));
            offer.setPicture(String.format("%s%s%s%s%s%s%s%s", request.getSession().getServletContext().getRealPath("/"),
                    IMAGE_PACKAGE, SEPARATOR, user.getName(), SEPARATOR,
                    car.getBrand() + car.getModelVehicle(), SEPARATOR, imageFileName));
            offer.setPrice(price);
            offer.setSoldState(soldStateDefault);
            offer.setCar(car);
            offer.setUser(user);
        }

        return offer;
    }

    /**
     * dateTransform transform date representing String type to Timestamp object.
     * @param yearOfManufacture date of String type.
     * @return date of TimeStamp type.
     * @throws ParseException exception during parsing date of String type passing as argument to the method.
     */
    private Timestamp dateTransformation(String yearOfManufacture) throws ParseException {
        SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
        Timestamp year;
        try {
            year = new Timestamp(formater.parse(yearOfManufacture).getTime());
        } catch (ParseException parsExp) {
            LOG.error("Problem with date parsing {}", parsExp);
            throw parsExp;
        }
        return year;
    }

    /**
     * Check if passing param is not empty.
     * @param tittle offer.
     * @param description  offer.
     * @param address  offer.
     * @param price  offer.
     * @return true if passing param is not empty, else teturn false.
     */
    private boolean isNotEmpty(String tittle, String description, String address, Integer price) {
        return !tittle.isEmpty() && !description.isEmpty() && !address.isEmpty() && price > 0;
    }

    /**
     * getter for offer object.
     * @param request user.
     * @return object of Offer type.
     */
    public Offer getOffer(HttpServletRequest request) {
        return convert(request);
    }
}
