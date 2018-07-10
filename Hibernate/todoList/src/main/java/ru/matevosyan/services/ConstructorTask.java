package ru.matevosyan.services;

import ru.matevosyan.entity.Task;

import javax.servlet.http.HttpServletRequest;

import java.sql.Timestamp;
import java.util.Optional;

/**
 * ConstructorTask use for returning JSON representation with all tasks info to the client.
 */

public enum ConstructorTask {
    TASK_CONSTRUCTOR;

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
        String description = request.getParameter("description");
        if (!description.isEmpty()) {
            task = new Task();
            task.setDescription(description);
            task.setCreateDate(new Timestamp(System.currentTimeMillis()));
        }
        return Optional.ofNullable(task);
    }
}
