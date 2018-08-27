package ru.matevosyan.repository;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ru.matevosyan.entity.Task;
import ru.matevosyan.services.HiberFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskRepository class.
 */
public class TaskRepository {
    private static final SessionFactory SESSION_FACTORY = HiberFactory.FACTORY.getFactory();

    /**
     * Add task to database.
     * @param task todoList.
     */
    public void add(Task task) {
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
        } catch (Exception tranExp) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    /**
     * GetAll method.
     * Get all task.
     * @return list of task
     */
    public List<Task> getAll() {
        List<Task> tasks = new ArrayList<>();
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            tasks = session.createQuery("FROM Task order by createDate desc").list();
            session.getTransaction().commit();
        }  catch (Exception tranExp) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return tasks;
    }

    /**
     * Get the last added task.
     * @return last task
     */
    public Task getLastAddedTask() {
        Task last = null;
        SessionFactory factory = HiberFactory.FACTORY.getFactory();
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            List<Task> list = (List<Task>) session.createQuery("FROM Task order by createDate asc").list();
            last = list.get(list.size() - 1);
            session.getTransaction().commit();
        }  catch (Exception tranExp) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return last;
    }

    /**
     * Change task done state in db.
     * @param stateButtonValue done state.
     * @param taskId task id.
     */
    public void changeTaskState(Boolean stateButtonValue, Integer taskId) {
        SessionFactory factory = HiberFactory.FACTORY.getFactory();
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("UPDATE Task set done=:done WHERE id=:id");
            query.setBoolean("done", stateButtonValue);
            query.setInteger("id", taskId);
            query.executeUpdate();
            session.getTransaction().commit();
        }  catch (Exception tranExp) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}
