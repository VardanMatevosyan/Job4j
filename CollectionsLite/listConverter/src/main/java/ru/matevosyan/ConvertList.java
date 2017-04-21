package ru.matevosyan;

import java.util.ArrayList;
import java.util.List;

public class ConvertList{

    public List<Integer> toList (int[][] array) {
        List<Integer> list = new ArrayList<>();
        return list;
    }
    public int[][] toArray (List<Integer> list, int rows) {
        int column = list.size()/rows;
        int[][] array = new int[rows][column];

        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < column; i++) {
                for (Integer aList : list) {
                    array[j][i] = aList;
                }
             }
        }

        if (rows != column) {
            for (int j = 0; j < rows; j++) {
                for (int i = 0; i < column; i++) {
                    if (array[j][i] == 0) {
                        array[j][i] = 0;
                    }
                }
            }
        }
        return  array;
    }

}
