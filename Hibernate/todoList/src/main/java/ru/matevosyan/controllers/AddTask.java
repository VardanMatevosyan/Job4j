package ru.matevosyan.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.matevosyan.entity.Task;
import ru.matevosyan.repository.TaskRepository;
import ru.matevosyan.services.ConstructorTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.util.Optional;



/**
 * AddTask servlet class.
 * Created on 07.07.2018.
 * @author Matevosyan Vardan.
 * @version 1.0
 */

public class AddTask extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(AddTask.class.getName());
    private static final TaskRepository TASK_REPOSITORY = new TaskRepository();

    /**
     * doPost method insert data to database.
     * @param req request.
     * @param resp response.
     * @throws ServletException servlet exception.
     * @throws IOException input output exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Task> task = ConstructorTask.TASK_CONSTRUCTOR.convertFromJsonToTask(req);
        if (task.isPresent()) {
            Task task1 = task.get();
            TASK_REPOSITORY.add(task1);
        } else {
            LOG.error("Trying to add to the database buy task is not present (null)");
        }
    }
}

