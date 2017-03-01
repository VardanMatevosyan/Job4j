package ru.matevosyan;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created SortFile for testing sort big file and write it to a new file.
 * Created on 19.02.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class zipWithStructureTest {

    private static String pathSource;
    private static String pathDist;
    private static File sourceFile;
    private static File distFile;
    private static SortFile sortFile;
    private static RandomAccessFile out;
    private static String[] forSort;

    /**
     * execute all common variable before start testing class methods, that use anywhere in the class.
     * @throws IOException when path to a file is invalid.
     */

    @BeforeClass
    public static void executeSameVariable() throws IOException {

        pathSource = "C:\\project\\Vardan-Git-Repository\\OOP\\Tracker\\target\\Tracker-1.0-shaded.jar";
        sourceFile = new File(pathSource);

    }

    /**
     * testing files size.
     */

    @Test
    public void whenAddFileAndSortToAnotherFileThanCheckFileSize() {

        try {
            for (String aForSort : forSort) {
                out.write(aForSort.getBytes());
                String s = System.getProperty("line.separator");
                out.write(s.getBytes());
            }
            sortFile.sort(sourceFile, distFile);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        long l = sourceFile.length();

        assertThat(distFile.length(), is(l));

        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}