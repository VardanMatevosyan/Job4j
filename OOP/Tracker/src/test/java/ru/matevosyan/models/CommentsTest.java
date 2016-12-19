package ru.matevosyan.models;

import org.junit.Test;

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

}
