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
}
