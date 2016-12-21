package ru.matevosyan.start;

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

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillAction() {
        this.userAction[0] = new AddItem();
        this.userAction[1] = new MenuTracker.ShowItems();
        this.userAction[2] = new EditItems();
    }

    public void select(String key) {
        this.userAction[Integer.parseInt(key) - 1].execute(this.input, this.tracker);
    }

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
     * @since 1.0
     * @author Matevosyan Vardan
     * @version 1.0
     */

    private class AddItem implements UserAction {

        @Override
        public int key(){
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
            return String.format("%s. %s", this.key(), "Add new Item");
        }

    }

    /**
     * Created sctaric class ShowItems for implements UserAction and add action to show all items for user action.
     * Created on 20.12.2016.
     * @since 1.0
     * @author Matevosyan Vardan
     * @version 1.0
     */

    private static class ShowItems implements UserAction {

        @Override
        public int key(){
            return 2;
        }
        @Override
        public void execute(Input input, Tracker tracker) {
            for (Item item : tracker.getAll()) {
                System.out.println(String.format("%s. %s. %s ", item.getId(), item.getName(), item.getDescription()));
            }
        }
        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Show items");
        }

    }
}

class EditItems implements UserAction {

    @Override
    public int key(){
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