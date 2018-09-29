package ru.matevosyan.controllers.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.matevosyan.entity.Offer;
import java.util.List;

/**
 * FormatJson for creating and formatting json object.
 */
public class FormatJson {

    /**
     * Default constructor.
     */
    protected FormatJson() {
    }

    /**
     * Format string representation of json from the list of objects.
     * @param offers list of objects.
     * @return string representation of json.
     */
    protected String getJsonObject(final List<Offer> offers) {
        JSONArray array = new JSONArray();
        JSONObject readyJsonObj = new JSONObject();

        for (Offer offer : offers) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("offerId", offer.getId());
            jsonObject.put("tittle", offer.getTittle());
            jsonObject.put("description", offer.getDescription());
            jsonObject.put("picture", offer.getPicture());
            jsonObject.put("soldState", offer.getSoldState());
            jsonObject.put("postingDate", offer.getPostingDate().toString());
            jsonObject.put("address", offer.getAddress());
            jsonObject.put("price", offer.getPrice());
            jsonObject.put("name", offer.getUser().getName());
            jsonObject.put("phoneNumber", offer.getUser().getPhoneNumber());
            jsonObject.put("city", offer.getUser().getCity());
            jsonObject.put("userId", offer.getUser().getId());
            jsonObject.put("role", offer.getUser().getRole().getName());
            jsonObject.put("yearOfManufacture", offer.getCar().getYearOfManufacture().toString());
            jsonObject.put("modelVehicle", offer.getCar().getModelVehicle());
            jsonObject.put("gearBox", offer.getCar().getGearBox());
            jsonObject.put("engineCapacity", offer.getCar().getEngineCapacity());
            jsonObject.put("bodyType", offer.getCar().getBodyType());
            jsonObject.put("brand", offer.getCar().getBrand());
            array.add(jsonObject);
        }
        readyJsonObj.put("offers", array);
        return readyJsonObj.toJSONString();
    }
}
