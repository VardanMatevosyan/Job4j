package ru.matevosyan.controller.servlet.json;

import ru.matevosyan.database.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * JsonCountryPost use for returning JSON representation with all country to the client.
 */
public class JsonCountryPost extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getCountryJsonFormat(req, resp);
    }

    /**
     * Format the JSON of all countries to send to the client.
     * @param req user request.
     * @param resp user response.
     * @throws IOException exception.
     */
    private void getCountryJsonFormat(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ConcurrentHashMap<String, String> counties = UserStore.STORE.getCounties();
        HttpSession session = req.getSession();
        synchronized (session) {
            session.setAttribute("countriesMap", counties);
        }
        Set<Map.Entry<String, String>> entrySet = counties.entrySet();
        resp.setCharacterEncoding("UTF-8");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"));
        writer.append("{ \"countryObj\":[");
        for (Map.Entry<String, String> entry : entrySet) {
            String countryId = entry.getKey();
            String country = entry.getValue();
            Collection<Map.Entry<String, String>> entryList = (Collection<Map.Entry<String, String>>) entrySet;
            ArrayList<Map.Entry<String, String>> list = new ArrayList<>();
            list.addAll(entryList);
            if (!(list.get(list.size() - 1).getValue().equals(country))) {
                writer.append(String.format("{\"country_id\":\"%s\", \"country\":\"%s\"}, ", countryId, country));
            } else {
                writer.append(String.format("{\"country_id\":\"%s\", \"country\":\"%s\"} ", countryId, country));
            }
        }
        writer.append("]}");
        writer.flush();
        writer.close();

    }
}
