package ru.matevosyan.controller.servlet.json;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.database.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * JsonCityPost fot return JSON representation with cities client want to the client.
 */
public class JsonCityPost extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(JsonCityPost.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getCityJsonFormat(req, resp);
    }

    /**
     * Format the JSON of all cities to send to the client.
     * @param req user request.
     * @param resp user response.
     * @throws IOException exception.
     */
    private void getCityJsonFormat(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String country = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream(), "UTF-8"));
        String json =  reader.readLine();
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObj = (JSONObject) parser.parse(json);
            country = (String) jsonObj.get("country");
        } catch (ParseException parsExp) {
            LOG.warn("Problem with parsing JSON", parsExp);
        }
        ConcurrentHashMap<String, String> cities = UserStore.STORE.getCities(Integer.parseInt(country));
        HttpSession session = req.getSession();
        synchronized (session) {
            session.setAttribute("citiesMap", cities);
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"));
        Set<Map.Entry<String, String>> entrySet = cities.entrySet();
        writer.append("[");
        for (Map.Entry<String, String> entry : entrySet) {
            String cityId = entry.getKey();
            String city = entry.getValue();
            Collection<Map.Entry<String, String>> entryList = (Collection<Map.Entry<String, String>>) entrySet;
            ArrayList<Map.Entry<String, String>> list = new ArrayList<>();
            list.addAll(entryList);
            if (!(list.get(list.size() - 1).getValue().equals(city))) {
                writer.append(String.format("{\"city_id\":\"%s\", \"city\":\"%s\"}, ", cityId, city));
            } else {
                writer.append(String.format("{\"city_id\":\"%s\", \"city\":\"%s\"}", cityId, city));
            }
        }
        writer.append("]");
        writer.flush();
        writer.close();

    }
}
