package ru.matevosyan.controllers.json;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.matevosyan.persistens.repository.IOffer;
import ru.matevosyan.persistens.repository.OfferRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ChangeSellStatus servlet.
 */
public class ChangeSellStatus extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(ChangeSellStatus.class.getName());
    private final IOffer repository = new OfferRepository();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        change(req);
        req.getRequestDispatcher("/WEB-INF/views/user.jsp").forward(req, resp);
    }

    /**
     * Change offer sell status.
     * @param req user request.
     */
    private void change(final HttpServletRequest req) {
        BufferedReader reader = null;
        String json = "";
        try {
            reader = new BufferedReader(new InputStreamReader(req.getInputStream(), "UTF-8"));
            json =  reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONParser parser = new JSONParser();

        try {
            JSONObject userObj = (JSONObject) parser.parse(json);
            Boolean statusButton = (Boolean) userObj.get("statusButton");
            Integer offerId = Integer.parseInt((String) userObj.get("offerId"));
            System.out.println("st is " + statusButton + " and id is " + offerId);
            repository.changeSellState(statusButton, offerId);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
