package ru.matevosyan.controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.entity.Task;
import ru.matevosyan.repository.TaskRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * ConverterTaskList class for converting list of task to json object.
 */
public class ConverterTaskList extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(ConverterTaskList.class.getName());
    private static final TaskRepository TASK_REPOSITORY = new TaskRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.writeJSON(req, resp);
    }


    /**
     * Format the JSON of all tasks info in the system to send to the client.
     * @param req user request.
     * @param resp user response.
     * @throws IOException exception.
     */
    private void writeJSON(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json; charset=UTF-8");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"));
        List<Task> tasks = TASK_REPOSITORY.getAll();
        tasks.stream().forEach(task -> LOG.debug((task.getDescription() + " " + task.getDone())));
        JSONArray jsonArray = new JSONArray();

        for (Task task : tasks) {
            JSONObject jsonTask = new JSONObject();
            jsonTask.put("id", task.getId());
            jsonTask.put("description", task.getDescription());
            jsonTask.put("createDate", task.getCreateDate().toString());
            jsonTask.put("done", task.getDone());
            jsonArray.add(jsonTask);
        }

        writer.append(jsonArray.toJSONString());
        writer.flush();
    }
}
