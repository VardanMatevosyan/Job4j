package ru.matevosyan;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * For testing Queue class.
 * Created on 25.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class QueueTest {

    /**
     * Created whenAddElementToTheQueueThanCheckAddedElements() to test adding values to the queue.
     */

    @Test
    public void whenAddElementToTheQueueThanCheckAddedElements() {
        Queue<Integer> integerQueue = new Queue<>();

        int first = integerQueue.push(4);
        int last = integerQueue.push(9);

        assertThat(first, is(4));
        assertThat(last, is(9));
    }

    /**
     * Create whenAddElementsAndPeekOneThanCheckIt() to test adding and peeking value from the queue.
     */

    @Test
    public void whenAddElementsAndPeekOneThanCheckIt() {
        Queue<Integer> integerQueue = new Queue<>();

        int first = integerQueue.push(4);
        int middle = integerQueue.push(3);
        int last = integerQueue.push(9);

        int peekFirst = integerQueue.peek();

        assertThat(peekFirst, is(4));
    }

    /**
     * Create whenAddElementsAndPeekOneAfterPopOneOfTheElementsThanCheckPeekElement() to test {@link Queue#pop()}.
     */

    @Test
    public void whenAddElementsAndPeekOneAfterPopOneOfTheElementsThanCheckPeekElement() {
        Queue<Integer> integerQueue = new Queue<>();

        int first = integerQueue.push(4);
        int middle = integerQueue.push(3);
        int last = integerQueue.push(9);

        int peekFirst = integerQueue.peek();

        int popElement = integerQueue.pop();

        int peekElement = integerQueue.peek();

        assertThat(peekFirst, is(first));
        assertThat(popElement, is(first));
        assertThat(peekElement, is(middle));
    }
}