package ru.matevosyan;

import org.junit.Test;
import ru.matevosyan.CycleDetection.Node;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * CycleDetectionTest class.
 * Created on 26.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class CycleDetectionTest {

    /**
     * Create WhenCreateCycleListThanDetectItAsTrue() to test cycle linked list.
     * When last element is pointer to the first, than return true.
     */

    @Test
    public void WhenCreateCycleListThanDetectItAsTrue() {

        //assign
        CycleDetection<Integer> cycleDetection = new CycleDetection<>();

        Node<Integer> first = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);

        // 1 -> 2 -> 3 -> 4 -> 1
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;

        //act
        boolean hasCycle = cycleDetection.hasCycle(first);

        //assertion
        assertThat(hasCycle, is(true));
    }

    /**
     * Create WhenCreateCycleListAndCycleItemIsNotFirstThanDetectItAsTrue() to test cycle linked list.
     * When last element is pointer to the second, than return true.
     */

    @Test
    public void WhenCreateCycleListAndCycleItemIsNotFirstThanDetectItAsTrue() {

        //assign
        CycleDetection<Integer> cycleDetection = new CycleDetection<>();

        Node<Integer> first = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);

        // 1 -> 2 -> 3 -> 4 -> 2
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = two;

        //act
        boolean hasCycle = cycleDetection.hasCycle(first);

        //assign
        assertThat(hasCycle, is(true));
    }

    /**
     * Create WhenCreateListWithoutCycleThanDetectItAsFalse() to test cycle linked list.
     * When last element have not next pointer, than return false.
     */

    @Test
    public void WhenCreateListWithoutCycleThanDetectItAsFalse() {

        //assign
        CycleDetection<Integer> cycleDetection = new CycleDetection<>();

        Node<Integer> first = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);

        // 1 -> 2 -> 3 -> 4
        first.next = two;
        two.next = third;
        third.next = four;


        //act
        boolean hasCycle = cycleDetection.hasCycle(first);

        //assign
        assertThat(hasCycle, is(false));
    }
}