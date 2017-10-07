package ru.matevosyan;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.*;

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
     * File System Traversal? find files with extesion and get result.
     * @return list of find files.
     */

    public List<String> result() {
        File currentFile;
        File rootFile = new File(this.root);
        if (rootFile.exists()) {
            File[] rootFiles = rootFile.listFiles();
            if (rootFiles != null) {
                Collections.addAll(queueOfFile, rootFiles);
            }
            while (!queueOfFile.isEmpty()) {
                currentFile = queueOfFile.remove();
                File[] files;

                if (currentFile.isDirectory()) {

                    if (check(currentFile)) {
                        files = currentFile.listFiles();
                        if (files != null) {
                            Collections.addAll(queueOfFile, files);
                        }
                    }
                } else {
                    this.searchText(exts, currentFile);
                }
            }

        }
        return listOfFindFileNames;
    }
//    ---------------------------------------------------------------------------

    /**
     * Search text in the files
     * @param exts extension.
     * @param currentFile is file where searching.
     */

    private void searchText(List<String> exts, File currentFile) {
        String textInTheFile;
        for (String anExtension : exts) {

            if ((currentFile.toString().endsWith("." + anExtension)
                    || currentFile.toString().endsWith("." + anExtension.toUpperCase()))
                    && currentFile.canRead()) {
                try (LineNumberReader lineNumberReader = new LineNumberReader(
                        new BufferedReader(new FileReader(currentFile)))) {

                    while ((textInTheFile = lineNumberReader.readLine()) != null) {
                        if (textInTheFile.contains(this.text)) {
                            listOfFindFileNames.add(currentFile.getAbsolutePath());
                        }
                    }
                } catch (IOException e) {
                    break;
                }
            }
        }
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
