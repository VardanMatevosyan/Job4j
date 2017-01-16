package ru.matevosyan.start;

import ru.matevosyan.models.Comments;
import ru.matevosyan.models.Item;

/**
 * Created class MenuTracker for add program menu.
 * Created on 20.12.2016.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.1
 */

public class MenuTracker {

    /**
     * Input instance variable input.
     */

    private Input input;

    /**
     * Input instance variable tracker.
     */

    private Tracker tracker;

    /**
     * Maximum user action.
     */

    private final int maxUserAction = 9;

    /**
     * Instance variable for saving all user action.
     * And use it for run specific class, in dependence users selection action.
     */

    private UserAction[] userAction = new UserAction[maxUserAction];

    /**
     * instance availableRange for menu number range
     */

    private int[] availableRange = new int[this.userAction.length];

    /**
     * Number of elements in userAction.
     * Variable one to use in userAction array in 0 position
     *
     */

    private int position = 0;


    /**
     * Constructor MenuTracker.
     * @since 1.0
     * @param input for getting input state
     * @param tracker for getting tracker
     */

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Method fillAction fot fill out user action which invoking new class instance.
     */

    public void fillAction() {

        addAction(new AddItem(1, "Add new item"));
        addAction(new ShowItems(2, "Show all item"));
        addAction(new EditItem(3, "Edit item"));
        addAction(new DeleteItem(4, "Delete item"));
        addAction(new AddCommentToItem(5, "Add comment to item"));
        addAction(new FindItemById(6, "Find item by id"));
        addAction(new FindItemByName(7, "Find item by name"));
        addAction(new FindItemByDate(8, "Find item by date"));
        addAction(new ShowItemComments(9, "Show item comments"));

        /**
         * fill availableRange out
         */

        for (int i = 0; i < this.userAction.length; i++) {
            availableRange[i] = this.userAction[i].key();
        }

    }

    public void addAction(BaseAction action) {
        this.userAction[position++] = action;



    }

    /**
     * method to return concrete key from availableRange
     */

    public int[] getKeys() {
        return availableRange;
    }

    /**
     * Method select created to execute concrete action method execute that contains in array position that user had invoked.
     * @param key user selection
     */

    public void select(int key) {
        this.userAction[key - 1].execute(this.input, this.tracker);
    }

    /**
     * Method show created for showing the list of user actions and action description.
     */

    public void show() {
        System.out.println("    M-E-N-U");
        for (UserAction userAction : this.userAction) {
            if (userAction != null) {
                System.out.println(userAction.info());
            }
        }
    }

    /**
     * Created class AddItem for implements UserAction and add action to add items for user action.
     * Created on 20.12.2016.
     *
     * @author Matevosyan Vardan
     * @version 1.0
     * @since 1.0
     */

    private class AddItem extends BaseAction {


        public AddItem(int key, String name) {
            super(key,name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Please enter the Task's name ");
            String description = input.ask("Please enter the Task's description ");
            tracker.add(new Item(name, description));
        }


    }

    /**
     * Created static class ShowItems for implements UserAction and add action to show all items for user action.
     * Created on 20.12.2016.
     * @author Matevosyan Vardan
     * @version 1.0
     * @since 1.0
     */

    private class ShowItems extends BaseAction {

        public ShowItems(int key, String name) {
            super(key, name);
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

    /**
     * Created class EditItems for implements UserAction and add action to edit items for user action.
     * User answer fo question about items id for edit item and then user can change items name and description.
     * Created on 20.12.2016.
     * @author Matevosyan Vardan
     * @version 1.0
     * @since 1.0
     */

    private class EditItem extends BaseAction {

        public EditItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {

            String id = String.valueOf(input.ask("Please enter the Task's id: ", tracker.fillRangeOfId()));
            String name = input.ask("Please enter the Task's name: ");
            String description = input.ask("Please enter the Task's description: ");

            Item item = new Item(name, description);
            item.setId(id);
            tracker.editItem(item);

        }

    }

    /**
     * Created class DeleteItem for implements UserAction for deleting item.
     * And add action to delete item for user action.
     * Created on 20.12.2016.
     * @author Matevosyan Vardan
     * @version 1.0
     * @since 1.0
     */

    private class DeleteItem extends BaseAction {

        public DeleteItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Please enter the Task's id: ");
            tracker.deleteItem(id);
        }


    }

