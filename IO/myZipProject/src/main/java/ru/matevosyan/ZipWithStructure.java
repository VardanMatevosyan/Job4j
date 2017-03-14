package ru.matevosyan;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
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

    private final String folderPathName;
    private final String[] s;
    private List<String> listOfFile = new ArrayList<>();

    /**
     * Adding folder path name and array of keys to the constructor.
     * @param folderPathNames a String representing path name to the folder.
     * @param str array of file extension, passing for zipping, files that have extension in {@link ZipWithStructure#s}
     */

    public ZipWithStructure(String folderPathNames, String[] str) {
        folderPathName = folderPathNames;
        s = str;
    }

    /**
     * Getter for {@link ZipWithStructure#listOfFile}.
     * @return listOfFile list of all paths.
     */

    public List<String> getListOfFile() {
        return listOfFile;
    }

    /**
     * Getter for {@link ZipWithStructure#folderPathName}.
     * @return FOLDER_PATH_NAME unzipping folder that using to zip with {@link ZipWithStructure#zipping(String)}
     */

    public String getFolderPathName() {
        return folderPathName;
    }

    /**
     * Unzip directory from zip file.
     * @param file passing zip file.
     * @throws IOException throwing exception.
     */

    public void unzipping(String file) throws IOException {

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

    /**
     * Created to return folder name extracting it from filePath.
     * @param file zip file path.
     * @return folder name.
     */

    private String folderReturner(String file) {
        return file.substring(0, file.lastIndexOf("."));
    }

    /**
     * Created to zip folder with file which extension is the same passing extension {@link ZipWithStructure#s}.
     * @param outZip zip path name.
     * @throws IOException throwing exception.
     */

    public void zipping(String outZip) throws IOException {

        this.genListOfFiles(new File(folderPathName), s);

        try (FileOutputStream fos = new FileOutputStream(outZip);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            int length;
            byte[] bytes = new byte[1024];

            String sourceFolder = folderPathName.substring(folderPathName.lastIndexOf("\\") + 1, folderPathName.length());


            for (String fileName : this.listOfFile) {
                ZipEntry zipEntry = new ZipEntry(sourceFolder + File.separator + fileName);
                zos.putNextEntry(zipEntry);

                FileInputStream fis = new FileInputStream(folderPathName + File.separator + fileName);

                while ((length = fis.read(bytes)) > 0) {
                    zos.write(bytes, 0, length);
                }
            }

            zos.closeEntry();
        } catch (FileNotFoundException fnf) {
            fnf.getMessage();
        }
    }

    /**
     * Fill {@link ZipWithStructure#listOfFile} only that files path name, that extension is equals with passing.
     * @param fileObject folder path name.
     * @param extension array of extension file, to find only files with this extension.
     */

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

    /**
     * Return file names without directories that {@link ZipWithStructure#genListOfFiles(File, String...)} is get.
     * @param name passing each file path name.
     * @return file names without directories.
     */

    private String getEntryNameOfFile(String name) {
        return name.substring(folderPathName.length() + 1, name.length());
    }
}