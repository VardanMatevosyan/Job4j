package ru.matevosyan.start;

/**
 * Class StubInput implements interface Input and created for testing the programs system input.
 * Created on 07.12.2016.
 * @since 1.0
 * @version 1.0
 * @author Matevosyan Vardan
 */

public class StubInput implements Input {

    private String[] answers;
    private int position = 0;

    public StubInput(String[] answers) {
        this.answers = answers;
    }

    @Override
    public String ask(String question) {
        return answers[position++];
    }
}
