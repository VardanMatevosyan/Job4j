package ru.matevosyan;

import java.io.*;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * FileOrganizer class for quick file searching in the file system.
 * And organize queue of files for parallel text searching threads.
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
public class FileOrganizer extends Thread {

    private String root;
    private final List<String> extension;
    private Queue<File> queueOfFile = new PriorityQueue<>(QUEUE_SIZE);
    private volatile CustomSynchQueue<File> queueOfAllFileWithExt;
    private static final int QUEUE_SIZE = 10;
//    private final Object lock = new Object();

    private boolean isReady = true;
    private boolean isWait = false;
    private int queueSize = 5;
    private int countingForQueueSize = 0;
    Thread threadToGetQueue;
    final ReentrantLock lock = new ReentrantLock();
    private final Condition isEnought = lock.newCondition();
    public static final File EMPTY_OBJECT = new File("");

    public FileOrganizer(String root, List<String> extension, CustomSynchQueue<File> queueOfFile) {
        this.root = root;
        this.extension = extension;
        this.queueOfAllFileWithExt = queueOfFile;
    }



    @Override
    public void run() {
        try {
            this.uploadFiles();
            this.queueOfAllFileWithExt.addToQueue(EMPTY_OBJECT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    /**
     * File System Traversal to find files with extesion and get result.
     * @return list of find files.
     */

    public CustomSynchQueue<File> uploadFiles() {
//        this.queueOfAllFileWithExt = new CustomSynchQueue<>(QUEUE_SIZE);
        File currentFile;
        File rootFile = new File(this.root);
        if (rootFile.exists()) {
            File[] rootFiles = rootFile.listFiles();
            for (String anExtension : this.extension) {
                if (rootFiles != null) {
                    for (File current : rootFiles) {
                        if (((current.toString().endsWith("." + anExtension)
                                || current.toString().endsWith("." + anExtension.toUpperCase()))
                                && current.canRead()) || current.isDirectory()) {
                            this.queueOfFile.add(current);
                        }
                    }
                }
            }
            while (!this.queueOfFile.isEmpty()) {
                currentFile = this.queueOfFile.remove();
                File[] files;

                if (currentFile.isDirectory()) {

                    if (check(currentFile)) {
                        files = currentFile.listFiles();
                        for (String anExtension : this.extension) {
                            if (files != null) {
                                for (File current : files) {
                                    if ((current.toString().endsWith("." + anExtension)
                                            || current.toString().endsWith("." + anExtension.toUpperCase()))
                                            && current.canRead() || current.isDirectory()) {
                                        this.queueOfFile.add(current);
                                        if (!current.isDirectory()) {
                                            try {
                                                this.queueOfAllFileWithExt.addToQueue(current);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                            countingForQueueSize++;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (!this.queueOfAllFileWithExt.contains(currentFile)) {
                        try {
                            this.queueOfAllFileWithExt.addToQueue(currentFile);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
        return this.queueOfAllFileWithExt;
    }

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
