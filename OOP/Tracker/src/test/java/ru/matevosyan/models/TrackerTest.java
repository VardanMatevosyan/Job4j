package ru.matevosyan.models;

import org.junit.Test;
import ru.matevosyan.start.Tracker;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class TrackerTest created for testing Tracker class.
 * Created on 30.11.2016.
 * @author Matevosyan Vardan
 * @since 1.0
 * @version 1.0
 */

public class TrackerTest {

    // assign

    /**
     * Instance variable tracker using for accesses to the methods in class Tracker and can test them.
     */

    private Tracker tracker = new Tracker();

    /**
     * Method whenAddItemThenReturnItem created for testing add method? that added new item to array items.
     * it's may the specific type of item, for example: new Task or new Bug.
     */

    @Test
    public void whenAddItemThenReturnItem() {

        //assign
        String itemName = "firstTask";
        String itemDesc = "firstDescription";

        //act
        this.tracker.add(new Task("firstTask", "firstDescription"));

        //check
        for (Item item : this.tracker.getAll()) {
            assertThat(item.getName(), is(itemName));
            assertThat(item.getDescription(), is(itemDesc));
        }


    }

    /**
     * Method whenEditItemThenReturnEditItem created for testing editItem method? that edit already exist item from array of items.
     */

    @Test
    public void whenEditItemThenReturnEditItem() {

        //assign
        String itemName = "SecondTask";
        String itemEdit = "firstTask";

        //act
        this.tracker.add(new Task("firstTask", "firstDescription"));
        for (Item item : tracker.getAll()) {
            String itemGetName = item.getName();
            if (!(item.getName().equals(itemName))) {
                this.tracker.editItem(item);
            }
            String itemGetEditName = item.getName();

         //check
            assertTrue("It is edit", (itemGetEditName.contains(itemGetName)));
            assertThat(itemEdit, is(item.getName()));
        }

    }

    /**
     * Method whenFindItemByIdThanReturnItem created for testing findById method to find exist item from array by id.
     */

    @Test
    public void whenFindItemByIdThanReturnItem() {

        //assign
        Item expected = this.tracker.add(new Item("TaskName", "DescTask"));
        Item result;

        //act
        for (Item item :tracker.getAll()) {
            result = this.tracker.findById(item.getId());
            assertThat(expected, is(result));
            }

    }

    /**
     * Method whenFindItemByNameThanReturnItem created for testing findByName method to find exist item from array by name.
     */

    @Test
    public void whenFindItemByNameThanReturnItem() {

        //assign
        Item expected = this.tracker.add(new Task("TaskName", "DescTask"));
        Item result;

        //act
        for (Item item :tracker.getAll()) {
            result = this.tracker.findByName(item.getName());
            assertThat(expected, is(result));
        }
    }

    /**
     * Method whenFindItemByDateThanReturnItem created for testing findByDate method to find exist item from array by date.
     */

    @Test
    public void whenFindItemByDateThanReturnItem() {

        //assign
        Item expected = this.tracker.add(new Item("TaskName", "DescTask"));
        Item result;

        //act
        for (Item item :tracker.getAll()) {
            result = this.tracker.findByDate(item.getCreate());
            assertThat(expected, is(result));
        }
    }

    /**
     * Method whenCommentItemThanSaveCommentToItem using for testing addComment method, that add comment to the item.
     */

    @Test
    public void whenCommentItemThanSaveCommentToItem() {

        //assign
        /**
         * Variable COMMENTS_CAP is comments array capacity.
         */

        final int COMMENTS_CAP = 5;

        /**
         * Instance variable comments is array of comments that hold all comments.
         */

        Comments[] c = new Comments[COMMENTS_CAP];
        String comment = "comment1";
        c[0] = new Comments(comment);

        //act
        for (Item item : tracker.getAll()) {

            this.tracker.add(new Item("TaskName", "DescTask"));

            if (item.getName().equals("TaskName")) {

                this.tracker.addComment(item, comment);
                assertThat(c, is(item.getComments()));

            }
        }
    }

    /**
     * Method whenDeleteItemThenReturnItemsWithoutItem created for testing  deleteItem method? that deleted exist item from array of items.
     * it's may remove the specific type of item: Task or Bug.
     */

    @Test
    public void whenDeleteItemThenReturnItemsWithoutItem() {


        for (Item item : tracker.getAll()) {
            //assign
            this.tracker.add(new Item("TaskName", "DescTask"));
            this.tracker.add(new Item("TaskName2", "DescTask2"));

            //act
            if (item.getName().equals("TaskName")) {
                this.tracker.deleteItem(item.getId());
                assertThat(new Item("TaskName2", "DescTask2"), is(item));
            }
        }
    }

    /**
     * Method whenGetAllItemThanReturnAllItem created for testing getAll method, that return all exist item from array of items.
     */

    @Test
    public void whenGetAllItemThanReturnAllItem() {

        //assign
        /**
         * Adding items.
         */

        Item item = new Item("FirstItem", "FirstDesc");
        Item task = new Task("FirstTask", "FirstTaskDesc");
        Item bug = new Bug("FirstBug", "FirstBugDesc");

        this.tracker.add(item);
        this.tracker.add(task);
        this.tracker.add(bug);

        //act
        assertThat(new Item[]{item, task, bug}, is(this.tracker.getAll()));

    }
}
