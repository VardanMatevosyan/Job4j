package ru.matevosyan.start;

import ru.matevosyan.models.Item;

/**
 * Created by Admin on 21.12.2016.
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
    }

    public void show() {
        for (UserAction userAction : this.userAction) {
            System.out.println(userAction.info());
        }
    }

    private class AddItem implements UserAction {
        public int key(){
            return 1;
        }
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Please enter the Task's name");
            String description = input.ask("Please enter the Task's description");
            tracker.add(new Item(name, description));
        }

        public String info() {
            return String.format("%s, %s", this.key(), " Add task");
        }
    }
}
