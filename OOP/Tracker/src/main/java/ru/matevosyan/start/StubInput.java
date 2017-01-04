package ru.matevosyan.start;

/**
 * Class StubInput implements interface Input and created for testing the programs system input.
 * Created on 07.12.2016.
 * @since 1.0
 * @version 1.0
 * @author Matevosyan Vardan
 */

public class StubInput implements Input {

    /**
     * Instance variable answer for using to save input parameters for testing StartUI class.
     */

    private String[] answers;

    /**
     * Element position in an instance variable answer.
     */

    private int position = 0;

    /**
     * Constructor for StubInput.
     * @param answers for imitation user answer
     */

    public StubInput(String[] answers) {
        this.answers = answers;
    }

    /**
     * Override interface Inputs method ask.
     * @param question use for showing messages to user
     * @return imitation user answers
     */

    @Override
    public String ask(String question) {
        return answers[position++];
    }

    @Override
    public int ask(String question, int[] range) {
        return -1;
    }

    @Override
    public int ask(Tracker tracker) {
        return -1;
    }
}
