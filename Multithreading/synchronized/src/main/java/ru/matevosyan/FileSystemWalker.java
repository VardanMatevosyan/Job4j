package ru.matevosyan;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.FileVisitResult;
import java.util.List;

/**
 * FileSystemWalker class for quick file searching in the file system.
 * And organize queue of files for parallel text searching threads.
 *
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 03.10.2017
 */
public class FileSystemWalker extends Thread {

    private final String root;
    private final List<String> extension;
    private static final int QUEUE_SIZE = 10;
    private volatile CustomSynchQueue<File> queueOfFile = new CustomSynchQueue<>(QUEUE_SIZE);
    protected static final File EMPTY_OBJECT = new File("");

    /**
     * Constructor.
     * @param root directory from which start searching files.
     * @param extension to find exactly files with passing extension.
     * @param queueOfFile common to exchange files.
     * from thread which load files and others who take it and search.
     */

    public FileSystemWalker(String root, List<String> extension, CustomSynchQueue<File> queueOfFile) {
        this.root = root;
        this.extension = extension;
        this.queueOfFile = queueOfFile;
    }


    /**
     * run the upload files method to get all files needed to.
     * and also put the empty object in the last position to check.
     * if it was the last files in the queue to take from.
     */

    @Override
    public void run() {
        MyFileVisitor myFileVisitor = new MyFileVisitor();
        Path path = Paths.get(this.root);
        try {
            Files.walkFileTree(path, myFileVisitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        queueOfFile.addToQueue(EMPTY_OBJECT);
    }

    /**
     * My owm simple file visitor MyFileVisitor class to visit all directory's.
     */

    private class MyFileVisitor extends SimpleFileVisitor<Path> {
        /**
         * Invoke parent constructor.
         */

        protected MyFileVisitor() {
            super();
        }

        /**
         * Add to the queue all files with spacial extension invoke override method visitFile.
         * @param file file object.
         * @param attrs basic file attributes.
         * @return FileVisitorResult in our case it is CONTINUE for continue searching.
         * @throws IOException if something with file goes wrong.
         */

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (attrs.isRegularFile()) {
                File fileFromPath = file.toFile();
                for (String ext : extension) {
                    if (fileFromPath.toString().endsWith("." + ext)
                            || fileFromPath.toString().endsWith("." + ext.toUpperCase())) {
                        queueOfFile.addToQueue(fileFromPath);
                    }
                }
            }
            return FileVisitResult.CONTINUE;
        }

    }
}

