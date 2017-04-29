package ru.matevosyan.start;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotEquals;


/**
 * Class ConsoleInputTest created for testing.
 * Created on 18.12.2016.
 * @since 1.0
 * @version 1.0
 * @author Matevosyan Vardan
 */

public class ConsoleInputTest {

    /**
     * whenAskForReturnThanReturnScanIn test method for test ConsoleInput class.
     * when we expected right value
     */

    @Test
    public void whenAskForReturnThanReturnScanIn() {

        String testText = "Hello";
        ByteArrayInputStream in = new ByteArrayInputStream(testText.getBytes());
        System.setIn(in);

        ConsoleInput input = new ConsoleInput();
        assertEquals(input.ask("Question"), testText);

    }

    /**
     * whenAskForWrongReturnThanExpectedNotEqualsTwoValues test method for test ConsoleInput class.
     * when we expected wrong value
     */

    @Test
    public void whenAskForWrongReturnThanExpectedNotEqualsTwoValues() {

        String testTextWrong = "World";
        ByteArrayInputStream inSecond = new ByteArrayInputStream(testTextWrong.getBytes());
        System.setIn(inSecond);

        ConsoleInput input = new ConsoleInput();
        assertNotEquals(input.ask("Question"), "Hello");

    }

    /**
     * whenSetQuestionThanGetOutputStreamValue test method for test ConsoleInput class.
     * when we expected output value
     */

    @Test
    public void whenSetQuestionThanGetOutputStreamValue() {

        String testTextWrong = System.getProperty("line.separator");
        ByteArrayInputStream inSecond = new ByteArrayInputStream(testTextWrong.getBytes());
        System.setIn(inSecond);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        ConsoleInput consoleInput = new ConsoleInput();
        consoleInput.ask("Question");

        assertEquals(out.toString(), "Question");

    }

    /**
     * Created method whenEnterMenuPointCheckIfItEqualsWithValueInRange for test ConsoleInput class.
     * when we enter menu point value
     */

    @Test
    public void whenEnterMenuPointCheckIfItEqualsWithValueInRange() {

        int menuPoint = 1;
        ArrayList<Integer> range = new ArrayList<>();
        range.add(1);
        range.add(2);
        range.add(3);

        ByteArrayInputStream in = new ByteArrayInputStream(String.valueOf(menuPoint).getBytes());
        System.setIn(in);

        ConsoleInput consoleInput = new ConsoleInput();
        int key = consoleInput.ask("Enter menu point", range);

        assertThat(key, is(1));
    }

    /**
     * Created method whenEnterWrongMenuPointCheckIfItEqualsWithValueInRangeIfItDoesThanThrowException for test ConsoleInput class.
     * when we enter menu point value out of range
     */

    @Test
    public void whenEnterWrongMenuPointCheckIfItEqualsWithValueInRangeIfItDoesThanThrowMOException() {

        int menuPoint = 4;
        ArrayList<Integer> range = new ArrayList<>();
        range.add(1);
        range.add(2);
        range.add(3);
        boolean isOkOrNot = false;

        ByteArrayInputStream in = new ByteArrayInputStream(String.valueOf(menuPoint).getBytes());
        System.setIn(in);

        ConsoleInput consoleInput = new ConsoleInput();
        try {
            consoleInput.ask("Enter menu point", range);
        } catch (MenuOutException moe) {
            isOkOrNot = true;
        }

        assertThat(isOkOrNot, is(true));

    }

}
