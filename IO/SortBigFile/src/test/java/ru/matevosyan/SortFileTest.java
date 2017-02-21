package ru.matevosyan;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created SortFile for testing sort big file and write it to a new file.
 * Created on 19.02.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */
public class SortFileTest {
    @Test
    public void whenAddFileThanSortToAnotherFile() {
        String pathSource = "C:\\Users\\Admin\\Desktop\\Abbat.txt";
        String pathDist = "C:\\Users\\Admin\\Desktop\\AbbatResults.txt";

        File sourceFile = new File(pathSource);
        File distFile = new File(pathDist);

        SortFile sortFile = new SortFile();

        try {
            sortFile.sort(sourceFile, distFile);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        long l = sourceFile.length();

        assertThat(distFile.length(), is(l));
    }


}