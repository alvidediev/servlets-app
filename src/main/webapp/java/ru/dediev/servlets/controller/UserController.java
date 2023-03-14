package ru.dediev.servlets.controller;

import com.google.gson.Gson;
import ru.dediev.servlets.model.entity.User;
import ru.dediev.servlets.service.UserService;
import ru.dediev.servlets.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user")
public class UserController extends HttpServlet {

    private final UserService userService = new UserServiceImpl();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter outWriter = resp.getWriter();
        final User user = gson.fromJson(req.getReader(), User.class);
        if (user.getId() == 0) {
            outWriter.print(gson.toJson(userService.getAll()));
        } else {
            outWriter.print(gson.toJson(userService.getById(user.getId())));
        }
        outWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter outWriter = resp.getWriter();
        final User savedUser = userService.save(gson.fromJson(req.getReader(), User.class));
        outWriter.println(gson.toJson(userService.getById(savedUser.getId())));
        outWriter.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter outWriter = resp.getWriter();
        final User updatedUser = userService.update(gson.fromJson(req.getReader(), User.class));
        outWriter.println("Successfully updated");
        outWriter.println(gson.toJson(userService.getById(updatedUser.getId())));
        outWriter.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter outWriter = resp.getWriter();
        final User user = gson.fromJson(req.getReader(), User.class);
        userService.remove(user.getId());
        outWriter.println("Successfully deleted!");
        outWriter.flush();

    }
}
