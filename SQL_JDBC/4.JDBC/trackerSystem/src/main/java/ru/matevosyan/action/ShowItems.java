package ru.matevosyan.action;

import ru.matevosyan.application.Tracker;
import ru.matevosyan.input.Input;
import ru.matevosyan.model.Item;

/**
 * Created static class ShowItems for implements UserAction and add action to show all items for user action.
 * Created on 20.12.2016.
 * Changed on 21.10.2019
 * @author Matevosyan Vardan
 * @version 1.0
 * @since 1.0
 */
@UserActionLoader
public class ShowItems extends BaseAction {
    private final static int KEY = 2;
    private final static String MENU_ITEM = "Show all item";

    public ShowItems() {
        super(KEY, MENU_ITEM);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        for (Item item : tracker.getAll()) {
            if (item != null) {
                System.out.println(String.format("\r\n Id: %s. \r\n Name: %s. \r\n Description: %s. \r\n Date: %s. \r\n"
                                + " ------------------------------------------------", item.getId(), item.getName(),
                        item.getDescription(), item.getCreate()));
            }
        }
    }

}