package ru.dediev.servlets.service.impl;

import ru.dediev.servlets.model.entity.Event;
import ru.dediev.servlets.model.entity.FileEntity;
import ru.dediev.servlets.model.entity.User;
import ru.dediev.servlets.repository.hibernate.HibernateEventRepositoryImpl;
import ru.dediev.servlets.repository.hibernate.HibernateFileRepositoryImpl;
import ru.dediev.servlets.repository.hibernate.HibernateUserRepositoryImpl;
import ru.dediev.servlets.service.FileService;

import java.util.List;

public class FileServiceImpl implements FileService {

    private final HibernateEventRepositoryImpl eventRepository;
    private final HibernateFileRepositoryImpl fileRepository;
    private final HibernateUserRepositoryImpl userRepository;

    public FileServiceImpl(HibernateFileRepositoryImpl fileRepository,
                           HibernateEventRepositoryImpl eventRepository,
                           HibernateUserRepositoryImpl userRepository) {
        this.fileRepository = fileRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public FileServiceImpl() {
        this.fileRepository = new HibernateFileRepositoryImpl();
        this.userRepository = new HibernateUserRepositoryImpl();
        this.eventRepository = new HibernateEventRepositoryImpl();
    }

    @Override
    public FileEntity save(FileEntity fileEntity) {
        final FileEntity fileToSave = fileEntity.getEvent().getFile();
        final FileEntity savedFile = fileRepository.save(fileToSave);
        final User userToSave = fileEntity.getEvent().getUser();
        final User savedUser = userRepository.save(userToSave);
        Event event = new Event();
        event.setFile(savedFile);
        event.setUser(savedUser);
        eventRepository.save(event);
        return fileRepository.save(fileEntity);
    }

    @Override
    public FileEntity getById(Integer id) {
        return fileRepository.getById(id);
    }

    @Override
    public List<FileEntity> getAll() {
        return fileRepository.getAll();
    }

    @Override
    public FileEntity update(FileEntity fileEntity) {
        return fileRepository.update(fileEntity);
    }

    @Override
    public void remove(Integer id) {
        fileRepository.remove(id);
    }
}
