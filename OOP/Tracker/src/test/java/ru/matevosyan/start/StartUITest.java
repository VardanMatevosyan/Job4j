package ru.matevosyan.start;

import org.junit.Test;
import ru.matevosyan.models.Item;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Class StartUITest created for testing.
 * Created on 07.12.2016.
 * @since 1.0
 * @version 1.0
 * @author Matevosyan Vardan
 */

public class StartUITest {
    @Test
    public void whenCreateItemThenSaveInTracker() {

        String[] answers = {

            "1", // select menu 1. add item
            "task 1", // items name
            "task desc", // items desc
            "n" // exit
        };

        Input stub = new StubInput(answers);
        Tracker tracker = new Tracker();

        new StartUI(stub, tracker).init();
        Item item = tracker.findByName("task 1");
        assertThat(item.getName(), is("task 1"));
    }
}