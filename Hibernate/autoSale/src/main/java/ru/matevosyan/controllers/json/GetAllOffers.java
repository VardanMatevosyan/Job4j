package ru.matevosyan.controllers.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.matevosyan.entity.Offer;
import ru.matevosyan.entity.User;
import ru.matevosyan.persistens.repository.OfferRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * Get all offers from the system.
 */
public class GetAllOffers extends HttpServlet {
    private static final OfferRepository OFFER_REPOSITORY = new OfferRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONArray array = new JSONArray();
        JSONObject objOffer = new JSONObject();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"));
        List<Offer> allOffers = OFFER_REPOSITORY.getOffers();
        User currentUser = (User) req.getSession().getAttribute("currentUser");
        for (Offer offer : allOffers) {
            JSONObject object = new JSONObject();
            object.put("offerId", offer.getId());
            object.put("tittle", offer.getTittle());
            object.put("description", offer.getDescription());
            object.put("picture", offer.getPicture());
            object.put("soldState", offer.getSoldState());
            object.put("postingDate", offer.getPostingDate().toString());
            object.put("address", offer.getAddress());
            object.put("price", offer.getPrice());
            object.put("name", offer.getUser().getName());
            object.put("phoneNumber", offer.getUser().getPhoneNumber());
            object.put("city", offer.getUser().getCity());
            object.put("userId", offer.getUser().getId());
            object.put("role", offer.getUser().getRole().getName());
            object.put("yearOfManufacture", offer.getCar().getYearOfManufacture().toString());
            object.put("modelVehicle", offer.getCar().getModelVehicle());
            object.put("gearBox", offer.getCar().getGearBox());
            object.put("engineCapacity", offer.getCar().getEngineCapacity());
            object.put("bodyType", offer.getCar().getBodyType());
            object.put("brand", offer.getCar().getBrand());
            array.add(object);
        }
        objOffer.put("offers", array);
        objOffer.put("currentUserId", currentUser.getId());
        objOffer.put("currentUserRoleName", currentUser.getRole().getName());

        bufferedWriter.write(objOffer.toJSONString());
        bufferedWriter.flush();

    }
}
