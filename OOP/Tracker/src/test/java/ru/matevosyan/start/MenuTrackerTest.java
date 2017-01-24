package ru.matevosyan.start;

import org.junit.Test;
import ru.matevosyan.models.Item;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Class MenuTrackerTest created for testing.
 * Created on 24.01.2017.
 * @since 1.0
 * @version 1.0
 * @author Matevosyan Vardan
 */
public class MenuTrackerTest {

    /**
     * whenCreateItemThenFindByNameInTrackerIsNotNull created for testing findByName method to find the item by name.
     */

    @Test
    public void whenCreateItemThenFindByNameInTrackerIsNotNull() {

        String[] answers = {

                "1",
                "Name",
                "task desc",
                "n",
                "7",
                "Name",
                "y"

        };


        Input stub = new StubInput(answers);
        Tracker tracker = new Tracker();
        new StartUI(stub, tracker).init();
        Item item = tracker.getAll()[0];

        assertThat(item.getName(), is("Name"));

    }

    /**
     * whenCreateItemThenFindByNameInTrackerIsNull created for testing findByName method to find the item by name.
     */

    @Test
    public void whenCreateItemThenFindByNameInTrackerIsNull() {

        String[] answers = {

                "1",
                "Name",
                "task desc",
                "n",
                "7",
                "NotName",
                "n",
                "Name",
                "y"

        };


        Input stub = new StubInput(answers);
        Tracker tracker = new Tracker();
        new StartUI(stub, tracker).init();
        Item item = tracker.getAll()[0];

        assertNotNull(item);
    }

    /**
     * whenCreateItemThenFindByDateInTrackerIsNull created for testing findByName method to find the item by name.
     */

    @Test
    public void whenCreateItemThenFindByDateInTrackerIsNull() {

        Item itemForFindByDate = new Item("NameLayer", "Description");

        String[] answers = {

                "1",
                "Name",
                "task desc",
                "n",
                "8",
                "23.03.2343",
                itemForFindByDate.getCreate(),
                "y"

        };


        Input stub = new StubInput(answers);
        Tracker tracker = new Tracker();
        new StartUI(stub, tracker).init();

        Item item = tracker.getAll()[0];
        assertThat(item.getName(), is("Name"));
    }

    /**
     * whenCreateItemThenFindByDateInTrackerIsNotNull created for testing findByName method to find the item by name.
     */

    @Test
    public void whenCreateItemThenFindByDateInTrackerIsNotNull() {

        Item itemForFindByDate = new Item("NameLayer", "Description");

        String[] answers = {

                "1",
                "Name",
                "task desc",
                "n",
                "8",
                itemForFindByDate.getCreate(),
                "y"

        };


        Input stub = new StubInput(answers);
        Tracker tracker = new Tracker();
        new StartUI(stub, tracker).init();

        Item item = tracker.getAll()[0];
        assertThat(item.getName(), is("Name"));
    }
}
