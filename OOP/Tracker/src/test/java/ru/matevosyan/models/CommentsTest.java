package ru.matevosyan.models;

import org.junit.Test;
import ru.matevosyan.start.Input;
import ru.matevosyan.start.StartUI;
import ru.matevosyan.start.StubInput;
import ru.matevosyan.start.Tracker;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class CommentsTest created for testing Comments class.
 * Created on 30.11.2016.
 * @author Matevosyan Vardan
 * @since 1.0
 * @version 1.0
 */

public class CommentsTest {

    /**
     * Method whenAddCommentThanGetCommentName created for testing getCommentName method, that give us the Comments name.
     * it's may the specific type of item, for example: new Task or new Bug.
     */

    @Test
    public void whenAddCommentThanGetCommentName() {
        String commentName = "comment";
        Comments comment = new Comments(commentName);
        String actual = comment.getCommentName();
        assertThat(actual, is(commentName));
    }

    /**
     * Method whenSetCommentThanFormatOutput created for testing getCommentName method, that output all with comments.
     * it's may the specific type of item, for example: new Task or new Bug.
     */

    @Test
    public void whenSetCommentThanFormatOutput() {

        Tracker tracker = new Tracker();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Item item = new Task("task 3", "task desc 3");
        item.addComment("comment");
        String[] answer = {
                "8",
                "n"
        };

        Input stub = new StubInput(answer);
        new StartUI(stub, tracker).init();
        Item[] items = tracker.getAll();
        String s = System.getProperty("line.separator");

        assertThat(out.toString(), is("___M_E_N_U___ " + s + "1. Add Item " + s + "2. Edit Item " + s
                + "3. Remove Item " + s + "4. Add comment" + s + "5. Find by id " + s + "6. Find by name " + s
                + "7. Find by date " + s + "8. Get all items" + s + "9. Exit" + s + s +  Arrays.toString(items) + s));
    }

}
