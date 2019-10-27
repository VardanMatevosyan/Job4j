package ru.matevosyan.action;

import ru.matevosyan.application.Tracker;
import ru.matevosyan.input.Input;
import ru.matevosyan.model.Comments;

import java.util.List;

/**
 * Class ShowItemComments using for implements UserAction to show concrete item comments asking user about item id.
 * And add action to show item comments for user action.
 * Created on 20.12.2016.
 * Changed on 21.10.2019
 * @author Matevosyan Vardan
 * @version 1.0
 * @since 1.0
 */
@UserActionLoader
public class ShowItemComments extends BaseAction {
    private final static int KEY = 9;
    private final static String MENU_ITEM = "Show item comments";

    public ShowItemComments() {
        super(KEY, MENU_ITEM);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        String id = String.valueOf(input.ask("Please enter the Task's id: "));
        List<Comments> itemForComment = tracker.findInCommentsByItemId(id);
        System.out.println("\r\n Comments: \r\n ------------------------------------------------");
        boolean check = true;
        for (Comments itemComments : itemForComment) {
            if (itemComments != null) {
                check = false;
                System.out.println(String.format(" |%s ------------------------------------------------", itemComments + "|\r\n"));
            } else if (check) {
                System.out.println("In this item no comments");
            }
        }

    }

}
