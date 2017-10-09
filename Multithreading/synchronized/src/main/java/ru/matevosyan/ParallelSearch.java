package ru.matevosyan;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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

public class ParallelSearch {

    private String root;
    private final String text;
    private final List<String> exts;
    private final List<String> listOfFindFileNames;
    private Queue<File> queueOfFile;


    /**
     * Constructor for ParallelSearch.
     * @param root - путь до папки откуда надо осуществлять поиск.
     * @param text - заданных текст.
     * @param exts - расширения файлов в которых нужно делать поиск.
     */

    public ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
        listOfFindFileNames = new ArrayList<>();
        queueOfFile = new PriorityQueue<>();
    }
//----------------------------------------------------------------------

    /**
     * File System Traversal to find files with extesion and get result.
     * @return list of find files.
     */

    public Queue<File> result() {
        Queue<File> queueOfAllFileWithExt = new PriorityQueue<>();
        File currentFile;
        File rootFile = new File(this.root);
        if (rootFile.exists()) {
            File[] rootFiles = rootFile.listFiles();
            for (String anExtension : exts) {
                if (rootFiles != null) {
                for (File current : rootFiles) {
                    if (((current.toString().endsWith("." + anExtension)
                            || current.toString().endsWith("." + anExtension.toUpperCase()))
                            && current.canRead()) || current.isDirectory()) {
                        queueOfFile.add(current);
                        }
                    }
                }
            }
            while (!queueOfFile.isEmpty()) {
                currentFile = queueOfFile.remove();
                File[] files;

                if (currentFile.isDirectory()) {

                    if (check(currentFile)) {
                        files = currentFile.listFiles();
                        for (String anExtension : exts) {
                            if (files != null) {
                                for (File current : files) {
                                    if ((current.toString().endsWith("." + anExtension)
                                            || current.toString().endsWith("." + anExtension.toUpperCase()))
                                            && current.canRead() || current.isDirectory()) {
                                        queueOfFile.add(current);
                                        queueOfAllFileWithExt.add(current);
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
        return queueOfAllFileWithExt;
    }
//    ---------------------------------------------------------------------------

    /**
     * Search text in the files.
     * @param currentFile is file where searching.
     */

    public List<String> searchText(File currentFile) {
        String textInTheFile;
            try (LineNumberReader lineNumberReader = new LineNumberReader(
                    new BufferedReader(new FileReader(currentFile)))) {
                while ((textInTheFile = lineNumberReader.readLine()) != null) {
                    if (textInTheFile.contains(this.text)) {
                        listOfFindFileNames.add(currentFile.getAbsolutePath());
                    }
                }
            } catch (IOException e) {
                e.getMessage();
            }
            return listOfFindFileNames;
    }

    //--------------------------------------------------------------------------------

    /**
     * Check if file or directory is empty or isReadable or doesn't exist.
     * @param files file to check.
     * @return true if file can read or directory is empty else return false.
     */

    private boolean check(File files) {
        boolean dirIsEmpty = false;
        String line;
            if (files.canRead()) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(
                        new FileInputStream(files)))) {
                    try {

                        while ((line = br.readLine()) != null) {
                            if (line.equals("")) {
                                dirIsEmpty = true;
                            }
                        }
                    } catch (IOException e) {
                        dirIsEmpty = true;
                    }
                } catch (IOException e) {
                    dirIsEmpty = true;
                }

            } else {
                dirIsEmpty = true;
            }
            return dirIsEmpty;
    }
}
