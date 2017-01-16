package ru.matevosyan.start;

/**
 * Created for valid input data.
 * Created on 27.12.2016.
 * @since 1.0
 * @version 1.0
 * @author Matevosyan Vardan
 */

public class ValidateInput extends ConsoleInput {

    public int ask(String question, int[] range) {

        boolean invalid = true;
        int value = -1;

        do {
            try {
                value = super.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Please select key from menu");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data again");
            }
        } while (invalid);

        return value;
    }

    public int ask(String question, String[] rangeIds) {
        boolean invalid = true;
        int value = -1;

        do {
            try {
                value = super.ask("Enter items id", rangeIds);
                invalid = false;
            }   catch (NumberFormatException nfe) {
                System.out.println("Such item with this id does not exist. Please enter id again");
            }
        } while (invalid);

        return value;
    }

}

