package ru.matevosyan;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created for zipping file from archive.
 * Created on 19.02.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class ZipWithStructure {

    private final String FOLDER_PATH_NAME;
    private final String[] S;
    private List<String> listOfFile = new ArrayList<>();

    /**
     * Adding folder path name and array of keys to the constructor.
     * @param folder_path_name a String representing path name to the folder.
     * @param s array of file extension, passing for zipping, files that have extension in {@link ZipWithStructure#S}
     */

    public ZipWithStructure(String folder_path_name, String[] s) {
        FOLDER_PATH_NAME = folder_path_name;
        S = s;
    }

    /**
     * Getter for {@link ZipWithStructure#listOfFile}.
     * @return listOfFile list of all paths.
     */

    public List<String> getListOfFile() {
        return listOfFile;
    }

    /**
     * Getter for {@link ZipWithStructure#FOLDER_PATH_NAME}.
     * @return FOLDER_PATH_NAME unzipping folder that using to zip with {@link ZipWithStructure#zipping(String)}
     */

    public String getFOLDER_PATH_NAME() {
        return FOLDER_PATH_NAME;
    }


    public void unzipping(String file) throws IOException{

        String folder = folderReturner(file);
        File direct = new File(folder);

        if (direct.isDirectory() && !direct.exists()) {
            direct.mkdir();
        }

        try (FileInputStream fileInputStream = new FileInputStream(file);
            ZipInputStream zip = new ZipInputStream(fileInputStream)) {

            ZipEntry entry = zip.getNextEntry();
            String fileName;
            while (entry != null) {
                fileName = entry.getName();

                File newFile = new File(folder + File.separator + fileName);

                if (entry.isDirectory()) {
                    new File(fileName).mkdir();
                } else {
                    new File(newFile.getParent()).mkdirs();

                    try (FileOutputStream fos = new FileOutputStream(newFile)) {
                        int len;
                        byte[] byteArray = new byte[1024];
                        while ((len = zip.read(byteArray)) > 0) {
                            fos.write(byteArray, 0, len);
                        }
                    }
                }
                entry = zip.getNextEntry();
            }

            zip.closeEntry();
        } catch (IOException ioe) {
            ioe.getMessage();
        }

    }
    private String folderReturner(String file) {
        return file.substring(0, file.lastIndexOf("."));
    }


    public void zipping(String outZip) throws IOException {

        this.genListOfFiles(new File(FOLDER_PATH_NAME), S);

        try (FileOutputStream fos = new FileOutputStream(outZip);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            int length;
            byte[] bytes = new byte[1024];

            String sourceFolder = FOLDER_PATH_NAME.substring(FOLDER_PATH_NAME.lastIndexOf("\\") + 1, FOLDER_PATH_NAME.length());


            for (String fileName : this.listOfFile) {
                ZipEntry zipEntry = new ZipEntry(sourceFolder + File.separator + fileName);
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
                for (String anExtension : extension) {
                    if (fileObject.toString().endsWith("." + anExtension) || fileObject.toString().endsWith("." + anExtension.toUpperCase())) {
                        this.listOfFile.add(getEntryNameOfFile(fileObject.toString()));
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