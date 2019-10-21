package ru.matevosyan.action;

import ru.matevosyan.application.Tracker;
import ru.matevosyan.input.Input;
import ru.matevosyan.model.Item;

import java.util.List;

/**
 * Created class FindItemByDate for implements UserAction to find item by date using method FindByDate from Tracker.
 * And add action to find item by date for user action.
 * Created on 20.12.2016.
 * @author Matevosyan Vardan
 * @version 1.0
 * @since 1.0
 */

public class FindItemByDate extends BaseAction {
    private final static int KEY = 8;
    private final static String MENU_ITEM = "Find item by date";

    public FindItemByDate() {
        super(KEY, MENU_ITEM);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        boolean invalid = true;
        int countTry = 0;

        do {
            String date;
            if (countTry <= 0) {
                date = input.ask("Please enter the Task's date: ");
            } else {
                date = input.ask("Please try again like this format yyyy-MM-dd:");
            }
            List<Item> itemFindByDate = tracker.findByDate(date);
            assert itemFindByDate != null;
            for (int i = 0; i < itemFindByDate.size() && !itemFindByDate.isEmpty(); i++) {
                Item item = itemFindByDate.get(i);
                countTry++;
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
