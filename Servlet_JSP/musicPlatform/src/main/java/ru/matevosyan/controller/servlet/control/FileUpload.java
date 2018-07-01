package ru.matevosyan.controller.servlet.control;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

import java.io.IOException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.File;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * FileUpload class for uploading files that user choose to upload.
 */
@MultipartConfig
public class FileUpload extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    /**
     * initialization servlet.
     * @param config servlet config.
     * @throws ServletException servlet exception.
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    /**
     * FileUpload class upload files from input stream.
     *
     * NOTE:
     * get list of files from request Part retrieves from <input type="file" name="file" multiple="true">.
     * filePart.getSubmittedFileName() work only for servlet version 3.1.
     * Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); - MSIE fix.
     * Paths.get(filePart.getSubmittedFileName()) - Get submitted path to the file,
     * and invoke .getFileName() get real file name.
     *
     * Parameters:
     * @param request user.
     * @param response user.
     * @throws ServletException if problem with getting from request or writing to response.
     * @throws IOException if problem with input or output methods invocation.
     */

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType(CONTENT_TYPE);
        List<Part> fileParts = request.getParts().stream().filter(part -> "file".equals(part.getName())).collect(Collectors.toList());
        for (Part filePart : fileParts) {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String filePath = String.format("%s%s%s%s",
                    Paths.get(getServletContext().getRealPath("/")).normalize().getRoot(),
                    getServletConfig().getInitParameter("uploadFilePath"),
                    System.getProperty("file.separator"), fileName);
            InputStream fileContent = filePart.getInputStream();
            ReadableByteChannel rbc = Channels.newChannel(fileContent);
            File dir = new File(String.format("%s%s",
                    Paths.get(getServletContext().getRealPath("/")).normalize().getRoot(),
                    getServletConfig().getInitParameter("uploadFilePath")));
            File file = new File(filePath);
            if (!(dir.exists())) {
                dir.mkdirs();
            } else if (dir.exists()) {
                file.createNewFile();
            } else {
                throw new IOException("Folder in which file gonna be created is not found");
            }
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }
        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
    }
}

