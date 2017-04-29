package ru.matevosyan.start;

import org.junit.Test;
import ru.matevosyan.models.Comments;
import ru.matevosyan.models.Item;
import ru.matevosyan.models.Task;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Class StartUITest created for testing.
 * Created on 07.12.2016.
 * @since 1.0
 * @version 1.0
 * @author Matevosyan Vardan
 */

public class StartUITest {

    /**
     * whenCreateItemThenReturnItem created for test adding item to Tracker.
     */

    @Test
    public void whenCreateItemThenReturnItem() {

        String[] answers = {

                "1", // select menu 1. add item
                "task 1", // items name
                "task desc", // items desc
                "y" // exit
        };

        Tracker tracker = new Tracker();
        Input stub = new StubInput(answers);
        new StartUI(stub, tracker).init();
        Item item = tracker.findByName("task 1");
        assertThat(item, is(tracker.getAll().get(0)));
    }

    /**
     * whenCreateItemThenEditTracker created for testing editItem method in StartUI class.
     */

    @Test
    public void whenCreateItemThenEditTracker() {
        Tracker tracker = new Tracker();
        Item item = new Task("task 1", "task desc 1");
        tracker.add(item);
        String[] answers = {
                "3",
                item.getId(),
                "task 2",
                "task desc 2",
                "y"
        };

        Input stub = new StubInput(answers);
        new StartUI(stub, tracker).init();

        for (Item items : tracker.getAll()) {
            assertThat(items.getName(), is("task 2"));
        }


    }

    /**
     * whenCommentItemThenReturnItemComments created for test adding comments to the item.
     */

    @Test
    public void whenCommentItemThenReturnItemComments() {

        Tracker tracker = new Tracker();
        Item item = new Task("task 1", "task desc 1");
        tracker.add(item);
        String[] answers = {
                "5",
                item.getId(),
                "comment1",
                "n",
                "5",
                item.getId(),
                "comment2",
                "n",
                "5",
                item.getId(),
                "comment3",
                "y"
        };

        Input stub = new StubInput(answers);
        new StartUI(stub, tracker).init();
        final int commentSize = 2;
        Comments[] expComment = {new Comments("comment1"), new Comments("comment2"), new Comments("comment3")};

        assertNotNull(item.getComments().get(2));
        assertThat(item.getComments().get(2).getCommentName(), is(expComment[commentSize].getCommentName()));
    }

    /**
     * whenCreateItemThenFindByNameInTracker created for testing findByName method to find the item by name.
     */

    @Test
    public void whenCreateItemThenFindByNameInTracker() {

        String[] answers = {

            "1",
            "task 1",
            "task desc",
            "y"
        };

        Input stub = new StubInput(answers);
        Tracker tracker = new Tracker();
        new StartUI(stub, tracker).init();
        Item item = tracker.findByName("task 1");

        assertThat(item.getName(), is("task 1"));
    }

    /**
     * whenCreateItemThenFindByIdInTracker created for testing findById method to find the item by id.
     */

    @Test
    public void whenCreateItemThenFindByIdTracker() {

        String[] answer = {
                "1",
                "task 2",
                "task desc",
                "y"
        };

        Input stub = new StubInput(answer);
        Tracker tracker = new Tracker();
        new StartUI(stub, tracker).init();

        for (Item item : tracker.getAll()) {
            item = tracker.findById(item.getId());

            assertThat(item, is(tracker.getAll().get(0)));
        }


    }

    /**
     * whenCreateItemThenFindByDateInTracker created for testing findByDate method to find the item by date.
     */

    @Test
    public void whenCreateItemThenFindByDateTracker() {

        String[] answer = {
                "1",
                "task 3",
                "task desc",
                "y"
        };

        Input stub = new StubInput(answer);
        Tracker tracker = new Tracker();
        new StartUI(stub, tracker).init();

        for (Item item : tracker.getAll()) {
            item = tracker.findByDate(item.getCreate());

            assertThat(item, is(tracker.getAll().get(0)));
        }

    }
    /**
     * whenFindByDateNullThenCheckIt created for testing findByDate method to find the item by date.
     */

    @Test
    public void whenFindByDateNullThenCheckIt() {

        String[] answer = {
                "1",
                "task 3",
                "task desc",
                "y",
        };

        Input stub = new StubInput(answer);
        Tracker tracker = new Tracker();
        new StartUI(stub, tracker).init();

        boolean invalid = true;
        for (Item item : tracker.getAll()) {
            item = tracker.findByDate("11.11.1111");
            if (item == null) {
                invalid = false;
            }
            assertFalse(invalid);
        }

    }

