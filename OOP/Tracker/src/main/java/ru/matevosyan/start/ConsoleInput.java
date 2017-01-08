package ru.matevosyan.start;

import java.util.Scanner;

/**
 * Class BasicAction implements interface UserAction.
 * Created for override key and info methods in this class.
 * Created on 07.12.2016.
 * @since 1.0
 * @version 1.0
 * @author Matevosyan Vardan
 */

public class ConsoleInput implements Input {

    /**
     * variable scanner for reading user input data.
     */

    private Scanner scanner = new Scanner(System.in);

    /**
     * Override interface Inputs method ask.
     * @param question use for showing messages to user
     * @return user reading data
     */

    @Override
    public String ask(String question) {

        System.out.print(question);
        return scanner.nextLine();

    }

    @Override
    public int ask(String question, int[] range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }

        if (exist) {
            return key;
        } else {
            throw new MenuOutException("Out of menu range. ");
        }

    }

    @Override
    public int ask(Tracker tracker) {
        int userId = Integer.valueOf(ask("Please enter the Task's id: "));
        int id;
        boolean exist = false;

        for (int i = 0; i < tracker.getAll().length; i++) {
           id = Integer.parseInt(tracker.getAll()[i].getId());
            if (id == userId) {
                exist = true;
                break;
            }
        }

        if (exist) {
            return userId;
        } else {
            throw new IdExistException("Such item with this id does not exist. Please try again");
        }
    }
}
