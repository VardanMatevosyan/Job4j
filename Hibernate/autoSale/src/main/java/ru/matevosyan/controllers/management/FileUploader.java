package ru.matevosyan.controllers.management;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.matevosyan.persistens.repository.UserRepository;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * FileUploader class to upload users file.
 */
public class FileUploader {
    private static final Logger LOG = LoggerFactory.getLogger(FileUploader.class.getName());
    private static final UserRepository USER_REPOSITORY = new UserRepository();

    /**
     * FileUploader constructor.
     */
    public FileUploader() {
    }

    /**
     * upload method to upload users file.
     * @param req client request with file parts.
     */
    public void upload(final HttpServletRequest req) {
        String imagePath = USER_REPOSITORY.getLastAddedOfferImagePath();

        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOG.error("problem with setting char encoding in upload method in FileUploaser class {}", e);
        }

        List<Part> fileParts = null;
        try {
            fileParts = req.getParts().stream().filter(part -> "file".equals(part.getName())).collect(Collectors.toList());
            for (Part filePart : fileParts) {
                String filePath = String.format("%s", Paths.get(imagePath));
                InputStream fileContent = filePart.getInputStream();
                ReadableByteChannel rbc = Channels.newChannel(fileContent);
                File dir = new File(Paths.get(imagePath).getParent().toUri());
                File file = new File(filePath);
                if (!(dir.exists())) {
                    dir.mkdirs();
                    file.createNewFile();
                } else if (dir.exists()) {
                    file.createNewFile();
                } else {
                    throw new IOException("Folder in which file gonna be created is not found");
                }
                FileOutputStream fos = new FileOutputStream(filePath);
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            }
        } catch (IOException ioExp) {
            LOG.error("problem with i/o in FileUploader class in upload method {}", ioExp);
        } catch (ServletException servletExp) {
            LOG.error("problem with get parts in upload method in FileUploader class {}", servletExp);
        }
    }
}
