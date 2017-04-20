package ru.matevosyan;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class EfficiencyListTest {
    @Test
    public void whenAddThousendAndFiveToTreeSetThanReturnFive() {

        //assign
        final String LN = System.getProperty("line.separator");
        Set<String> treeSet = new TreeSet<>();

        EfficiencyList efficiencyList = new EfficiencyList();

        //act
        System.out.printf("TreeSet%s", LN);

        long timeAddTeeSet = efficiencyList.add(treeSet, "treeSet", 2_000_000);
        int expectedTreeSet = treeSet.size();
        long timeRemoveTreeSet = efficiencyList.delete(treeSet, 500);

        System.out.printf(String.format("Add time --- %s%sRemove time  --- %s%s", timeAddTeeSet, LN,
                timeRemoveTreeSet, LN));
//        int i = 0;
//        for (String str : treeSet) {
//
//            System.out.printf("%s. element --- %s%s", i++, str, LN);
//        }
//        System.out.printf("Amount --- %s%s%s", i, LN, LN);

        //assert
//        assertThat(i, is(999_500));
        assertThat(expectedTreeSet, is(2_000_000));
    }
    @Test
    public void whenAddThousendAndFiveToArrayListThanReturnFive() {

        //assign
        final String LN = System.getProperty("line.separator");
        List<String> arrayList = new ArrayList<>();

        EfficiencyList efficiencyList = new EfficiencyList();

        //act
        System.out.printf("ArrayList%s", LN);

        long timeAddArray = efficiencyList.add(arrayList, "Linked", 2_000_000);
        int expectedArrayList = arrayList.size();
        long timeRemoveArray = efficiencyList.delete(arrayList, 500);

        System.out.printf(String.format("Add time --- %s%sRemove time --- %s%s",
                timeAddArray, LN, timeRemoveArray, LN));
//        int i = 0;
//        for (String str : arrayList) {
//
//            System.out.printf("%s. element --- %s%s", i++, str, LN);
//        }
//        System.out.printf("Amount --- %s%s%s", i, LN, LN);

        //assert
//        assertThat(i, is(999_500));
        assertThat(expectedArrayList, is(2_000_000));
    }

    @Test
    public void whenAddThousendAndFiveToLinkedListThanReturnFive() {

        //assign
        final String LN = System.getProperty("line.separator");
        List<String> linkedList = new LinkedList<>();

        EfficiencyList efficiencyList = new EfficiencyList();

        //act
        System.out.printf("LinkedList%s", LN);

        long timeAddLinkedList = efficiencyList.add(linkedList, "Linked", 2_000_000);
        int expectedLinkedList = linkedList.size();
        long timeRemoveLinkedList = efficiencyList.delete(linkedList, 500);

        System.out.print(String.format("Add time --- %s%sRemove time --- %s%s", timeAddLinkedList, LN,
                timeRemoveLinkedList, LN));

//        int i = 0;
//        for (String str : linkedList) {
//
//            System.out.printf("%s. element --- %s%s", i++, str, LN);
//        }
//        System.out.printf("Amount --- %s%s%s", i, LN, LN);

        //assert
//        assertThat(i, is(999_500));
        assertThat(expectedLinkedList, is(2_000_000));
    }



}