package ru.matevosyan;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created SortFile for testing sort big file and write it to a new file.
 * Created on 19.02.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class ZipWithStructureTest {

    private static String java;
    private static String classFormat;
    String[] s = new String[]{java, classFormat};
    private final static String zip_path_name = "D:\\Tracker-1.0-shaded.jar";
    private final static String FOLDER_PATH_NAME = "D:\\Tracker-1.0-shaded";
    private static String outZip;

    /**
     * execute all common variable before start testing class methods, that use anywhere in the class.
     * @throws IOException when path to a file is invalid.
     */

    @BeforeClass
    public static void executeSameVariable() throws IOException {

        outZip = "D:\\dirJar.zip";
        java = "java";
        classFormat = "class";

    }

    /**
     * testing files size.
     */

    @Test
    public void whenAddFileAndSortToAnotherFileThanCheckFileSize() {
        ZipWithStructure zip = new ZipWithStructure(zip_path_name);

        try {
            zip.unzipping(zip_path_name, FOLDER_PATH_NAME);
            zip.genListOfFiles(new File(zip_path_name), s);
            zip.zipping(outZip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}