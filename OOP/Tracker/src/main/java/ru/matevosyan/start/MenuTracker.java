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

    private Input input;
    private Tracker tracker;
    private UserAction[] userAction = new UserAction[9];

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
        this.userAction[0] = this.new AddItem();
        this.userAction[1] = new MenuTracker.ShowItems();
        this.userAction[2] = new EditItems();
        this.userAction[3] = this.new DeleteItem();
        this.userAction[4] = this.new AddCommentToItem();
        this.userAction[5] = this.new FindItemById();
        this.userAction[6] = this.new FindItemByName();
        this.userAction[7] = this.new FindItemByDate();
        this.userAction[8] = this.new ShowItemComments();
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
            return 1;
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
     *
     * @author Matevosyan Vardan
     * @version 1.0
     * @since 1.0
     */

    private static class ShowItems implements UserAction {

        @Override
        public int key() {
            return 2;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            for (Item item : tracker.getAll()) {
                if (item != null) {
                    System.out.println(String.format("\r\n Id: %s. \r\n Name: %s. \r\n Description: %s. \r\n Date: %s. \r\n" +
                            " ------------------------------------------------", item.getId(), item.getName(),
                            item.getDescription(), item.getCreate()));
                }
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Show items");
        }

    }

    private class DeleteItem implements UserAction {

        @Override
        public int key() {
            return 4;
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

    private class AddCommentToItem implements UserAction {

        @Override
        public int key() {
            return 5;
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

    private class FindItemById implements UserAction {

        @Override
        public int key() {
            return 6;
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

    private class FindItemByName implements UserAction {

        @Override
        public int key() {
            return 7;
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

    private class FindItemByDate implements UserAction {

        @Override
        public int key() {
            return 8;
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

    private class ShowItemComments implements UserAction {

            @Override
            public int key() {
                return 9;
            }

            @Override
            public void execute(Input input, Tracker tracker) {
                int maxCommentLength = 5;
                for (Item item : tracker.getAll()) {
                    Comments[] comment = item.getAllComment();
                    System.out.println("\r\n Comments: \r\n ------------------------------------------------");
                    for(int i = 0; i < maxCommentLength; i++) {
                        if (comment[i] != null) {
                            System.out.println(String.format(" |%s ------------------------------------------------", comment[i] + "|\r\n"));
                        }
                    }
                }
            }

            @Override
            public String info() {
                return String.format("%s. %s", this.key(), "Show item comments \r\n");
            }

        }
    }


class EditItems implements UserAction {

    @Override
    public int key() {
        return 3;
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