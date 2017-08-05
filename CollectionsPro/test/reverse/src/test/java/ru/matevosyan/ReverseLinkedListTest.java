package ru.matevosyan;

import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Was created to test ReverseLinkedListTest class.
 * Created on 04.08.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */


public class ReverseLinkedListTest {

    /**
     * use to test {@link ReverseLinkedList#reverseList()} method.
     */

    @Test
    public void whenCreateListAndReverseItThanCheckTheReverseValueWithExpectedValue() {
        //assign
        ReverseLinkedList<Integer> reverseLinkedList = new ReverseLinkedList<>();
        List<Integer> expected = new LinkedList<>();

        //act
        reverseLinkedList.add(1);
        reverseLinkedList.add(3);
        reverseLinkedList.add(2);
        reverseLinkedList.add(3);
        reverseLinkedList.add(0);

        expected.add(0);
        expected.add(3);
        expected.add(2);
        expected.add(3);
        expected.add(1);

        reverseLinkedList.reverseList();

        Iterator itrExp = expected.iterator();
        Iterator itrReverseLinkedList = reverseLinkedList.iterator();

        //assertion
        while(itrReverseLinkedList.hasNext() && itrExp.hasNext()) {
        assertThat(itrReverseLinkedList.next(), is(itrExp.next()));
        }

    }
}