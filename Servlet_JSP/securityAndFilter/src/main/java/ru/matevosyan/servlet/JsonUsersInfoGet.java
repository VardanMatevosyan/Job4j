package ru.matevosyan.servlet;

import ru.matevosyan.model.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
        writer.append("[");
        for (Object object  : users) {
            User user = (User) object;
            if (!(users.get(users.size() - 1).equals(user))) {
                writer.append(String.format("{\"name\":\"%s\", \"login\":\"%s\", \"date\":\"%s\", \"role\":\"%s\""
                                + ", \"country\":\"%s\", \"city\":\"%s\"}, ",
                        user.getName(),
                        user.getLogin(),
                        user.getCreateDate().toString(),
                        user.getRole().getName(),
                        user.getCountry(),
                        user.getCity()));
            } else {
                writer.append(String.format("{\"name\":\"%s\", \"login\":\"%s\", \"date\":\"%s\", \"role\":\"%s\""
                                + ", \"country\":\"%s\", \"city\":\"%s\"}",
                        user.getName(),
                        user.getLogin(),
                        user.getCreateDate().toString(),
                        user.getRole().getName(),
                        user.getCountry(),
                        user.getCity()));
            }
        }
        writer.append("]");
        writer.flush();
        writer.close();
    }
}
