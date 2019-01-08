package ru.matevosyan;

import org.junit.Test;
import ru.matevosyan.CycleDetection.Node;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * CycleDetectionTest class.
 * Created on 26.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class CycleDetectionTest {

    /**
     * Test cycle linked list.
     * When last element is pointer to the first, than return true.
     */

    @Test
    public void whenCreateCycleListThanDetectItAsTrue() {

        //assign
        CycleDetection<Integer> cycleDetection = new CycleDetection<>();

        Node<Integer> first = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);

        // 1 -> 2 -> 3 -> 4 -> 1
        first.setNext(two);
        two.setNext(third);
        third.setNext(four);
        four.setNext(first);

        //act
        boolean hasCycle = cycleDetection.hasCycle(first);

        //assertion
        assertThat(hasCycle, is(true));
    }

    /**
     * Test cycle linked list.
     * When last element is pointer to the second, than return true.
     */

    @Test
    public void whenCreateCycleListAndCycleItemIsNotFirstThanDetectItAsTrue() {

        //assign
        CycleDetection<Integer> cycleDetection = new CycleDetection<>();

        Node<Integer> first = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);

        // 1 -> 2 -> 3 -> 4 -> 2
        first.setNext(two);
        two.setNext(third);
        third.setNext(four);
        four.setNext(two);

        //act
        boolean hasCycle = cycleDetection.hasCycle(first);

        //assign
        assertThat(hasCycle, is(true));
    }

    /**
     * Test cycle linked list.
     * When last element have not next pointer, than return false.
     */

    @Test
    public void whenCreateListWithoutCycleThanDetectItAsFalse() {

        //assign
        CycleDetection<Integer> cycleDetection = new CycleDetection<>();

        Node<Integer> first = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);

        // 1 -> 2 -> 3 -> 4
        first.setNext(two);
        two.setNext(third);
        third.setNext(four);
        //act
        boolean hasCycle = cycleDetection.hasCycle(first);

        //assign
        assertThat(hasCycle, is(false));
    }
}