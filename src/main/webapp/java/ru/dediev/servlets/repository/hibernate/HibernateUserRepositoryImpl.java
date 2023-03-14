package ru.dediev.servlets.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.dediev.servlets.config.HibernateConfiguration;
import ru.dediev.servlets.model.entity.User;
import ru.dediev.servlets.repository.UserRepository;

import java.util.List;

public class HibernateUserRepositoryImpl implements UserRepository {

    @Override
    public User save(User userEntity) {
        try (Session session = HibernateConfiguration.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(userEntity);
            transaction.commit();
        }
        return userEntity;
    }

    @Override
    public User getById(Integer id) {
        try (Session session = HibernateConfiguration.getSession()) {
            return session
                    .createQuery("FROM User a LEFT JOIN FETCH a.eventEntities WHERE a.id = :userId",
                            User.class)
                    .setParameter("userId", id)
                    .getSingleResult();
        }
    }

    @Override
    public List<User> getAll() {
        try (Session session = HibernateConfiguration.getSession()) {
            return session
                    .createQuery(
                            "SELECT a FROM User a JOIN FETCH a.eventEntities e",
                            User.class)
                    .getResultList();
        }
    }

    @Override
    public User update(User userEntity) {
        try (Session session = HibernateConfiguration.getSession()) {
            Transaction transaction = session.beginTransaction();
            User userEntityEntityToUpdate = getById(userEntity.getId());
            userEntityEntityToUpdate.setName(userEntity.getName());
            session.merge(userEntityEntityToUpdate);
            transaction.commit();
        }
        return userEntity;
    }

    @Override
    public void remove(Integer id) {
        try (Session session = HibernateConfiguration.getSession()) {
            Transaction transaction = session.beginTransaction();
            User userEntityEntityForRemove = getById(id);
            session.remove(userEntityEntityForRemove);
            transaction.commit();
        }
    }
}
