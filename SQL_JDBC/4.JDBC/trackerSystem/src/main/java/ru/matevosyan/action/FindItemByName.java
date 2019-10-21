package ru.matevosyan.action;

import ru.matevosyan.application.Tracker;
import ru.matevosyan.input.Input;
import ru.matevosyan.model.Item;

import java.util.List;

/**
 * Created class FindItemByName for implements UserAction to find item by name using method FindByName from Tracker.
 * And add action to find item by name for user action.
 * Created on 20.12.2016.
 * Changed on 21.10.2019
 * @author Matevosyan Vardan
 * @version 1.0
 * @since 1.0
 */

public class FindItemByName extends BaseAction {
    private final static int KEY = 7;
    private final static String MENU_ITEM = "Find item by name";

    public FindItemByName() {
        super(KEY, MENU_ITEM);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        boolean invalid = true;
        int countTry = 0;

        do {
            String name;
            if (countTry <= 0) {
                name = input.ask("Please enter the Task's name: ");
            } else {
                name = input.ask("Please try again: ");
            }

            List<Item> itemFindByName = tracker.findByName(name);
            countTry++;
            for (int i = 0; i < itemFindByName.size() && !itemFindByName.isEmpty(); i++) {
                Item item = itemFindByName.get(i);
                if (item != null) {
                    System.out.println(String.format("\r\n Id: %s. \r\n Name: %s. \r\n Description: %s. \r\n Date: %s. \r\n"
                                    + " ------------------------------------------------", item.getId(), item.getName(),
                            item.getDescription(), item.getCreate()));
                    invalid = false;
                }
            }
        } while (invalid);

    }

}
