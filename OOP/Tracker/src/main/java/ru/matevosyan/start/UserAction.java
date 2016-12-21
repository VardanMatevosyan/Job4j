package ru.matevosyan.start;

/**
 * Interface UserAction.
 * Created on 20.12.2016.
 * @since 1.0
 * @version 1.0
 * @author Matevosyan Vardan
 */

public interface UserAction {

    /**
     * Method key for using in MenuTracker.
     * @return menu key value
     */

    int key();

    /**
     * Method execute for using in MenuTracker and ask user for return answer.
     * and of course run action with our items in tracker.
     * @param input, tracker
     */

    void execute(Input input, Tracker tracker);

    /**
     * Method info for using in MenuTracker.
     * and inform user about what user can do with items in program Tracker.
     * @return menu info to user
     */

    String info();
}
