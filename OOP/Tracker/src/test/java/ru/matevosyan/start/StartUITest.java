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
    public void whenCreateItemThenFindByNameInTracker() {

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

    @Test
    public void whenCreateItemThenFindByIdTracker() {

        String[] answer = {
                "1",
                "task 2",
                "task desc",
                "n"
        };

        Input stub = new StubInput(answer);
        Tracker tracker = new Tracker();
        new StartUI(stub, tracker).init();

        for (Item item : tracker.getAll()){
            item = tracker.findById(item.getId());

            assertThat(item, is(tracker.getAll()[0]));
        }


    }

    @Test
    public void whenCreateItemThenFindByDateTracker() {

        String[] answer = {
                "1",
                "task 3",
                "task desc",
                "n"
        };

        Input stub = new StubInput(answer);
        Tracker tracker = new Tracker();
        new StartUI(stub, tracker).init();

        for (Item item : tracker.getAll()){
            item = tracker.findByDate(item.getCreate());

            assertThat(item, is(tracker.getAll()[0]));
        }


    }

    @Test
    public void whenCreateItemThenEditTracker() {

        String id = "0";
        Tracker tracker = new Tracker();

        for (Item item : tracker.getAll()){
            id = item.getId();
        }

        String[] answer = {
                "1",
                "task 3",
                "task desc",
                "y",
                "2",
                id,
                "task 4",
                "task desc",
                "n"
        };

        Input stub = new StubInput(answer);
        new StartUI(stub, tracker).init();

        for (Item item : tracker.getAll()){
           assertThat(item.getName(), is("task 4"));
        }


    }
}