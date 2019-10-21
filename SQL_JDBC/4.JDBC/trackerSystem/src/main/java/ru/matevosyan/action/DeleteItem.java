package ru.matevosyan.action;

import ru.matevosyan.application.Tracker;
import ru.matevosyan.input.Input;

/**
 * Created class DeleteItem for implements UserAction for deleting item.
 * And add action to delete item for user action.
 * Created on 20.12.2016.
 * @author Matevosyan Vardan
 * @version 1.0
 * @since 1.0
 */

public class DeleteItem extends BaseAction {
    private final static int KEY = 4;
    private final static String MENU_ITEM = "Delete item";

    public DeleteItem() {
        super(KEY, MENU_ITEM);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        String id = String.valueOf(input.ask("Please enter the Task's id: "));
        tracker.deleteItem(id);
    }
}
