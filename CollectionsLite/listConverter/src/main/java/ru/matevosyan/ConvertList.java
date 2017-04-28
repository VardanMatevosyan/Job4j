package ru.matevosyan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ConvertList was created convert from array to list and backward.
 * Created on 28.04.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class ConvertList {

    /**
     * convert method convert list of an array to an array list.
     * @param list list with array inside.
     * @return converted array list.
     */

    public List<Integer> convert (List<int[]> list) {
        Iterator iterator = list.listIterator();
        ArrayList<Integer> arrayList = new ArrayList<>();
        int[] arrList;
        while (iterator.hasNext()) {
            arrList = (int[]) iterator.next();
            for (int anArrList : arrList) {
                arrayList.add(anArrList);
            }
        }
        return arrayList;
    }

    /**
     * toList method convert two-dimensional array to an array list.
     * @param array two-dimensional array.
     * @return converted array list.
     */

    public List<Integer> toList (int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] anArray : array) {
            for (int j = 0; j < array.length; j++) {
                list.add(anArray[j]);
            }
        }
        return list;
    }

    /**
     * Convert list to array with passing amount of rows.
     * @param list list that to be converted to array.
     * @param rows amount rows.
     * @return array.
     */

    public int[][] toArray (List<Integer> list, int rows) {

        int column = list.size() % rows == 0 ? list.size() / rows : list.size() / rows + 1;

        int[][] array = new int[rows][column];

        Iterator<Integer> iterator = list.iterator();

        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < column && iterator.hasNext(); i++) {
                array[j][i] = iterator.next();
            }

        }

        return  array;
    }

}
