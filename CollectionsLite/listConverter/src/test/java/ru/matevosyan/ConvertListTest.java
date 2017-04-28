package ru.matevosyan;

import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * ConvertListTest was created to test ConvertList class.
 * Created on 28.04.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class ConvertListTest {

    /**
     * whenPassArrayThanCheckList was created for testing {@link ConvertList#toList(int[][])}.
     */

    @Test
    public void whenPassArrayThanCheckList() {

        //assign
       int[][] arrayToList = new int[][]{{1, 2 ,3}, {4, 5, 6}, {7, 8, 9}};
       List<Integer> expectedArrayList = new ArrayList<>();
       List<Integer> actualArrayList = new ArrayList<>();

       ConvertList convertList = new ConvertList();

        //act
        actualArrayList = convertList.toList(arrayToList);

        for (int i = 1; i < actualArrayList.size() + 1; i++) {
            expectedArrayList.add(i);
        }
        //assert

        for (int i = 0; i < actualArrayList.size(); i++) {
            assertThat(actualArrayList.toString(), is(expectedArrayList.toString()));
        }
    }

    /**
     * whenPassSquareListThanCheckArray was created for testing {@link ConvertList#toArray(List, int)}.
     * with passing odd number of rows.
     */

    @Test
    public void whenPassSquareListThanCheckArray() {

        //assign
        int[][] expectedArray = new int[][]{{1, 2 ,3}, {4, 5, 6}, {7, 0, 0}};
        int[][] actualArray;
        List<Integer> arrayList = new ArrayList<>();

        ConvertList convertList = new ConvertList();

        //act
        //array values is 1,2,3,4,5,6,7
        for (int i = 1; i < 8; i++) {
            arrayList.add(i);
        }

        actualArray = convertList.toArray(arrayList, 3);
        //assert

        for (int i = 0; i < expectedArray.length; i++) {
            for (int f = 0; f < expectedArray.length; f++) {
                assertThat(actualArray[i][f], is(expectedArray[i][f]));

            }
        }
    }

    /**
     * whenPassRectangleListThanCheckArray was created for testing {@link ConvertList#toArray(List, int)}.
     * with passing even number of rows.
     */

    @Test
    public void whenPassRectangleListThanCheckArray() {

        //assign
        int[][] expectedArray = new int[][]{{1, 2 ,3, 4}, {5, 6, 7, 0}};
        int[][] actualArray;
        List<Integer> arrayList = new ArrayList<>();

        ConvertList convertList = new ConvertList();

        //act
        //array values is 1,2,3,4,5,6,7
        for (int i = 1; i < 8; i++) {
            arrayList.add(i);
        }

        actualArray = convertList.toArray(arrayList, 2);
        //assert

        for (int i = 0; i < expectedArray.length - 2; i++) {
            for (int f = 0; f < expectedArray.length; f++) {
                assertThat(actualArray[i][f], is(expectedArray[i][f]));
            }
        }
    }

    /**
     * whenPassArraysInListThanCheckArrayList was created to test {@link ConvertList#convert(List)}.
     * that convert list of an arrays of different dimensions to one list of Integer.
     */

    @Test
    public void whenPassArraysInListThanCheckArrayList() {

        //assign
        int[] firstArray = new int[]{1, 2};
        int[] secondArray = new int[]{3, 4, 5};
        List<Integer> expectedArrayList = new ArrayList<>();
        List<Integer> actualIntList = new ArrayList<>();
        List<int[]> actualArray = new ArrayList<>();
        ConvertList convertList = new ConvertList();

        //act
        for (int i = 1; i < 6; i++) {
            expectedArrayList.add(i);
        }
        actualArray.add(firstArray);
        actualArray.add(secondArray);

        actualIntList = convertList.convert(actualArray);

        //assert
        assertThat(actualIntList, is(expectedArrayList));
    }

}