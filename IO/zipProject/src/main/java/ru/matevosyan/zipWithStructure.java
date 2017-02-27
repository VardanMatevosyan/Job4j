package ru.matevosyan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * Created for zipping file from archive.
 * Created on 19.02.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class zipWithStructure {

    public void zipping(File file, String[] keys) throws IOException {

        try (ZipInputStream zip = new ZipInputStream(new FileInputStream(file)); ZipFile zipFile = new ZipFile(file)) {
            ZipEntry entry;
            Enumeration entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                entry = (ZipEntry) entries.nextElement();
                for (String key : keys) {
                    if (entry.getName().contains("." + key)) {

                    }
                }
            }
        }  catch (FileNotFoundException fnfe) {
            fnfe.getMessage();
        }
    }
}
