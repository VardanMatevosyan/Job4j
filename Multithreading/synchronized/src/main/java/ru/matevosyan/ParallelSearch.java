package ru.matevosyan;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * ParallelSearch class for quick file searching in the file system.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 03.10.2017
 */

public class ParallelSearch extends Thread {

    private final String text;
    private volatile Queue<String> listOfFindFileNames;
    private CustomSynchQueue<File> customSynchQueue;

    /**
     * Getter for return list of find file names.
     * @return list of files.
     */

    public Queue<String> getListOfFindFileNames() {
        return listOfFindFileNames;
    }

    /**
     * Constructor for ParallelSearch.
     * root - directory from which start searching files.
     * @param text - word to find out.
     * @param customSynchQueue - word to find out.
     * text - to find exactly files with passing extension.
     */

    public ParallelSearch(String text, CustomSynchQueue<File> customSynchQueue) {
        this.text = text;
        listOfFindFileNames = new PriorityQueue<>();
        this.customSynchQueue = customSynchQueue;
    }

    /**
     * run the searchText method while taking files from the queue is not empty.
     * if it take empty object wich is end of the queue than put it bake.
     * to tell other threads that it is the end of queue for stop processing.
     */

    @Override
    public void run() {
        boolean isDone = false;
        while (!isDone) {
            File fileForSearching;

            fileForSearching = customSynchQueue.removeFromQueue();

            /**
             * can be used wis NOT RECURSIVE search with FileOrganizer class.
             * with if statement -> fileForSearching == FileOrganizer.EMPTY_OBJECT.
             */

            if (fileForSearching == FileSystemWalker.EMPTY_OBJECT) {
                customSynchQueue.addToQueue(fileForSearching);
                isDone = true;
            } else {
                this.searchText(fileForSearching);
            }
        }
    }

    /**
     * Search text in the files.
     * And after seatchText method add all files to listOfFindFileNames queue.
     * @param currentFile is file where searching.
     * @return Queue of find file names.
     */

    public Queue<String> searchText(File currentFile) {
            String textInTheFile;
            try (LineNumberReader lineNumberReader = new LineNumberReader(
                    new BufferedReader(new FileReader(currentFile)))) {
                while ((textInTheFile = lineNumberReader.readLine()) != null) {
                    if (textInTheFile.contains(this.text)) {
                        listOfFindFileNames.add(currentFile.getAbsolutePath());
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(currentFile.getAbsolutePath());
                    }
                }
            } catch (IOException e) {
                e.getMessage();
            }

            return listOfFindFileNames;
    }

}



