package ru.dediev.servlets.service.impl;

import ru.dediev.servlets.model.entity.Event;
import ru.dediev.servlets.model.entity.FileEntity;
import ru.dediev.servlets.model.entity.User;
import ru.dediev.servlets.repository.hibernate.HibernateEventRepositoryImpl;
import ru.dediev.servlets.repository.hibernate.HibernateFileRepositoryImpl;
import ru.dediev.servlets.repository.hibernate.HibernateUserRepositoryImpl;
import ru.dediev.servlets.service.EventService;

import java.util.List;

public class EventServiceImpl implements EventService {

    private final HibernateEventRepositoryImpl eventRepository;
    private final HibernateFileRepositoryImpl fileRepository;
    private final HibernateUserRepositoryImpl userRepository;

    public EventServiceImpl() {
        this.eventRepository = new HibernateEventRepositoryImpl();
        this.userRepository = new HibernateUserRepositoryImpl();
        this.fileRepository = new HibernateFileRepositoryImpl();
    }

    public EventServiceImpl(HibernateEventRepositoryImpl eventRepository,
                            HibernateFileRepositoryImpl fileRepository,
                            HibernateUserRepositoryImpl userRepository) {
        this.eventRepository = eventRepository;
        this.fileRepository = fileRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Event save(Event eventEntity) {
        return eventRepository.save(eventEntity);
    }

    @Override
    public Event getById(Integer id) {
        return eventRepository.getById(id);
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.getAll();
    }

    @Override
    public Event update(Event eventEntity) {
        return eventRepository.update(eventEntity);
    }

    @Override
    public void remove(Integer id) {
        eventRepository.remove(id);
    }
}
