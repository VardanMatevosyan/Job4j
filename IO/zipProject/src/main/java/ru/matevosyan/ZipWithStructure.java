package ru.matevosyan;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created for zipping file from archive.
 * Created on 19.02.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class ZipWithStructure {

    private String FOLDER_PATH_NAME;

    public ZipWithStructure(String folder_path_name) {
        FOLDER_PATH_NAME = folder_path_name;
    }
    private List<String> listOfFile = new ArrayList<>();

    public void zipping(String outZip) throws IOException {

        try (FileOutputStream fos = new FileOutputStream(outZip); ZipOutputStream zos = new ZipOutputStream(fos)) {

            int length;
            byte[] bytes = new byte[1024];

            for (String fileName : this.listOfFile) {
                ZipEntry zipEntry = new ZipEntry(fileName);
                zos.putNextEntry(zipEntry);

                FileInputStream fis = new FileInputStream(FOLDER_PATH_NAME + File.separator + fileName);

                while ((length = fis.read(bytes)) > 0) {
                    zos.write(bytes, 0, length);
                }
            }

            zos.closeEntry();
        } catch (FileNotFoundException fnf) {
            fnf.getMessage();
        }
    }

    public void genListOfFiles(File fileObject, String ... extension) {
        if (fileObject.exists()) {
            if (fileObject.isFile()) {
                for (int i = 0; i < extension.length; i++) {
                    String anExtension = extension[i];
                    if (fileObject.toString().endsWith("." + anExtension)) {
                        this.listOfFile.add(getEntryNameOfFile(fileObject.getAbsoluteFile().toString()));
                    }
                }
            }

            if (fileObject.isDirectory()) {
                String[] fileList = fileObject.list();

                if (fileList != null) {
                    for (String file : fileList) {
                        genListOfFiles(new File(fileObject, file), extension);
                    }
                }
            }
        }
    }

    private String getEntryNameOfFile(String name) {
        return name.substring(FOLDER_PATH_NAME.length() + 1, name.length());
    }
}
