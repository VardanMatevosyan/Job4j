package ru.matevosyan.start;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Class ConsoleInputTest created for testing.
 * Created on 18.12.2016.
 * @since 1.0
 * @version 1.0
 * @author Matevosyan Vardan
 */

public class ConsoleInputTest {

    /**
     * whenAskForReturnThanReturnScanIn test method for test ConsoleInput class
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
     * whenAskForWrongReturnThanExpectedNotEqualsTwoValues test method for test ConsoleInput class
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
     * whenSetQuestionThanGetOutputStreamvalue test method for test ConsoleInput class
     * when we expected output value
     */

    @Test
    public void whenSetQuestionThanGetOutputStreamvalue() {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        ConsoleInput consoleInput = new ConsoleInput();
        consoleInput.ask("Question");

        assertEquals(out.toString(),"Question");

    }
  }
