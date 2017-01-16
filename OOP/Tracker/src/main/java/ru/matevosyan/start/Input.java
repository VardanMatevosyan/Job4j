package ru.matevosyan.start;

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
     * @return String value
     */

    int ask(String question, int[] range);

    /**
     * Method ask for overloading method ask(String question) for case when user enter wrong number.
     * @param question use to ask question
     * @param  rangeIds for get all items id
     * @return String id
     */

    int ask(String question, String[] rangeIds);
}
