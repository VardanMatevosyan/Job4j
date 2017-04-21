package ru.matevosyan;

import java.util.ArrayList;
import java.util.List;

public class ConvertList {

    public List<Integer> toList (int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] anArray : array) {
            for (int j = 0; j < array.length; j++) {
                list.add(anArray[j]);
            }
        }
        return list;
    }

    public int[][] toArray (List<Integer> list, int rows) {

        int column = 0;
        if (list.size() % 2 == 0) {
            column = list.size()/ rows;
        } else {
            int c = 1;
            column = ((list.size() - c) / rows) + 1;

            while (column %2 != 0) {
                c--;
                column = (list.size() - c) / rows;
            }
            if (!(column %2 == 0 && list.size() % 2 != 0 && rows %2 == 0)) {
                column = column + 1;
            }

        }

        int[][] array = new int[rows][column];

        int g = 0;
        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < column; i++) {
                if (g < list.size()) {
                    array[j][i] = list.get(g++);
                }
            }
             }

        if (list.size() % rows == 0) {
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
