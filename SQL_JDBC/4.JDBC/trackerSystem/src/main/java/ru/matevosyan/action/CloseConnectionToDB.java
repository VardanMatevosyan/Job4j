package ru.matevosyan.action;


import ru.matevosyan.application.Tracker;
import ru.matevosyan.input.Input;

import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Close connection to database and exit from the program.
 * Created on 18.01.2018.
 * Changed on 21.10.2019
 * @author Matevosyan Vardan
 */

public class CloseConnectionToDB extends BaseAction {
    private static final Logger LOG = LoggerFactory.getLogger(CloseConnectionToDB.class.getName());
    private final String exit = "y";
    private final static int KEY = 10;
    private final static String MENU_ITEM = "Press y to EXIT";

    public CloseConnectionToDB() {
        super(KEY, MENU_ITEM);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        try {
            if (!tracker.getConnection().isClosed() && (exit.equals(input.ask("Exit? (y) ")))) {
                tracker.getConnection().close();
                LOG.debug("close connection to database");
            }
        } catch (SQLException sql) {
            LOG.warn("Can't close connection", sql);
        }
    }
}
