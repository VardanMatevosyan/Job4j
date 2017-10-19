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
    public void whenThan() throws IOException, InterruptedException {
        List<String> listOfExtension = new ArrayList<String>();
        listOfExtension.add("txt");
//        ParallelSearch parallelSearch = new ParallelSearch("C:\\progect", "Hello", listOfExtension);
        final int queueSize = 10;
//        CustomSynchQueue<File> queueForSearching = new CustomSynchQueue<>(queueSize);
        CustomSynchQueue<File> queueForSearchingToGet = new CustomSynchQueue<>(queueSize);
        FileOrganizer fileOrganizer = new FileOrganizer("C:/folderForSearchingFiles/", listOfExtension, queueForSearchingToGet);
        ParallelSearch parallelSearch = new ParallelSearch("Hello", queueForSearchingToGet);
        ParallelSearch parallelSearch2 = new ParallelSearch("Hello", queueForSearchingToGet);
        ParallelSearch parallelSearch3 = new ParallelSearch("Hello", queueForSearchingToGet);
        ParallelSearch parallelSearch4 = new ParallelSearch("Hello", queueForSearchingToGet);
//        Queue<File> list = parallelSearch.result();

        fileOrganizer.start();

        parallelSearch.start();
        parallelSearch2.start();
        parallelSearch3.start();
        parallelSearch4.start();

        try {
            fileOrganizer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            parallelSearch.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            parallelSearch2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            parallelSearch3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            parallelSearch4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Queue<String> findNames = parallelSearch.getListOfFindFileNames();
        Queue<String> findNames2 = parallelSearch2.getListOfFindFileNames();
        Queue<String> findNames3 = parallelSearch3.getListOfFindFileNames();
        Queue<String> findNames4 = parallelSearch3.getListOfFindFileNames();

        System.out.println();

        System.out.println("\nAll file with extension you want in the 1 Thread");
        System.out.println("==============================================================================");
        for (String name : findNames) {
            System.out.printf("%s \n", name);
        }
        System.out.println();
        System.out.println("\nAll file with extension you want in the 2 Thread");
        System.out.println("==============================================================================");
        for (String name : findNames2) {
            System.out.printf("%s \n", name);
        }

        System.out.println();
        System.out.println("\nAll file with extension you want in the 3 Thread");
        System.out.println("==============================================================================");
        for (String name : findNames3) {
            System.out.printf("%s \n", name);
        }
        System.out.println();
        System.out.println("\nAll file with extension you want in the 4 Thread");
        System.out.println("==============================================================================");
        for (String name : findNames4) {
            System.out.printf("%s \n", name);
        }

    }
}