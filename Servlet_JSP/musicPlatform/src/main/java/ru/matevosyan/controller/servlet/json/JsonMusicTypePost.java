package ru.matevosyan.controller.servlet.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.dao.DaoException;
import ru.matevosyan.dao.IGeneric;
import ru.matevosyan.dao.MusicTypes;
import ru.matevosyan.entity.MusicType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * JsonMusicTypePost for create JSON of music type and set it to the session attribute.
 */
public class JsonMusicTypePost extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(JsonMusicTypePost.class.getName());
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        IGeneric<MusicType, Integer> musicTypes = new MusicTypes();
        try {
            CopyOnWriteArrayList<MusicType> allMusicTypes = musicTypes.getAll();
            ConcurrentHashMap<Integer, String> mapOfMusicTypes = new ConcurrentHashMap<>();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(resp.getOutputStream(),
                    "UTF-8"));
            bufferedWriter.append("{\"musicTypes\": [");
            for (MusicType musicObj : allMusicTypes) {
                Integer id = musicObj.getId();
                String name = musicObj.getName();
                if (!(allMusicTypes.get(allMusicTypes.size() - 1).getName().equals(name))) {
                    bufferedWriter.append(String.format("{\"musicTypeId\":\"%s\", \"musicTypeName\":\"%s\"}, ",
                            id, name));
                    mapOfMusicTypes.put(id, name);
                } else {
                    bufferedWriter.append(String.format("{\"musicTypeId\":\"%s\", \"musicTypeName\":\"%s\"} ",
                            id, name));
                    mapOfMusicTypes.put(id, name);
                }
            }
            HttpSession session = req.getSession();
            synchronized (session) {
                session.setAttribute("mapOfMusicType", mapOfMusicTypes);
            }
            bufferedWriter.append("]}");
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (DaoException daoExp) {
            LOG.error("Problem with getting all music types using getAll dao method {} ", daoExp);
        }
    }
}
