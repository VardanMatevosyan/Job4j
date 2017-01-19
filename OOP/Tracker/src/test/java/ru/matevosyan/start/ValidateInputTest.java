package ru.matevosyan.start;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class ConsoleInputTest created for testing.
 * Created on 18.01.2017.
 * @since 1.0
 * @version 1.0
 * @author Matevosyan Vardan
 */
public class ValidateInputTest {

    /**
     * whenMenuPointIsIllegalThanThrowException for testing validate input.
     */

    @Test
    public void whenMenuPointIsIllegalThanThrowException() {
        String answer = "4n";
        int[] range = {1, 2, 3};
        boolean invalid = true;

        ByteArrayInputStream in = new ByteArrayInputStream(answer.getBytes());
        System.setIn(in);

        Input validate = new ConsoleInput();
            try {
                validate.ask("Enter menu point", range);
            } catch (MenuOutException moe) {
                invalid = true;
            } catch (NumberFormatException nfe) {
                invalid = false;
            }

        assertThat(invalid, is(false));

    }

    /**
     * whenMenuPointIsOutOfRangeThanThrowException for testing validate input.
     */
    @Test
    public void whenMenuPointIsOutOfRangeThanThrowException() {
        String answer = "4";
        int[] range = {1, 2, 3};
        boolean invalid = false;
        String question = "Enter menu point";

        ByteArrayInputStream in = new ByteArrayInputStream(answer.getBytes());
        System.setIn(in);

        ConsoleInput validate = new ConsoleInput();
            try {
                validate.ask(question, range);
            } catch (MenuOutException moe) {
                invalid = true;
            }

        assertThat(invalid, is(true));
    }

    /**
     * whenMenuPointIsOKThanReturnMenuPoint for testing validate input.
     */
    @Test
    public void whenMenuPointIsOKThanReturnTrue() {
        String answer = "3";
        int[] range = {1, 2, 3};
        boolean invalid = true;

        ByteArrayInputStream in = new ByteArrayInputStream(answer.getBytes());
        System.setIn(in);

        Input validate = new ValidateInput();
            try {
                validate.ask("Enter menu point", range);
            } catch (NumberFormatException nfe) {
                invalid = false;
            }

        assertThat(invalid, is(true));
    }

    /**
     * whenMenuPointIsOutOfRangeAfterThatIsInTheRangeThanReturnTrue for testing validate input.
     */

    @Test
    public void whenMenuPointIsOutOfRangeAfterThatIsInTheRangeThanReturnTrue() {
        String answer = "4";
        String answer2 = "3";

        int[] range = {1, 2, 3};
        boolean invalid = true;
        String question = "Enter menu point";

        ByteArrayInputStream in = new ByteArrayInputStream(answer.getBytes());
        System.setIn(in);

        ByteArrayInputStream in2 = new ByteArrayInputStream(answer2.getBytes());
        System.setIn(in2);

        Input validate = new ValidateInput();
        try {
            validate.ask(question, range);
        } catch (MenuOutException moe) {
            invalid = false;
        }

        assertThat(invalid, is(true));
    }

    /**
     * whenItemsIdIsOKThanReturnTrue for testing validate input.
     */
    @Test
    public void whenItemsIdIsOKThanReturnTrue() {
        String answer = "3";
        String[] range = {"1", "2", "3"};
        boolean invalid = true;

        ByteArrayInputStream in = new ByteArrayInputStream(answer.getBytes());
        System.setIn(in);

        Input validate = new ValidateInput();
        try {
            validate.ask("Enter id", range);
        } catch (NumberFormatException nfe) {
            invalid = false;
        }

        assertThat(invalid, is(true));
    }

    /**
     * whenItemsIdIsNotOKThanReturnTrue for testing validate input.
     */
    @Test
    public void whenItemsIdIsNotOKThanReturnTrue() {
        String answer = "4";
        String[] range = {"1", "2", "3"};
        boolean invalid = true;

        ByteArrayInputStream in = new ByteArrayInputStream(answer.getBytes());
        System.setIn(in);

        Input validate = new ValidateInput();
        try {
            validate.ask("Enter id", range);
        } catch (NoSuchElementException nfe) {
            invalid = false;
        }

        assertThat(invalid, is(false));
    }
}
