package ru.matevosyan.action;

import ru.matevosyan.application.Tracker;
import ru.matevosyan.input.Input;
import ru.matevosyan.model.Item;

/**
 * Created class AddItem for implements UserAction and add action to add items for user action.
 * Created on 20.12.2016.
 * Changed on 21.10.2019
 * @author Matevosyan Vardan
 * @version 1.0
 * @since 1.0
 */

public class AddItem extends BaseAction {
    private final static int KEY = 1;
    private final static String MENU_ITEM = "Add new item";

    public AddItem() {
        super(KEY, MENU_ITEM);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        String name = input.ask("Please enter the Task's name ");
        String description = input.ask("Please enter the Task's description ");
        tracker.add(new Item(name, description));
    }


}