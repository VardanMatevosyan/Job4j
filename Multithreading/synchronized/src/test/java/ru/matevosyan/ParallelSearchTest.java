package ru.matevosyan;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


/**
 * ParallelSearchTst class for test file searching in the file system.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 03.10.2017
 */

public class ParallelSearchTest {
    @Test
    public void whenThan() throws IOException {
        List<String> listOfExtension = new ArrayList<String>();
        listOfExtension.add("txt");
        ParallelSearch parallelSearch = new ParallelSearch("C:\\progect", "Hello", listOfExtension);

        Queue<File> list = parallelSearch.result();
        List<String> findNames = new ArrayList<>();

        for (File strName : list) {
            findNames = parallelSearch.searchText(strName);
        }
        System.out.println("Paths to search");
        System.out.println("==============================================================================\n");
        for (File name : list) {
            System.out.printf("%s \n", name.getAbsolutePath());
        }
        System.out.println("\nAll file with extension you want");
        System.out.println("==============================================================================");
        for (String name : findNames) {
            System.out.printf("%s \n", name);
        }
    }
}