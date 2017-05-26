package ru.matevosyan;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * For testing Stack class.
 * Created on 25.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */
public class StackTest {
    @Test
    public void whenAddToTheStackAndPeekThanCheckAddedAndPeekedElement() {
        Stack<Integer> stack = new Stack<>();

        int a = stack.push(3);
        int b = stack.push(2);

        int peek = stack.peek();

        assertThat(a, is(3));
        assertThat(peek, is(b));
    }

    @Test
    public void whenAddToTheStackAndPopThanCheckAddedAndPopElement() {
        Stack<Integer> stack = new Stack<>();

        int a = stack.push(3);
        int b = stack.push(2);

        int peek = stack.peek();

        int pop = stack.pop();

        int peekAfterPop = stack.peek();
        assertThat(a, is(3));
        assertThat(peek, is(b));
        assertThat(peekAfterPop, is(3));
        assertThat(pop, is(2));

    }

}