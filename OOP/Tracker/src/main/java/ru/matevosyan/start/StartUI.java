package ru.matevosyan.start;
/**
 * Created class StartUI for starting the program.
 * Created on 25.11.2016.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class StartUI {

    /**
     * Instance variable for set the range of user enters action
     */

    private int[] range = new int[] {1, 2, 3};

    /**
     * Input instance variable input.
     */

    private Input input;

    /**
     * Input instance variable tracker.
     */

    private Tracker tracker;

    /**
     * Constructor for StartUI.
     * @param input for assign ot enter data to the program.
     * @param tracker for take the Tracker state.
     */

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Method for initialization program menu and interaction with user.
     */

    public void init() {

        /**
         * variables for user check.
         */

        final String exit = "y";
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillAction();

        do {

            menu.show();
            menu.select(input.ask("Select: ", range));

        } while (!exit.equals(this.input.ask("Exit? (y) ")));

    }

    /**
     * The static main method which is starting our program.
     * @param args is the array argument that pass to main method
     */

    public static void main(String[] args) {

        /**
         * Instance variable StartUI and call the method init.
         */

        Input input = new ValidateInput();
        new StartUI(input, new Tracker()).init();

    }

}
