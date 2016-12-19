package ru.matevosyan.start;
import ru.matevosyan.models.Item;
import java.util.Arrays;

/**
 * Created class StartUI for starting the program.
 * Created on 25.11.2016.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class StartUI {

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

        final int one = 1;
        final int two = 2;
        final int three = 3;
        final int four = 4;
        final int five = 5;
        final int six = 6;
        final int seven = 7;
        final int eight = 8;
        final int nine = 9;

        System.out.println("___M_E_N_U___ \r\n" + "1. Add Item \r\n" + "2. Edit Item \r\n" + "3. Remove Item \r\n" + "4. Add comment\r\n" + "5. Find by id \r\n" + "6. Find by name \r\n" + "7. Find by date \r\n" + "8. Get all items\r\n" + "9. Exit\r\n");

        String number = input.ask("Please enter the number of Task's: ");

        while (Integer.parseInt(number) != nine) {

            if (Integer.parseInt(number) == one) {

                String name = input.ask("Please enter the Task's name: ");
                String description = input.ask("Please enter the Task's description: ");
                this.tracker.add(new Item(name, description));

            }

            if (Integer.parseInt(number) == two) {

                String id = input.ask("Please enter the Task's id: ");
                Item item = this.tracker.findById(id);

                String name = input.ask("Please enter the Task's name: ");
                item.setName(name);

                String description = input.ask("Please enter the Task's description: ");
                item.setDescription(description);

                this.tracker.editItem(item);
            }

            if (Integer.parseInt(number) == three) {

                String id = input.ask("Please enter the Task's id: ");
                this.tracker.deleteItem(id);

            }

            if (Integer.parseInt(number) == four) {

                String id = input.ask("Please enter the Task's id: ");
                String comment = input.ask("Please enter the Task's comment: ");

                Item findItem = this.tracker.findById(id);
                this.tracker.addComment(findItem, comment);

            }

            if (Integer.parseInt(number) == five) {

                String id = input.ask("Please enter the Task's id: ");
                Item itemFindById = this.tracker.findById(id);
                System.out.println(itemFindById);

            }

            if (Integer.parseInt(number) == six) {

                String name = input.ask("Please enter the Task's name: ");
                Item itemFindByName = this.tracker.findByName(name);
                System.out.println(itemFindByName);

            }

            if (Integer.parseInt(number) == seven) {

                String date = input.ask("Please enter the Task's date: ");
                Item itemFindByDate = this.tracker.findByDate(Long.parseLong(date));
                System.out.println(itemFindByDate);

            }

            if (Integer.parseInt(number) == eight) {

                System.out.println(Arrays.toString(this.tracker.getAll()));

            }

           String askForContinue = input.ask("Would you like to continue, y/n?: ");

            if (askForContinue.equals("y")) {

                number = input.ask("Please enter the number of Task's: ");

            } else if (askForContinue.equals("n")) {

                number = "9";

            }
        }

    }

    /**
     * The static main method which is starting our program.
     * @param args is the array argument that pass to main method
     */

    public static void main(String[] args) {

        /**
         * Instance variable StartUI and call the method init.
         */

        Input input = new ConsoleInput();
        new StartUI(input, new Tracker()).init();

    }

}
