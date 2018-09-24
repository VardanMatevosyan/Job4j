package ru.matevosyan.repository;

import org.hibernate.Query;

import ru.matevosyan.entity.Task;
import ru.matevosyan.services.SessionManager;

import java.util.List;

/**
 * TaskRepository class for holding main method to manipulate with task.
 */
public class TaskRepository {
    private static final SessionManager TRANSACTION = SessionManager.TRANSACTION;

    /**
     * Add task to database.
     * @param task todoList.
     */
    public void add(Task task) {
        TRANSACTION.use(session -> session.save(task));
    }

    /**
     * GetAll method.
     * Get all task.
     * @return list of task
     */
    public List<Task> getAll() {
        return TRANSACTION.use(session -> {
                    Query query = session.createQuery("FROM Task order by createDate desc");
                    return (List<Task>) query.list();
        });
    }

    /**
     * Get the last added task.
     * @return last task
     */
    public Task getLastAddedTask() {
        return TRANSACTION.use(session -> {
            Query query = session.createQuery("FROM Task order by createDate asc");
            List<Task> quryList = (List<Task>) query.list();
            return quryList.get(quryList.size() - 1);
        });
    }

    /**
     * Change task done state in db.
     * @param stateButtonValue done state.
     * @param taskId task id.
     * @return count of updated rows.
     */
    public int changeTaskState(Boolean stateButtonValue, Integer taskId) {
        return TRANSACTION.use(session -> {
            Query query = session.createQuery("UPDATE Task set done=:done WHERE id=:id");
            query.setBoolean("done", stateButtonValue);
            query.setInteger("id", taskId);
            return query.executeUpdate();
        });
    }
}
