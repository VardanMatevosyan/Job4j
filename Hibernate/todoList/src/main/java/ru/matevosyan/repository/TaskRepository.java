package ru.matevosyan.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.entity.Task;
import ru.matevosyan.services.HiberFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskRepository class.
 */
public class TaskRepository {
    private static final Logger LOG = LoggerFactory.getLogger(TaskRepository.class.getName());

    /**
     * Add task to database.
     * @param task todoList.
     */
    public void add(Task task) {
        try (SessionFactory factory = HiberFactory.FACTORY.getFactory();
             Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
        }
    }

    /**
     * GetAll method.
     * Get all task.
     * @return list of task
     */
    public List<Task> getAll() {
        List<Task> tasks = new ArrayList<>();
        try (SessionFactory factory = HiberFactory.FACTORY.getFactory();
        Session session = factory.openSession()) {
            session.beginTransaction();
            tasks = session.createQuery("FROM Task order by done").list();
            session.getTransaction().commit();
        }
        return tasks;
    }

}
