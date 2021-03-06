package ru.matevosyan.controllers.crud;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.controllers.json.ConverterJsonToJava;

import ru.matevosyan.controllers.json.JsonConvertException;
import ru.matevosyan.controllers.management.FileUploader;
import ru.matevosyan.entity.Offer;
import ru.matevosyan.entity.User;
import ru.matevosyan.persistens.repository.OfferRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * AddOffer add offer to the database.
 */
@MultipartConfig
public class AddOffer extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(AddOffer.class.getName());
    private static final OfferRepository OFFER_REPOSITORY = new OfferRepository();
    private static final FileUploader FILE_UPLOADER = new FileUploader();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConverterJsonToJava converterJsonToJava = new ConverterJsonToJava();
        Offer offer = null;
        try {
            offer = converterJsonToJava.getOffer(req);
            System.out.println(" offers ---  " + offer.getDescription() + "  model  " + offer.getCar().getModelVehicle());
        } catch (JsonConvertException jcExp) {
           LOG.error("Problem with structuring offer object {}", jcExp);
        }
        OFFER_REPOSITORY.add(offer);

        if (req.getParts() != null) {
            FILE_UPLOADER.upload(req);
        }

        this.respondJson(req, resp);
    }

    /**
     * Respond JSON obj to the client.
     * @param req user request.
     * @param resp user response.
     * @throws IOException when problem with getting or writing to streams.
     */
    private void respondJson(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json; charset=UTF-8");
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
