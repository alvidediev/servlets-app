package ru.dediev.servlets.service.impl;

import ru.dediev.servlets.model.entity.FileEntity;
import ru.dediev.servlets.repository.hibernate.HibernateFileRepositoryImpl;
import ru.dediev.servlets.service.FileService;

import java.util.List;

public class FileServiceImpl implements FileService {

    private final HibernateFileRepositoryImpl fileRepository;

    public FileServiceImpl(HibernateFileRepositoryImpl fileRepository) {
        this.fileRepository = fileRepository;
    }

    public FileServiceImpl() {
        this.fileRepository = new HibernateFileRepositoryImpl();
    }

    @Override
    public FileEntity save(FileEntity fileEntity) {
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
