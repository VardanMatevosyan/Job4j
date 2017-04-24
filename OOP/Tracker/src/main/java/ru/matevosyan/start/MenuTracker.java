package ru.matevosyan.start;

import ru.matevosyan.models.Comments;
import ru.matevosyan.models.Item;

import java.util.ArrayList;

/**
 * Created class MenuTracker for add program menu.
 * Created on 20.12.2016.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.1
 */

class MenuTracker {

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

    private ArrayList<UserAction> userAction = new ArrayList<>();

//    private UserAction[] userAction = new UserAction[maxUserAction];

    /**
     * instance availableRange for menu number range.
     */

//    private int[] availableRange = new int[this.userAction.length];

    private ArrayList<Integer> availableRange = new ArrayList<>();
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

    MenuTracker(Input input, Tracker tracker) {
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

        for (int i = 0; i < this.userAction.size(); i++) {
            availableRange.get(this.userAction.get(i).key());
        }

    }

    /**
     * Method addAction use to add action to userAction array.
     * @param action concrete class is use as menu point
     */

    public void addAction(BaseAction action) {
//        this.userAction[position++] = action;
        this.userAction.add(action);

    }

    /**
     * method to return concrete key from availableRange.
     * @return availableRange array for getting to menu
     */

//    public int[] getKeys() {
    public ArrayList<Integer> getKeys() {
        return availableRange;
    }

    /**
     * Method select created to execute concrete action method execute that contains in array position that user had invoked.
     * @param key user selection
     * @throws NullPointerException fo select which invoke execute method
     */

    public void select(int key) {
//        this.userAction[key - 1].execute(this.input, this.tracker);
        this.userAction.get(key - 1).execute(this.input, this.tracker);
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

        /**
         * use BaseAction constructor to assign the variable value created own AddItem constructor.
         * @param key use to assign userAction key to return it.
         * @param name use to assign userAction menu point to return it.
         *
         */

        private AddItem(int key, String name) {
            super(key, name);
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

        /**
         * use BaseAction constructor to assign the variable value created own ShowItems constructor.
         * @param key use to assign userAction key to return it.
         * @param name use to assign userAction menu point to return it.
         *
         */


        private ShowItems(int key, String name) {
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

        /**
         * use BaseAction constructor to assign the variable value created own EditItem constructor.
         * @param key use to assign userAction key to return it.
         * @param name use to assign userAction menu point to return it.
         *
         */

        private EditItem(int key, String name) {
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

        /**
         * use BaseAction constructor to assign the variable value created own DeleteItem constructor.
         * @param key use to assign userAction key to return it.
         * @param name use to assign userAction menu point to return it.
         *
         */

        private DeleteItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = String.valueOf(input.ask("Please enter the Task's id: ", tracker.fillRangeOfId()));
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

        /**
         * use BaseAction constructor to assign the variable value created own AddCommentToItem constructor.
         * @param key use to assign userAction key to return it.
         * @param name use to assign userAction menu point to return it.
         *
         */

        private AddCommentToItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = String.valueOf(input.ask("Please enter the Task's id: ", tracker.fillRangeOfId()));
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

    private class FindItemById extends BaseAction {

        /**
         * use BaseAction constructor to assign the variable value created own FindItemById constructor.
         * @param key use to assign userAction key to return it.
         * @param name use to assign userAction menu point to return it.
         *
         */

        private FindItemById(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = String.valueOf(input.ask("Please enter the Task's id: ", tracker.fillRangeOfId()));
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

        /**
         * use BaseAction constructor to assign the variable value created own FindItemByName constructor.
         * @param key use to assign userAction key to return it.
         * @param name use to assign userAction menu point to return it.
         *
         */

        private FindItemByName(int key, String name) {
            super(key, name);
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

                Item itemFindByName = tracker.findByName(name);
                countTry++;
                if (itemFindByName != null) {
                    System.out.println(String.format("\r\n Id: %s. \r\n Name: %s. \r\n Description: %s. \r\n Date: %s. \r\n"
                                    + " ------------------------------------------------", itemFindByName.getId(), itemFindByName.getName(),
                            itemFindByName.getDescription(), itemFindByName.getCreate()));
                    invalid = false;
                }
            } while (invalid);

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

        /**
         * use BaseAction constructor to assign the variable value created own FindItemByDate constructor.
         * @param key use to assign userAction key to return it.
         * @param name use to assign userAction menu point to return it.
         *
         */

        private FindItemByDate(int key, String name) {
            super(key, name);
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
                    date = input.ask("Please try again like this format DD.MM.YYYY:");
                }
                Item itemFindByDate = tracker.findByDate(date);
                countTry++;
                if (itemFindByDate != null) {

                    System.out.println(String.format("\r\n Id: %s. \r\n Name: %s. \r\n Description: %s. \r\n Date: %s. \r\n"
                                    + " ------------------------------------------------", itemFindByDate.getId(), itemFindByDate.getName(),
                            itemFindByDate.getDescription(), itemFindByDate.getCreate()));
                    invalid = false;
                }
            } while (invalid);

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

        /**
         * use BaseAction constructor to assign the variable value created own ShowItemComments constructor.
         * @param key use to assign userAction key to return it.
         * @param name use to assign userAction menu point to return it.
         *
         */

        private ShowItemComments(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = String.valueOf(input.ask("Please enter the Task's id: ", tracker.fillRangeOfId()));
            Item itemForComment = tracker.findById(id);

            final int maxCommentLength = 5;

            Comments[] comment = itemForComment.getAllComment();
            System.out.println("\r\n Comments: \r\n ------------------------------------------------");
            boolean check = true;
            for (int i = 0; i < maxCommentLength; i++) {
                if (comment[i] != null) {
                    check = false;
                    System.out.println(String.format(" |%s ------------------------------------------------", comment[i] + "|\r\n"));
                } else if (comment[i] == null && check) {
                    i = 1999999999;
                    System.out.println("In this item no comments");
                }
            }

        }

    }

}