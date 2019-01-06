package ru.matevosyan.json;

import org.springframework.stereotype.Component;
import ru.matevosyan.entity.Offer;
import ru.matevosyan.json.entity.JsonResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * FormatJson for creating and formatting json object.
 */
@Component
public class FormatJson {

    /**
     * Default constructor.
     */
    public FormatJson() {
    }

    /**
     * method that return list of respond object converting from offer model.
     * @param offers object.
     * @return list of JsonResponse object.
     */
    public List<JsonResponse> getResponseList(List<Offer> offers) {
        List<JsonResponse> jsonResponseList = new ArrayList<>();

        for (Offer offer : offers) {
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setOfferId(offer.getId());
            jsonResponse.setTittle(offer.getTittle());
            jsonResponse.setAddress(offer.getAddress());
            jsonResponse.setDescription(offer.getDescription());
            jsonResponse.setPicture(offer.getPicture());
            jsonResponse.setPrice(offer.getPrice());
            jsonResponse.setSoldState(offer.getSoldState());
            jsonResponse.setPostingDate(offer.getPostingDate());
            jsonResponse.setUserId(offer.getUser().getId());
            jsonResponse.setName(offer.getUser().getName());
            jsonResponse.setCity(offer.getUser().getCity());
            jsonResponse.setPhoneNumber(offer.getUser().getPhoneNumber());
            jsonResponse.setRoleId(offer.getUser().getRole().getId());
            jsonResponse.setRole(offer.getUser().getRole().getName());
            jsonResponse.setBodyType(offer.getCar().getBodyType());
            jsonResponse.setBrand(offer.getCar().getBrand());
            jsonResponse.setGearBox(offer.getCar().getGearBox());
            jsonResponse.setModelVehicle(offer.getCar().getModelVehicle());
            jsonResponse.setEngineCapacity(offer.getCar().getEngineCapacity());
            jsonResponse.setYearOfManufacture(offer.getCar().getYearOfManufacture());

            jsonResponseList.add(jsonResponse);
        }
        return jsonResponseList;
    }
}
