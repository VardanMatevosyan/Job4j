package ru.matevosyan;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created SortFile for testing sort big file and write it to a new file.
 * Created on 19.02.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class SortFileTest {

    private static String pathSource;
    private static String pathDist;
    private static File sourceFile;
    private static File distFile;
    private static SortFile sortFile;

    /**
     * execute all common variable before start testing class methods, that use anywhere in the class.
     * @throws IOException when path to a file is invalid.
     */

    @BeforeClass
    public static void executeSameVariable() throws IOException {

        pathSource = "C:\\Users\\Admin\\Desktop\\Abbat.txt";
        pathDist = "C:\\Users\\Admin\\Desktop\\AbbatResults.txt";

        sourceFile = new File(pathSource);
        distFile = new File(pathDist);
        sortFile = new SortFile();
    }

    /**
     * testing files size.
     */

    @Test
    public void whenAddFileAndSortToAnotherFileThanCheckFileSize() {

        try {
            sortFile.sort(sourceFile, distFile);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        long l = sourceFile.length();

        assertThat(distFile.length(), is(l));
    }

    /**
     * testing {@link SortFile#sort(File, File)} that sorting the file.
     */

    @Test
    public void whenAddFileAndSortToAnotherFileThanCheckIfItSort() {

        try {

            File sourceFile = new File(pathSource);
            File distFile = new File(pathDist);
            sortFile.sort(sourceFile, distFile);

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        String[] expectedArray = {
                "1",
                "22",
                "22",
                "333",
                "4444",
                "0000",
                "55555",
                "55555",
                "666666",
                "666666",
                "666666",
                "666666",
                "8888888",
                "77777777",
                "999999999",
                "999999999"
        };

       String[] sActualArray = sortFile.getStringsThirdSortedToRead();

        for (int i = 0; i < expectedArray.length; i++) {
            assertThat(expectedArray[i], is(sActualArray[i]));
        }
    }

}