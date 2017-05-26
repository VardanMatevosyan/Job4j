package ru.matevosyan;

import org.junit.Test;
import ru.matevosyan.CycleDetection.Node;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Пользователь on 26.05.2017.
 */
public class CycleDetectionTest {

    @Test
    public void WhenCreateCycleListThanDetectIt() {

        CycleDetection<Integer> cycleDetection = new CycleDetection<>();

        Node<Integer> first = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;

        boolean hasCycle = cycleDetection.hasCycle(first);

        assertThat(hasCycle, is(true));
    }
}