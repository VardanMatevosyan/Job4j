package ru.matevosyan.action;

import ru.matevosyan.application.Tracker;
import ru.matevosyan.input.Input;
import ru.matevosyan.model.Item;

/**
 * Created class FindItemById for implements UserAction to find item by id using method FindById from Tracker.
 * And add action to find item by id for user action.
 * Created on 20.12.2016.
 * Changed on 21.10.2019
 * @author Matevosyan Vardan
 * @version 1.0
 * @since 1.0
 */

public class FindItemById extends BaseAction {
    private final static int KEY = 6;
    private final static String MENU_ITEM = "Find item by id";

    public FindItemById() {
        super(KEY, MENU_ITEM);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        try {
            String id = String.valueOf(input.ask("Please enter the Task's id: "));
            Item itemFindById = tracker.findById(id);

            System.out.println(String.format("\r\n Id: %s. \r\n Name: %s. \r\n Description: %s. \r\n Date: %s. \r\n"
                            + " ------------------------------------------------", itemFindById.getId(), itemFindById.getName(),
                    itemFindById.getDescription(), itemFindById.getCreate()));
        } catch (NullPointerException npe) {

            System.out.printf("Task dose not exist \r\n");
            execute(input, tracker);

        }
    }
}