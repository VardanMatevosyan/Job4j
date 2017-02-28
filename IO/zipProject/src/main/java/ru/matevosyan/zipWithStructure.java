package ru.matevosyan;

import java.io.*;
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

    public void zipping(File file, String ... keys) throws IOException {

        try (ZipInputStream zip = new ZipInputStream(new FileInputStream(file));
             ZipFile zipFile = new ZipFile(file)) {
            ZipEntry entry;
            Enumeration entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                entry = (ZipEntry) entries.nextElement();
                for (String key : keys) {
                    if (entry.getName().contains("." + key)) {
                        writeToZip(zipFile.getInputStream(entry),
                                new BufferedOutputStream(new FileOutputStream(
                                        new File(file.getParent(), entry.getName()))));
                    } else if (entry.isDirectory()) {
                        new File(file.getParent(), entry.getName()).mkdirs();
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
        while ((length = in.read(buff)) != -1) {
            out.write(buff, 0, length);
        }
    }
}
