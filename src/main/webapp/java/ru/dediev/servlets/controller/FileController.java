package ru.dediev.servlets.controller;

import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.dediev.servlets.model.entity.FileEntity;
import ru.dediev.servlets.service.impl.FileServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static ru.dediev.servlets.util.Constants.*;


@WebServlet(name = "FileUploadServlet", urlPatterns = "/file")
public class FileController extends HttpServlet {

    private final FileServiceImpl fileService = new FileServiceImpl();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter outWriter = resp.getWriter();
        final FileEntity file = gson.fromJson(req.getReader(), FileEntity.class);
        if (file.getId() == 0) {
            outWriter.print(gson.toJson(fileService.getAll()));
        } else {
            outWriter.print(gson.toJson(fileService.getById(file.getId())));
        }
        outWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter outWriter = resp.getWriter();
        final FileEntity savedFile = fileService.save(fileUploadMethod(req));
        outWriter.println(gson.toJson(fileService.getById(savedFile.getId())));
        outWriter.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter outWriter = resp.getWriter();
        final FileEntity updatedFile = fileService.update(gson.fromJson(req.getReader(), FileEntity.class));
        outWriter.println(gson.toJson(fileService.getById(updatedFile.getId())));
        outWriter.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter outWriter = resp.getWriter();
        FileEntity file = gson.fromJson(req.getReader(), FileEntity.class);
        fileService.remove(file.getId());
        outWriter.println("Successfully deleted!");
        outWriter.flush();
    }

    protected FileEntity fileUploadMethod(HttpServletRequest req) throws IOException {
        FileEntity fileEntity = new FileEntity();

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setRepository(new File(FILE_PATH));
        diskFileItemFactory.setSizeThreshold(MEM_MAX_SIZE);

        ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
        upload.setSizeMax(FILE_MAX_SIZE);

        try {
            List<FileItem> fileItems = upload.parseRequest(req);

            for (FileItem fileItem : fileItems) {
                if (!fileItem.isFormField()) {
                    String fileName = fileItem.getName();
                    File file;
                    if (fileName.lastIndexOf("\\") >= 0) {
                        file = new File(FILE_PATH +
                                fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        file = new File(FILE_PATH +
                                fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }
                    fileItem.write(file);
                    fileEntity.setName(fileName);
                    fileEntity.setPath(file.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileEntity;
    }
}
