package ru.matevosyan.action;


import ru.matevosyan.application.Tracker;
import ru.matevosyan.input.Input;
import ru.matevosyan.model.Item;

/**
 * Created static class AddCommentToItem for implements UserAction fo comment item.
 * And add action to add items comment for user action.
 * Created on 20.12.2016.
 * Changed on 21.10.2019
 * @author Matevosyan Vardan
 * @version 1.0
 * @since 1.0
 */

@UserActionLoader
public class AddCommentToItem extends BaseAction {
    private final static int KEY = 5;
    private final static String MENU_ITEM = "Add comment to item";

    public AddCommentToItem() {
        super(KEY, MENU_ITEM);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        String id = String.valueOf(input.ask("Please enter the Task's id: "));
        String comment = input.ask("Please enter the Task's comment: ");

        Item findItem = tracker.findById(id);
        tracker.addComment(findItem, comment);
    }

}
