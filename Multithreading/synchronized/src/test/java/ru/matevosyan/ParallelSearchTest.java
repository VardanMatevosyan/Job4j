package ru.matevosyan;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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
        ParallelSearch parallelSearch = new ParallelSearch("C:\\", "Hello", listOfExtension);
        System.out.println(parallelSearch.result());
    }
}