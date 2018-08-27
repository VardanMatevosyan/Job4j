package ru.matevosyan.controllers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.repository.TaskRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * TaskStateChanger class for changing tasks done state.
 */
public class TaskStateChanger extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(TaskStateChanger.class.getName());
    private final TaskRepository repository = new TaskRepository();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        change(req);
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
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
            Boolean stateButtonValue = (Boolean) userObj.get("doneState");
            Integer taskId = Integer.parseInt((String) userObj.get("taskId"));
            repository.changeTaskState(stateButtonValue, taskId);
        } catch (ParseException parserExp) {
            LOG.error("Problem with json parsing {} ", parserExp);
        }
    }
}
