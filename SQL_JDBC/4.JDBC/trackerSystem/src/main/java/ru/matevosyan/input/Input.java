package ru.matevosyan.input;

import java.util.ArrayList;

/**
 * Interface Input.
 * Created on 07.12.2016.
 * @since 1.0
 * @version 1.0
 * @author Matevosyan Vardan
 */

public interface Input {

    /**
     * Method ask for using in ConsoleInput and StubInput.
     * @param question use for passing question value
     * @return String value
     */

    String ask(String question);

    /**
     * Method ask for overloading method ask with int value for case when user enter wrong parameter.
     * And when enters number (which is item or task or bug) out of the range using in ConsoleInput and StubInput.
     * @param question use for passing question value
     * @param range use the range of keys from userAction
     * @return String value
     */

    int ask(String question, ArrayList<Integer> range);

}
