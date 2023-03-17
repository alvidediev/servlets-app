package ru.dediev.servlets.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.dediev.servlets.config.HibernateConfiguration;
import ru.dediev.servlets.model.entity.FileEntity;
import ru.dediev.servlets.repository.FileRepository;

import java.util.List;

public class HibernateFileRepositoryImpl implements FileRepository {

    @Override
    public FileEntity save(FileEntity fileEntity) {
        try (Session session = HibernateConfiguration.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(fileEntity);
            transaction.commit();
            return fileEntity;
        }
    }

    @Override
    public FileEntity getById(Integer id) {
        try (Session session = HibernateConfiguration.getSession()) {
            return session
                    .createQuery("FROM FileEntity a WHERE a.id = :fileId", FileEntity.class)
                    .setParameter("fileId", id)
                    .getSingleResult();
        }
    }

    @Override
    public List<FileEntity> getAll() {
        try (Session session = HibernateConfiguration.getSession()) {
            return session
                    .createQuery("SELECT a FROM FileEntity a", FileEntity.class)
                    .getResultList();
        }
    }

    @Override
    public FileEntity update(FileEntity fileEntity) {
        try (Session session = HibernateConfiguration.getSession()) {
            Transaction transaction = session.beginTransaction();
            FileEntity fileEntityToUpdate = getById(fileEntity.getId());
            fileEntityToUpdate.setName(fileEntity.getName());
            fileEntityToUpdate.setPath(fileEntity.getPath());
            session.merge(fileEntityToUpdate);
            transaction.commit();
        }
        return fileEntity;
    }

    @Override
    public void remove(Integer id) {
        try (Session session = HibernateConfiguration.getSession()) {
            Transaction transaction = session.beginTransaction();
            FileEntity fileEntityToRemove = getById(id);
            session.remove(fileEntityToRemove);
            transaction.commit();
        }
    }
}
