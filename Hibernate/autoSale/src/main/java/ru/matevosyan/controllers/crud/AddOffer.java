package ru.matevosyan.controllers.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.controllers.json.ConverterJsonToJava;

import ru.matevosyan.controllers.json.JsonConvertException;
import ru.matevosyan.controllers.management.FileUploader;
import ru.matevosyan.entity.Offer;
import ru.matevosyan.persistens.repository.OfferRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        } catch (JsonConvertException jcExp) {
           LOG.error("Problem with structuring offer object {}", jcExp);
        }
        OFFER_REPOSITORY.add(offer);
        FILE_UPLOADER.upload(req);
        req.getSession().setAttribute("offers", OFFER_REPOSITORY.getOffers());
    }
}
