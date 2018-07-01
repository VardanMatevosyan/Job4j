package ru.matevosyan.controller.servlet.json;

import org.json.simple.JSONObject;
import ru.matevosyan.entity.MusicType;
import ru.matevosyan.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * JsonUsersInfoGet use for returning JSON representation with all users info to the client.
 */
public class JsonUsersInfoGet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.writeJSON(req, resp);
    }

    /**
     * Format the JSON of all users info in the system to send to the client.
     * @param req user request.
     * @param resp user response.
     * @throws IOException exception.
     */
    private void writeJSON(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int i = 0;
        resp.setContentType("text/json; charset=UTF-8");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"));
        HttpSession session = req.getSession();
        CopyOnWriteArrayList users;
        synchronized (session) {
            users = (CopyOnWriteArrayList) session.getAttribute("users");
        }
        JSONObject jsonObj = new JSONObject();
        writer.append(" [");
        for (Object object : users) {
            User user = (User) object;
            jsonObj.put("name", user.getName());
            jsonObj.put("login", user.getLogin());
            jsonObj.put("date", user.getCreateDate().toString());
            jsonObj.put("role", user.getRole().getName());
            int count = 0;
            List<MusicType> listOfMusicType = user.getMusicType();
            for (MusicType musicType : listOfMusicType) {
                if (listOfMusicType.get(0).getName().isEmpty()) {
                    break;
                }
                count++;
                jsonObj.put("musicType" + count, musicType.getName());
            }
            jsonObj.put("country", user.getAddress().getCountry());
            jsonObj.put("city", user.getAddress().getCity());
            jsonObj.put("street", user.getAddress().getStreet());
            jsonObj.put("number", user.getAddress().getNumber());
            writer.append(jsonObj.toJSONString());
            jsonObj.clear();
            if (!(((User) users.get(users.size() - 1)).getName().equals(user.getName()))) {
                writer.append(", ");
            }
        }

        writer.append(" ]");
        writer.flush();
        writer.close();
    }
}
