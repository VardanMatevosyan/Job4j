package ru.matevosyan.utils;


import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * FileUploader class to upload users file.
 */
@Component
public class FileUploader {
    private static final Logger LOG = LoggerFactory.getLogger(FileUploader.class.getName());

    /**
     * FileUploader constructor.
     */
    public FileUploader() {
    }

    /**
     * upload method to upload users file.
     * @param file file to upload.
     * @param path pth to the file.
     */
    public void upload(final MultipartFile file, String path) {
        try {
            FileUtils.writeByteArrayToFile(new File(path), file.getBytes());
        } catch (FileNotFoundException fileExp) {
            LOG.error("file not found. Trying to write user cars image file. {} ", fileExp);
        } catch (IOException ioExp) {
            LOG.error("problem when get bytes from multipart file {} ", ioExp);
        }
    }

    /**
     * Delete directory with all user image files.
     * @param dir directory with images.
     */
    public void delete(final File dir) {
        try {
            FileUtils.forceDelete(dir);
        } catch (IOException ioExp) {
            LOG.error("problem when get info about file {} ", ioExp);
        }
    }
}
