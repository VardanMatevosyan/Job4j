package ru.matevosyan.start;

import ru.matevosyan.models.Comments;
import ru.matevosyan.models.Item;

/**
 * Created class MenuTracker for add program menu.
 * Created on 20.12.2016.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
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
     * Number of elements in userAction.
     * Variable one to use in userAction array in 0 position
     *
     */

    private final int one = 1;

    /**
     * Variable two to use in userAction array in 1 position.
     */

    private final int two = 2;

    /**
     * Variable three to use in userAction array in 2 position.
     */

    private final int three = 3;

    /**
     * Variable four to use in userAction array in 3 position.
     */

    private final int four = 4;

    /**
     * Variable five to use in userAction array in 4 position.
     */

    private final int five = 5;

    /**
     * Variable six to use in userAction array in 5 position.
     */

    private final int six = 6;

    /**
     * Variable seven to use in userAction array in 6 position.
     */

    private final int seven = 7;

    /**
     * Variable eight to use in userAction array in 7 position.
     */

    private final int eight = 8;

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

        /**
         * Number of elements in userAction.
         * @param zero to use in userAction array
         */

        final int zero = 0;

        this.userAction[zero] = this.new AddItem();
        this.userAction[one] = this.new ShowItems();
        this.userAction[two] = this.new EditItems();
        this.userAction[three] = this.new DeleteItem();
        this.userAction[four] = this.new AddCommentToItem();
        this.userAction[five] = this.new FindItemById();
        this.userAction[six] = this.new FindItemByName();
        this.userAction[seven] = this.new FindItemByDate();
        this.userAction[eight] = this.new ShowItemComments();
    }

    /**
     * Method select created to execute concrete action method execute that contains in array position that user had invoked.
     * @param key user selection
     */

    public void select(String key) {
        this.userAction[Integer.parseInt(key) - 1].execute(this.input, this.tracker);
    }

    /**
     * Method show created for showing the list of user actions and action description.
     */

    public void show() {
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

    private class AddItem implements UserAction {

        @Override
        public int key() {
            return one;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Please enter the Task's name ");
            String description = input.ask("Please enter the Task's description ");
            tracker.add(new Item(name, description));
        }

        @Override
        public String info() {
            System.out.println("    M-E-N-U");
            return String.format("%s. %s", this.key(), "Add new Item");
        }

    }

    /**
     * Created static class ShowItems for implements UserAction and add action to show all items for user action.
     * Created on 20.12.2016.
     * @author Matevosyan Vardan
     * @version 1.0
     * @since 1.0
     */

    private class ShowItems implements UserAction {

        @Override
        public int key() {
            return two;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Show items");
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

    private class EditItems implements UserAction {

        @Override
        public int key() {
            return three;
        }

        @Override
        public void execute(Input input, Tracker tracker) {

            String id = input.ask("Please enter the Task's id: ");
            String name = input.ask("Please enter the Task's name: ");
            String description = input.ask("Please enter the Task's description: ");

            Item item = new Item(name, description);
            item.setId(id);
            tracker.editItem(item);

        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Edit items");
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

    private class DeleteItem implements UserAction {

        @Override
        public int key() {
            return four;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Please enter the Task's id: ");
            Item item = tracker.findById(id);
            if (item != null) {
                tracker.deleteItem(id);
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Delete items");
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

    private class AddCommentToItem implements UserAction {

        @Override
        public int key() {
            return five;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Please enter the Task's id: ");
            String comment = input.ask("Please enter the Task's comment: ");

            Item findItem = tracker.findById(id);
            if (findItem != null) {
                tracker.addComment(findItem, comment);
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Add comment to item");
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

    private class FindItemById implements UserAction {

        @Override
        public int key() {
            return six;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Please enter the Task's id: ");
            Item itemFindById = tracker.findById(id);
            System.out.println(itemFindById);
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find item by id");
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

    private class FindItemByName implements UserAction {

        @Override
        public int key() {
            return seven;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Please enter the Task's name: ");
            Item itemFindByName = tracker.findByName(name);
            System.out.println(itemFindByName);
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find item by name");
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

    private class FindItemByDate implements UserAction {

        @Override
        public int key() {
            return eight;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String date = input.ask("Please enter the Task's date: ");
            Item itemFindByDate = tracker.findByDate(Long.parseLong(date));
            System.out.println(itemFindByDate);
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find item by date");
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

    private class ShowItemComments implements UserAction {

        /**
         * Number for method key return value.
         */

        private final int nine = 9;

            @Override
            public int key() {
                return nine;
            }

            @Override
            public void execute(Input input, Tracker tracker) {

                String id = input.ask("Please enter the Task's id: ");
                Item itemForComment = tracker.findById(id);

                final int maxCommentLength = 5;

                Comments[] comment = itemForComment.getAllComment();
                System.out.println("\r\n Comments: \r\n ------------------------------------------------");

                for (int i = 0; i < maxCommentLength; i++) {
                    if (comment[i] != null) {
                        System.out.println(String.format(" |%s ------------------------------------------------", comment[i] + "|\r\n"));
                    }
                }

            }

            @Override
            public String info() {
                return String.format("%s. %s", this.key(), "Show item comments \r\n");
            }

        }
    }