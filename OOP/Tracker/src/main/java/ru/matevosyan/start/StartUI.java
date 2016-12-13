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
     * The static main method which is starting our program.
     */

    private Input input;
    private Tracker tracker = new Tracker();

    public StartUI(Input input) {
        this.input = input;
    }

    public void init() {

        System.out.println("___M_E_N_U___ \n\r" + "1. Add Item \n\r" + "2. Edit Item \n\r" + "3. Remove Item \n\r" + "4. Add comment\n\r" + "5. Find by id \n\r" + "6. Find by name \n\r" + "7. Find by date \n\r" + "8. Get all items\n\r" + "9. Exit\n\r" );

        String number = input.ask("Please enter the number of Task's: ");

        while(Integer.parseInt(number) != 9) {

            if (Integer.parseInt(number) == 1) {

                String name = input.ask("Please enter the Task's name: ");
                String description = input.ask("Please enter the Task's description: ");
                this.tracker.add(new Item(name, description));

            }

            if (Integer.parseInt(number) == 2) {

                String id = input.ask("Please enter the Task's id: ");
                Item item = this.tracker.findById(id);

                String name = input.ask("Please enter the Task's name: ");
                item.setName(name);

                String description = input.ask("Please enter the Task's description: ");
                item.setDescription(description);

                this.tracker.editItem(item);
            }

            if (Integer.parseInt(number) == 3) {

                String id = input.ask("Please enter the Task's id: ");
                this.tracker.deleteItem(id);

            }

            if (Integer.parseInt(number) == 4) {

                String id = input.ask("Please enter the Task's id: ");
                String comment = input.ask("Please enter the Task's comment: ");

                Item findItem = this.tracker.findById(id);
                this.tracker.addComment(findItem, comment);

            }

            if (Integer.parseInt(number) == 5) {

                String id = input.ask("Please enter the Task's id: ");
                Item itemFindById = this.tracker.findById(id);
                System.out.println(itemFindById);

            }

            if (Integer.parseInt(number) == 6) {

                String name = input.ask("Please enter the Task's name: ");
                Item itemFindByName = this.tracker.findByName(name);
                System.out.println(itemFindByName);

            }

            if (Integer.parseInt(number) == 7) {

                String date = input.ask("Please enter the Task's date: ");
                Item itemFindByDate = this.tracker.findByDate(Long.parseLong(date));
                System.out.println(itemFindByDate);

            }

            if (Integer.parseInt(number) == 8) {

                System.out.println(Arrays.toString(this.tracker.getAll()));

            }

           String askForContinue = input.ask("Would you like to continue, y/n?: ");

            if (askForContinue.equals("y")) {

                number = input.ask("Please enter the number of Task's: ");

            }

            else if (askForContinue.equals("n")) {

                number = "9";

            }
        }

    }

    public static void main(String[] args) {

        /**
         * Instance variable StartUI and call the method init.
         */

        Input input = new ConsoleInput();
        new StartUI(input).init();

    }

}
