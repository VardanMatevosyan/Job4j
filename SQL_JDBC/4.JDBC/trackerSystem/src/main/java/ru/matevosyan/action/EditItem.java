package ru.matevosyan.action;


import ru.matevosyan.application.Tracker;
import ru.matevosyan.input.Input;
import ru.matevosyan.model.Item;

/**
 * Created class EditItems for implements UserAction and add action to edit items for user action.
 * User answer fo question about items id for edit item and then user can change items name and description.
 * Created on 20.12.2016.
 * Changed on 21.10.2019
 * @author Matevosyan Vardan
 * @version 1.0
 * @since 1.0
 */
@UserActionLoader
public class EditItem extends BaseAction {
    private final static int KEY = 3;
    private final static String MENU_ITEM = "Edit item";

    public EditItem() {
        super(KEY, MENU_ITEM);
    }

    @Override
    public void execute(Input input, Tracker tracker) {

        String id = String.valueOf(input.ask("Please enter the Task's id: "));
        Item itemFind = tracker.findById(id);

        if (itemFind == null) {
            execute(input, tracker);
        } else {
            String name = input.ask("Please enter the Task's name: ");
            String description = input.ask("Please enter the Task's description: ");

            Item item = new Item(name, description);
            item.setId(id);
            tracker.editItem(item);
        }

    }

}