package ru.matevosyan.application;

import ru.matevosyan.action.*;
import ru.matevosyan.input.Input;
import java.util.ArrayList;
import java.util.List;

/**
 * Created class MenuTracker for add program menu.
 * Created on 20.12.2016.
 * Changed on 21.10.2019
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.1
 */

public class MenuTracker {
    /**
     * Input instance variable input.
     */
    private Input input;
    /**
     * Input instance variable tracker.
     */
    private Tracker tracker;
    /**
     * Instance variable for saving all user action.
     * And use it for run specific class, in dependence users selection action.
     */
    private List<UserAction> userAction = ActionsFactory.getUserAction();
    /**
     * instance availableRange for menu number range.
     */
    private ArrayList<Integer> availableRange = new ArrayList<>();

    /**
     * Constructor MenuTracker.
     *
     * @param input   for getting input state
     * @param tracker for getting tracker
     * @since 1.0
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;

        for (UserAction action : this.userAction) {
            availableRange.add(action.key());
        }
    }

    /**
     * method to return concrete key from availableRange.
     * @return availableRange array for getting to menu
     */
    public ArrayList<Integer> getKeys() {
        return availableRange;
    }

    /**
     * Method select created to execute concrete action method execute that contains in array position that user had invoked.
     * @param key user selection
     * @throws NullPointerException fo select which invoke execute method
     */
    public void select(int key) {
        this.userAction.get(key - 1).execute(this.input, this.tracker);
    }

    /**
     * Method show created for showing the list of user actions and action description.
     */
    public void show() {
        System.out.println("    M-E-N-U");
        for (UserAction userAction : this.userAction) {
            if (userAction != null) {
                System.out.println(userAction.info());
            }
        }
    }
}