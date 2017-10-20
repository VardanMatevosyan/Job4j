package ru.matevosyan;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * ParallelSearch class for quick file searching in the file system.
 *
 *
 * Логика приложения.
 *
 * 1. Запустить код.
 * 2. Внутри запустить несколько потоков. Объяснить для чего нужно делать потоки.
 * 3. Долждатся завершения поиска.
 * 4. Вывести на консоль результат.
 *
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 03.10.2017
 */

public class ParallelSearch extends Thread {
//    private static final File EMPTY_OBJECT = new File("");
    private final String text;
    private volatile Queue<String> listOfFindFileNames;
    private CustomSynchQueue<File> customSynchQueue;

    public Queue<String> getListOfFindFileNames() {
        return listOfFindFileNames;
    }

    /**
     * Constructor for ParallelSearch.
     * root - путь до папки откуда надо осуществлять поиск.
     * @param text - заданных текст.
     * exts - расширения файлов в которых нужно делать поиск.
     */

    public ParallelSearch(String text, CustomSynchQueue<File> customSynchQueue) {
        this.text = text;
        listOfFindFileNames = new PriorityQueue<>();
        this.customSynchQueue = customSynchQueue;
    }

    @Override
    public void run() {
        boolean isDone = false;
        while (!isDone) {
            File fileForSearching;

                fileForSearching =  customSynchQueue.removeFromQueue();
                if (fileForSearching == FileOrganizer.EMPTY_OBJECT) {
                    customSynchQueue.addToQueue(fileForSearching);
                    isDone = true;
                } else {
                    this.searchText(fileForSearching);
                }
        }
    }

    /**
     * Search text in the files.
     * @param currentFile is file where searching.
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
