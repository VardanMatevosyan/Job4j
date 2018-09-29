package ru.matevosyan.controllers.json;

import ru.matevosyan.entity.Offer;
import ru.matevosyan.persistens.repository.OfferRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Controller for send the response with offers list filtering by cars brand.
 */
public class OffersWithBrand extends HttpServlet {
    private static final OfferRepository OFFER_REPOSITORY = new OfferRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> brands = getBrands(req);
        resp.setContentType("text/json; charset=UTF-8");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"));
        List<Offer> lastDayOffers = OFFER_REPOSITORY.getOffersByBrand(brands);
        FormatJson formatJson = new FormatJson();
        bufferedWriter.write(formatJson.getJsonObject(lastDayOffers));
        bufferedWriter.flush();
    }

    /**
     * Return list of brands getting from request.
     * @param req client request.
     * @return list of brands.
     */
    private List<String> getBrands(final HttpServletRequest req) {
        String[] brandsJson = req.getParameterValues("brand");
        return Arrays.asList(brandsJson);
    }
}
