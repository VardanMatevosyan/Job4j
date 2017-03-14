package ru.matevosyan;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Use {@link ZipWithStructureTest} for testing sort big file and write it to a new file.
 * Created on 19.02.2017.
 * @author Matevosyan Vardan
 */

public class ZipWithStructureTest {

    private static String java;
    private static String classFormat;
    private String[] s = new String[]{java, classFormat};
    private final static String zip_path_name = "D:\\Tracker-1.0-shaded.jar";
    private final static String FOLDER_PATH_NAME = "D:\\Tracker-1.0-shaded";
    private static String outZip;

    /**
     * assign all common variable before start testing class methods, that use anywhere in the class.
     * @throws IOException when path to a file is invalid.
     */

    @BeforeClass
    public static void executeSameVariable() throws IOException {

        outZip = "D:\\TrackerOUT.zip";
        java = "java";
        classFormat = "class";

    }

    /**
     * Check unzipping directory after invoking {@link ZipWithStructure#unzipping(String)} method.
     */

    @Test
    public void whenUnzipThanCheckUnzippedDirectory() {
        ZipWithStructure zip = new ZipWithStructure(FOLDER_PATH_NAME, s);

        try {
            zip.unzipping(zip_path_name);

            File file = new File(zip_path_name.substring(0, zip_path_name.lastIndexOf(".")));
            boolean checkExistDirectory = false;

            if (file.isDirectory() && file.exists()) {
                checkExistDirectory = true;
            }

            assertThat(checkExistDirectory, is(true));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Check creation zip file in file system.
     */

    @Test
    public void whenZipThanCheckZippedFileExisting() {
        ZipWithStructure zip = new ZipWithStructure(FOLDER_PATH_NAME, s);

        try {
            zip.unzipping(zip_path_name);
            zip.zipping(outZip);
            File file = new File(outZip);
            boolean checkExistDirectory = false;

            if (file.isFile() && file.exists() && file.getName().endsWith(".zip")) {
                checkExistDirectory = true;
            }

            assertThat(checkExistDirectory, is(true));

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    /**
     * Check, that all files ends with key {@link ZipWithStructure#S}.
     */

    @Test
    public void whenZipedThanCheckAllFilesWithKey() {
        ZipWithStructure zip = new ZipWithStructure(FOLDER_PATH_NAME, s);

        try {
            zip.unzipping(zip_path_name);
            zip.zipping(outZip);
            List<String> list = zip.getListOfFile();
            boolean checkExistDirectory = false;

            for (String filesPath : list) {
                for (String value : s) {
                    checkExistDirectory = false;
                    if (filesPath.endsWith(value)) {
                        checkExistDirectory = true;
                    }
                }
            }
            assertThat(checkExistDirectory, is(true));

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    /**
     * Check all files after working with {@link ZipWithStructure#zipping(String)} method in zip files is ok.
     */

    @Test
    public void whenZipedThanCheckAllFilesInZipFile() {
        ZipWithStructure zip = new ZipWithStructure(FOLDER_PATH_NAME, s);

        try {
            zip.unzipping(zip_path_name);
            zip.zipping(outZip);

            List<String> arrayListResult = new ArrayList<>();
            List<String> arrayList = zip.getListOfFile();

            try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(outZip))) {
                ZipEntry entry = zipInputStream.getNextEntry();

                while (entry != null) {
                    String filePathName = entry.getName();
                    String folder = zip.getFOLDER_PATH_NAME();
                    int lastIndex = folder.lastIndexOf("\\");
                    int folderLength = folder.substring(lastIndex + 1, folder.length()).length();

                    arrayListResult.add(filePathName.substring(folderLength + 1, filePathName.length()));
                    entry = zipInputStream.getNextEntry();
                }

                for (int i = 0; i < arrayListResult.size(); i++) {
                    assertThat(arrayList.get(i), is(arrayListResult.get(i)));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}