    /**
     * whenCreateItemThenFindByDateInTracker created for testing findByDate method to find the item by date.
     */



    @Test
    public void whenCreateItemThenCheckOutputShowFindByIdTracker() {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Tracker tracker = new Tracker();
        Item itemFirst = new Item("ItemNameFirst", "ItemDescFirst");
        tracker.add(itemFirst);

        String[] answer = {
                "6",
                itemFirst.getId(),
                "y"
        };

        Input stub = new StubInput(answer);
        new StartUI(stub, tracker).init();

        String s = System.getProperty("line.separator");

        assertThat(out.toString(), is(
                "    M-E-N-U" + s + "1. Add new item" + s + "2. Show all item" + s
                        + "3. Edit item" + s + "4. Delete item" + s + "5. Add comment to item" + s + "6. Find item by id" + s
                        + "7. Find item by name" + s + "8. Find item by date" + s + "9. Show item comments" + s + s
                        + " Id: " + itemFirst.getId() + ". " + s
                        + " Name: " + itemFirst.getName() + ". " + s
                        + " Description: " + itemFirst.getDescription() + ". " + s
                        + " Date: " + itemFirst.getCreate() + ". " + s
                        + " ------------------------------------------------" + s
        ));

    }

    /**
     * whenCreateItemThenFindByDateInTracker created for testing findByDate method to find the item by date.
     */

    @Test
    public void whenCreateItemThenCheckOutputShowFindByNameTracker() {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Tracker tracker = new Tracker();
        Item itemFirst = new Item("ItemNameFirst", "ItemDescFirst");
        tracker.add(itemFirst);

        String[] answer = {
                "7",
                itemFirst.getName(),
                "y"
        };

        Input stub = new StubInput(answer);
        new StartUI(stub, tracker).init();

        String s = System.getProperty("line.separator");

        assertThat(out.toString(), is(
                "    M-E-N-U" + s + "1. Add new item" + s + "2. Show all item" + s
                + "3. Edit item" + s + "4. Delete item" + s + "5. Add comment to item" + s + "6. Find item by id" + s
                + "7. Find item by name" + s + "8. Find item by date" + s + "9. Show item comments" + s + s
                + " Id: " + itemFirst.getId() + ". " + s
                + " Name: " + itemFirst.getName() + ". " + s
                + " Description: " + itemFirst.getDescription() + ". " + s
                + " Date: " + itemFirst.getCreate() + ". " + s
                + " ------------------------------------------------" + s
        ));

    }

    /**
     * whenCreateItemThenFindByDateInTracker created for testing findByDate method to find the item by date.
     */

    @Test
    public void whenCreateItemThenCheckOutputShowFindByDateTracker() {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Tracker tracker = new Tracker();
        Item itemFirst = new Item("ItemNameFirst", "ItemDescFirst");
        tracker.add(itemFirst);
        String getDate = String.valueOf(itemFirst.getCreate());

        String[] answer = {
                "8",
                getDate,
                "y"
        };

        Input stub = new StubInput(answer);
        new StartUI(stub, tracker).init();

        String s = System.getProperty("line.separator");

        assertThat(out.toString(), is(
                "    M-E-N-U" + s + "1. Add new item" + s + "2. Show all item" + s
                + "3. Edit item" + s + "4. Delete item" + s + "5. Add comment to item" + s + "6. Find item by id" + s
                + "7. Find item by name" + s + "8. Find item by date" + s + "9. Show item comments" + s + s
                + " Id: " + itemFirst.getId() + ". " + s
                + " Name: " + itemFirst.getName() + ". " + s
                + " Description: " + itemFirst.getDescription() + ". " + s
                + " Date: " + itemFirst.getCreate() + ". " + s
                + " ------------------------------------------------" + s
        ));

        }

    /**
     * whenCreateItemThenDeleteItem using for testing DeleteItem method that can delete item from tracker.
     */

