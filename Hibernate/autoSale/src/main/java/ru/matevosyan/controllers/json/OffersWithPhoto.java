package ru.matevosyan.controllers.json;

import ru.matevosyan.entity.Offer;
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
 * Controller for responding json that contains list of offers with photo to the client.
 */
public class OffersWithPhoto  extends HttpServlet {
    private static final OfferRepository OFFER_REPOSITORY = new OfferRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json; charset=UTF-8");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"));
        List<Offer> offersWithPhoto = OFFER_REPOSITORY.getOffersWithPhoto();
        FormatJson formatJson = new FormatJson();
        bufferedWriter.write(formatJson.getJsonObject(offersWithPhoto));
        bufferedWriter.flush();
    }
}
