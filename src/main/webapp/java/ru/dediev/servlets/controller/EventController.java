package ru.dediev.servlets.controller;

import com.google.gson.Gson;
import ru.dediev.servlets.model.entity.Event;
import ru.dediev.servlets.service.EventService;
import ru.dediev.servlets.service.impl.EventServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "EventMapping", urlPatterns = "/event")
public class EventController extends HttpServlet {

    private final EventService eventService = new EventServiceImpl();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter outWriter = resp.getWriter();
        final Event eventId = gson.fromJson(req.getReader(), Event.class);
        if (eventId.getId() == 0) {
            outWriter.print(gson.toJson(eventService.getAll()));
        } else {
            outWriter.print(gson.toJson(eventService.getById(eventId.getId())));
        }
        outWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter outWriter = resp.getWriter();
        final Event savedEvent = eventService.save(gson.fromJson(req.getReader(), Event.class));
        outWriter.println(gson.toJson(eventService.getById(savedEvent.getId())));
        outWriter.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter outWriter = resp.getWriter();
        final Event updatedEvent = eventService.update(gson.fromJson(req.getReader(), Event.class));
        outWriter.println(gson.toJson(eventService.getById(updatedEvent.getId())));
        outWriter.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Event event = gson.fromJson(req.getReader(), Event.class);
        eventService.remove(event.getId());
        PrintWriter outWriter = resp.getWriter();
        outWriter.println("Successfully deleted!");
        outWriter.flush();
    }
}
