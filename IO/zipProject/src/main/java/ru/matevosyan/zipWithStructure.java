package ru.matevosyan;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.nio.file.Files;
import java.io.File;
import java.util.zip.ZipInputStream;

/**
 * Created for zipping file from archive.
 * Created on 19.02.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class zipWithStructure {

    public void zipping(File file, String ... keys) throws IOException {

        try (ZipFile zipFile = new ZipFile(file)) {
            ZipEntry entry;
            Enumeration entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                entry = (ZipEntry) entries.nextElement();
                for (String key : keys) {
//                    if (entry.getName().endWith("." + key)) {
//                        writeToZip(zipFile.getInputStream(entry),
//                                new BufferedOutputStream(new FileOutputStream(
//                                        new File(file.getParent(), entry.getName()))));
//                    } else
                        if (entry.isDirectory()) {
                            File[] listOfFile = searchFileExtension(entry, key);
                            for (File keyFile: listOfFile) {
                                if (keyFile.exists()) {
                                    writeToZip(zipFile.getInputStream(entry),
                                new BufferedOutputStream(new FileOutputStream(
                                        new File(file.getParent(), entry.getName()))));
                                } else {
//                        File[] listOfFileWithKey = fileEntry.listFiles("." + key);
                                    File dir = new File(file.getParent(), entry.getName());
                                    dir.mkdirs();
                                }
                            }
                    }
                }
            }
        }  catch (FileNotFoundException fnfe) {
            fnfe.getMessage();
        }
    }

    public void writeToZip(InputStream in, OutputStream out) throws IOException {
        byte[] buff = new byte[1024];
        int length;
        while ((length = in.read(buff)) >= 0) {
            out.write(buff, 0, length);
        }
    }

    public static File[] searchFileExtension(ZipEntry entry, String key) {
        File file = new File(String.valueOf(entry));
        File[] files = file.listFiles(new Filter("." + key));

            if (files.length == 0) {
                System.out.println("Directory does not exist files with ends with " + key);
            } else {
                return files;
            }

    }

    public static class Filter implements FilenameFilter {

        String key;

        public Filter(String key) {
            this.key = key.toLowerCase();
        }

        @Override
        public boolean accept(File dir, String fileName) {
            return fileName.toLowerCase().endsWith(this.key);
        }
    }
}
