package ru.matevosyan.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.action.UserActionLoader;
import ru.matevosyan.action.UserActionLoaderHandler;
import ru.matevosyan.application.MenuTracker;
import ru.matevosyan.application.Tracker;
import ru.matevosyan.database.conection.ConnectionDB;
import ru.matevosyan.input.Input;
import ru.matevosyan.input.ValidateInput;

import java.sql.SQLException;

/**
 * Created class StartUI for starting the program.
 * Created on 25.11.2016.
 * change on 18.01.2018.
 * Changed on 21.10.2019
 * @since 1.0
 * @author Matevosyan Vardan.
 * @version 2.0 with database.
 */

public class StartUI {
    /**
     * Input instance variable input.
     */
    private Input input;

    /**
     * Input instance variable tracker.
     */
    private Tracker tracker;
    private boolean isConnectionClosed = false;
    private static final Logger LOG = LoggerFactory.getLogger(StartUI.class.getName());

    static {
        UserActionLoaderHandler.fillActions();
    }

    /**
     * Constructor for StartUI.
     * @param input for assign ot enter data to the program.
     * @param tracker for take the Tracker state.
     */

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Method for initialization program menu and interaction with user.
     */
    private void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        do {
            menu.show();
            menu.select(input.ask("Select: ", menu.getKeys()));
            try {
                isConnectionClosed = !(ConnectionDB.getConnection().isClosed());
            } catch (SQLException closeEx) {
                LOG.warn("Problem with trying to invoke isClosed method on DB connection", closeEx);
            }
        } while (isConnectionClosed);
    }

    /**
     * The static main method which is starting our program.
     * @param args is the array argument that pass to main method
     */
    public static void main(String[] args) {
        Input input = new ValidateInput();
        new StartUI(input, new Tracker()).init();

    }

}
