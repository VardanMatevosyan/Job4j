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
    public int ask(String question, int[] range) throws MenuOutException {
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
    public int ask(String question, String[] rangeIds) throws NumberFormatException {
        String userId = ask(question);
        boolean exist = false;

        for (String gettingId : rangeIds) {
           if (Integer.parseInt(gettingId) == Integer.parseInt(userId)) {
                exist = true;
                break;
            }
        }

        if (exist) {
            return  Integer.parseInt(userId);
        } else {
            throw new NumberFormatException("Such item with this id does not exist. Please try again");
        }
    }
}
