package ru.matevosyan;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
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
    private static final ArrayList<Thread> THREADS = new ArrayList<>(5);

    /**
     * Start all THREADS and check output to console.
     * @throws IOException if problem with reading files or files access.
     */

    @Test
    public void whenStartAllThreadsWithFilesWalkTreeThanCheckTheOutput() throws IOException {
        List<String> listOfExtension = new ArrayList<String>();
        listOfExtension.add("txt");

        final int queueSize = 10;
        CustomSynchQueue<File> queueForSearchingToGet = new CustomSynchQueue<>(queueSize);

        /**
         * If ues NOTrecursive custom algorithm use FileOrganizer fileOrganizer =.
         *  = new FileOrganizer("C:/folderForSearchingFiles/", listOfExtension, queueForSearchingToGet);.
         */
        FileSystemWalker fileSystemWalker = new FileSystemWalker("C:/folderForSearchingFiles/",
                listOfExtension, queueForSearchingToGet);
        Queue<ParallelSearch> parallelSearchQueue = new ArrayDeque<>(5);

        THREADS.add(fileSystemWalker);
        fileSystemWalker.start();
        for (int i = 1; i <= 5; i++) {
            ParallelSearch parallelSearch = new ParallelSearch("Hello", queueForSearchingToGet);
            THREADS.add(parallelSearch);
            THREADS.get(i).start();

            parallelSearchQueue.add(parallelSearch);

        }
        System.out.println("All files with extension you want\n");
        for (Thread thread : THREADS) {
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }

        for (int i = 1; i <= 5; i++) {
            Queue<String> findNames = parallelSearchQueue.poll().getListOfFindFileNames();
            System.out.println();
            System.out.println("\nAll file with extension you want in the " + i + " Thread");
            for (String name : findNames) {
                System.out.printf("%s \n", name);
            }
            System.out.println("==============================================================================");
        }

    }
}