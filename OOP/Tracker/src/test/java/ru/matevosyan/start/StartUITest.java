package ru.matevosyan.start;

import org.junit.Test;
import ru.matevosyan.models.Comments;
import ru.matevosyan.models.Item;
import ru.matevosyan.models.Task;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static junit.framework.TestCase.assertNull;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Class StartUITest created for testing.
 * Created on 07.12.2016.
 * @since 1.0
 * @version 1.0
 * @author Matevosyan Vardan
 */

public class StartUITest {

    @Test
    public void whenCreateItemThenReturnItem() {

        String[] answers = {

                "1", // select menu 1. add item
                "task 1", // items name
                "task desc", // items desc
                "n" // exit
        };

        Tracker tracker = new Tracker();
        Input stub = new StubInput(answers);
        new StartUI(stub, tracker).init();
        Item item = tracker.findByName("task 1");
        assertThat(item, is(tracker.getAll()[0]));
    }

    @Test
    public void whenCreateItemThenEditTracker() {
        Tracker tracker = new Tracker();
        Item item = new Task("task 1", "task desc 1");
        tracker.add(item);
        String[] answers = {
                "2",
                item.getId(),
                "task 2",
                "task desc 2",
                "n" // exit
        };

        Input stub = new StubInput(answers);
        new StartUI(stub, tracker).init();

        for (Item items : tracker.getAll()){
            assertThat(items.getName(), is("task 2"));
        }


    }

    @Test
    public void whenCommentItemThenReturnItemComments() {

        Tracker tracker = new Tracker();
        Item item = new Task("task 1", "task desc 1");
        tracker.add(item);
        String[] answers = {
                "4",
                item.getId(),
                "comment1",
                "y",
                "4",
                item.getId(),
                "comment2",
                "y",
                "4",
                item.getId(),
                "comment3",
                "y",
                "4",
                item.getId(),
                "comment4",
                "y",
                "4",
                item.getId(),
                "comment5",
                "n"
        };

        Input stub = new StubInput(answers);
        new StartUI(stub, tracker).init();
        final int commentSize = 5;
        Comments[] expComment = new Comments[commentSize];
        expComment[0] = new Comments("comment1");
        expComment[1] = new Comments("comment2");
        expComment[2] = new Comments("comment3");
        expComment[3] = new Comments("comment4");
        expComment[4] = new Comments("comment5");
        assertNotNull(item.getComments()[3]);
        assertThat(item.getComments()[3].getCommentName(), is(expComment[3].getCommentName()));
    }

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
    public void whenCreateItemThenDeleteItem() {

        Tracker tracker = new Tracker();
        Item item = new Task("task 1", "task desc 1");
        Item item2 = new Task("task 2", "task desc 2");
        tracker.add(item);
        tracker.add(item2);

        String[] answer = {
                "3",
                item.getId(),
                "n"
        };

        Input stub = new StubInput(answer);
        new StartUI(stub, tracker).init();
        assertThat(item2,is(tracker.getAll()[0]));
        assertNull(tracker.getAll()[1]);
        assertNotNull(tracker.getAll()[0]);
    }

    @Test
    public void whenCreateItemThenGetAllItem() {

        Tracker tracker = new Tracker();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String[] answer = {
                "1",
                "task 3",
                "task desc 3",
                "y",
                "8",
                "n"
        };

        Input stub = new StubInput(answer);
        new StartUI(stub, tracker).init();
        Item[] item = tracker.getAll();
        String s = System.getProperty("line.separator");

        assertThat(out.toString(), is("___M_E_N_U___ " + s + "1. Add Item " + s + "2. Edit Item " + s +
                "3. Remove Item " + s + "4. Add comment" + s + "5. Find by id " + s + "6. Find by name " +
                s + "7. Find by date " + s + "8. Get all items" + s + "9. Exit" + s + s +  Arrays.toString(item) + s));
    }

}