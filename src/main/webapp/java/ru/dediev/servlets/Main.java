package ru.dediev.servlets;

import com.google.gson.Gson;
import ru.dediev.servlets.model.entity.Event;
import ru.dediev.servlets.model.entity.FileEntity;
import ru.dediev.servlets.model.entity.User;
import ru.dediev.servlets.service.impl.EventServiceImpl;
import ru.dediev.servlets.service.impl.FileServiceImpl;

import java.util.Arrays;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        EventServiceImpl eventService = new EventServiceImpl();
        List<Event> list = List.of(
                new Event()
        );

        User user = new User();
        user.setEvents(list);
        FileEntity file = new FileEntity();

        eventService.save()

    }
}