    /**
     * Created static class AddCommentToItem for implements UserAction fo comment item.
     * And add action to add items comment for user action.
     * Created on 20.12.2016.
     * @author Matevosyan Vardan
     * @version 1.0
     * @since 1.0
     */

    private class AddCommentToItem extends BaseAction {

        public AddCommentToItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Please enter the Task's id: ");
            String comment = input.ask("Please enter the Task's comment: ");

            Item findItem = tracker.findById(id);
                tracker.addComment(findItem, comment);
        }

    }

    /**
     * Created class FindItemById for implements UserAction to find item by id using method FindById from Tracker.
     * And add action to find item by id for user action.
     * Created on 20.12.2016.
     * @author Matevosyan Vardan
     * @version 1.0
     * @since 1.0
     */

    public class FindItemById extends BaseAction {

        public FindItemById(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Please enter the Task's id: ");
            Item itemFindById = tracker.findById(id);
                System.out.println(String.format("\r\n Id: %s. \r\n Name: %s. \r\n Description: %s. \r\n Date: %s. \r\n"
                        + " ------------------------------------------------", itemFindById.getId(), itemFindById.getName(),
                        itemFindById.getDescription(), itemFindById.getCreate()));
        }


    }

    /**
     * Created class FindItemByName for implements UserAction to find item by name using method FindByName from Tracker.
     * And add action to find item by name for user action.
     * Created on 20.12.2016.
     * @author Matevosyan Vardan
     * @version 1.0
     * @since 1.0
     */

    private class FindItemByName extends BaseAction {

        public FindItemByName(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Please enter the Task's name: ");
            Item itemFindByName = tracker.findByName(name);
                System.out.println(String.format("\r\n Id: %s. \r\n Name: %s. \r\n Description: %s. \r\n Date: %s. \r\n"
                        + " ------------------------------------------------", itemFindByName.getId(), itemFindByName.getName(),
                        itemFindByName.getDescription(), itemFindByName.getCreate()));
        }

    }

    /**
     * Created class FindItemByDate for implements UserAction to find item by date using method FindByDate from Tracker.
     * And add action to find item by date for user action.
     * Created on 20.12.2016.
     * @author Matevosyan Vardan
     * @version 1.0
     * @since 1.0
     */

    private class FindItemByDate extends BaseAction {

        public FindItemByDate(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String date = input.ask("Please enter the Task's date: ");
            Item itemFindByDate = tracker.findByDate(Long.parseLong(date));
                System.out.println(String.format("\r\n Id: %s. \r\n Name: %s. \r\n Description: %s. \r\n Date: %s. \r\n"
                        + " ------------------------------------------------", itemFindByDate.getId(), itemFindByDate.getName(),
                        itemFindByDate.getDescription(), itemFindByDate.getCreate()));
        }

    }

    /**
     * Class ShowItemComments using for implements UserAction to show concrete item comments asking user about item id.
     * And add action to show item comments for user action.
     * Created on 20.12.2016.
     * @author Matevosyan Vardan
     * @version 1.0
     * @since 1.0
     */

    private class ShowItemComments extends BaseAction {

        public ShowItemComments(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {

            String id = input.ask("Please enter the Task's id: ");
            Item itemForComment = tracker.findById(id);

            final int maxCommentLength = 5;

            Comments[] comment = itemForComment.getAllComment();
            System.out.println("\r\n Comments: \r\n ------------------------------------------------");
            boolean chack = true;
            for (int i = 0; i < maxCommentLength; i++) {
                if (comment[i] != null && chack) {
                    chack = false;
                    System.out.println(String.format(" |%s ------------------------------------------------", comment[i] + "|\r\n"));
                } else if (comment[i] == null && chack) {
                    i = 1999999999;
                    System.out.println("In this item no comments");
                }
            }

        }

    }
}