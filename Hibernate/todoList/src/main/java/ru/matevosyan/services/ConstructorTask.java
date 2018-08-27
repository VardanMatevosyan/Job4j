package ru.matevosyan.services;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.entity.Task;
import ru.matevosyan.repository.TaskRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.sql.Timestamp;

import java.util.Optional;

/**
 * ConstructorTask use for returning JSON representation with all tasks info to the client.
 */

public enum ConstructorTask {
    TASK_CONSTRUCTOR;
    private static final Logger LOG = LoggerFactory.getLogger(ConstructorTask.class.getName());
    private static final TaskRepository TASK_REPOSITORY = new TaskRepository();

    /**
     * Default constructor.
     */
    ConstructorTask() {

    }

    /**
     * convert from json object to object of Task type.
     * @param request user request.
     * @return Optional which hold Task object.
     */
    public Optional<Task> convertFromJsonToTask(HttpServletRequest request) {
        Task task = null;
        BufferedReader reader = null;
        String description = "";

        try {
            reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
        } catch (IOException ioExp) {
            LOG.error("problem with getting request imput stream {}", ioExp);
        }

        JSONParser parser = new JSONParser();
        try {
            JSONObject objectJSON = (JSONObject) parser.parse(reader);
            description = (String) objectJSON.get("description");
        } catch (IOException ioExp) {
            LOG.error("Problem with i/o json from inputstream {}", ioExp);
        } catch (ParseException parsExp) {
            LOG.error("Problem with parsing json object {}", parsExp);
        }

        if (!description.isEmpty()) {
            task = new Task();
            task.setDescription(description);
            task.setCreateDate(new Timestamp(System.currentTimeMillis()));
        }
        return Optional.ofNullable(task);
    }

    /**
     * send back last added task.
     * @param resp user response.
     * @throws IOException exception when problem with getting output stream from response.
     */
    public void responseAddedTask(final HttpServletResponse resp)  throws IOException {
        resp.setContentType("text/json; charset=UTF-8");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"));
        Task task = TASK_REPOSITORY.getLastAddedTask();

        JSONObject jsonTask = new JSONObject();
        jsonTask.put("id", task.getId());
        jsonTask.put("description", task.getDescription());
        jsonTask.put("createDate", task.getCreateDate().toString());
        jsonTask.put("done", task.getDone());

        writer.append(jsonTask.toJSONString());
        writer.flush();
        writer.close();
    }
}