    @Test
    public void whenCreateItemThenDeleteItem() {

        Tracker tracker = new Tracker();
        Item itemFirst = new Task("task 1", "task desc 1");
        Item itemSecond = new Task("task 2", "task desc 2");
        tracker.add(itemFirst);
        tracker.add(itemSecond);

        String[] answer = {
                "4",
                itemFirst.getId(),
                "y"
        };

        Input stub = new StubInput(answer);
        new StartUI(stub, tracker).init();
        assertThat(itemSecond, is(tracker.getAll().get(0)));
        assertFalse(tracker.getAll().get(0).getName().contains("task 1"));
        assertTrue(tracker.getAll().get(0).getName().contains("task 2"));
        assertNull(tracker.getAll().get(1));
        assertNotNull(tracker.getAll().get(0));
    }

    /**
     * whenCreateItemThenDeleteItem using for testing DeleteItem method that can delete item from tracker.
     */

    @Test
    public void whenCreateItemsThenCheckOutputItems() {

        Tracker tracker = new Tracker();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Item itemFirst = new Item("task 1", "task desc 1");
        tracker.add(itemFirst);
        Item itemSecond = new Item("task 2", "task desc 2");
        tracker.add(itemSecond);
        String[] answer = {
                "4",
                itemFirst.getId(),
                "n",
                "2",
                "y"

        };

        Input stub = new StubInput(answer);
        new StartUI(stub, tracker).init();
        String s = System.getProperty("line.separator");

        assertThat(out.toString(), is(
                "    M-E-N-U" + s + "1. Add new item" + s + "2. Show all item" + s
                + "3. Edit item" + s + "4. Delete item" + s + "5. Add comment to item" + s + "6. Find item by id" + s
                + "7. Find item by name" + s + "8. Find item by date" + s + "9. Show item comments" + s
                + "    M-E-N-U" + s + "1. Add new item" + s + "2. Show all item" + s
                + "3. Edit item" + s + "4. Delete item" + s + "5. Add comment to item" + s + "6. Find item by id" + s
                + "7. Find item by name" + s + "8. Find item by date" + s + "9. Show item comments" + s + s
                + " Id: " + itemSecond.getId() + ". " + s
                + " Name: " + itemSecond.getName() + ". " + s
                + " Description: " + itemSecond.getDescription() + ". " + s
                + " Date: " + itemSecond.getCreate() + ". " + s
                + " ------------------------------------------------" + s
        ));

    }

    /**
     * whenCreateItemThenGetAllItem created for test that moment when user see that getAll method can print out.
     * test all that can print out, when user want see all items.
     */

    @Test
    public void whenCreateItemThenGetAllItem() {

        Tracker tracker = new Tracker();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Item item = new Item("Task", "Desc");
        tracker.add(item);
        String[] answer = {
                "2",
                "y"
        };

        Input stub = new StubInput(answer);
        new StartUI(stub, tracker).init();
        String s = System.getProperty("line.separator");

        assertThat(out.toString(), is(
                "    M-E-N-U" + s + "1. Add new item" + s + "2. Show all item" + s
                + "3. Edit item" + s + "4. Delete item" + s + "5. Add comment to item" + s + "6. Find item by id" + s
                + "7. Find item by name" + s + "8. Find item by date" + s + "9. Show item comments" + s + s
                + " Id: " + item.getId() + ". " + s
                + " Name: " + item.getName() + ". " + s
                + " Description: " + item.getDescription() + ". " + s
                + " Date: " + item.getCreate() + ". " + s
                + " ------------------------------------------------" + s
        ));

    }

    /**
     * whenAddCommentToItemThenShowItemsComment created for test all output printing.
     * when user invoke himself in console method ShowItemComments type "9".
     * And get all out print comments with concrete item.
     * test all that can print out, when user want see all comments in item.
     */

    @Test
    public void whenAddCommentToItemThenShowItemsComment() {

        Tracker tracker = new Tracker();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Item item = new Item("Task", "Desc");
        tracker.add(item);
        item.addComment("I send this comment");
        String[] answer = {
                "9",
                item.getId(),
                "y"
        };

        Input stub = new StubInput(answer);
        new StartUI(stub, tracker).init();
        String s = System.getProperty("line.separator");

        assertThat(out.toString(), is("    M-E-N-U" + s + "1. Add new item" + s + "2. Show all item" + s
                + "3. Edit item" + s + "4. Delete item" + s + "5. Add comment to item" + s + "6. Find item by id" + s
                + "7. Find item by name" + s + "8. Find item by date" + s + "9. Show item comments" + s + s
                + " Comments: " + s + " ------------------------------------------------" + s
                + " |I send this comment|" + s + " ------------------------------------------------" + s
        ));

    }



}