package ru.dediev.servlets.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.dediev.servlets.config.HibernateConfiguration;
import ru.dediev.servlets.model.entity.Event;
import ru.dediev.servlets.repository.EventRepository;

import java.util.List;

public class HibernateEventRepositoryImpl implements EventRepository {
    @Override
    public Event save(Event eventEntity) {
        try (Session session = HibernateConfiguration.getSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(eventEntity);
            transaction.commit();
        }
        return eventEntity;
    }

    @Override
    public Event getById(Integer id) {
        try (Session session = HibernateConfiguration.getSession()) {
            return session
                    .createQuery("select a from  Event a where  a.id = :eventId"
                            , Event.class)
                    .setParameter("eventId", id)
                    .getSingleResult();
        }
    }

    @Override
    public List<Event> getAll() {
        try(Session session = HibernateConfiguration.getSession()){
            return session
                    .createQuery("SELECT a FROM Event a",
                            Event.class)
                    .getResultList();
        }
    }

    @Override
    public Event update(Event eventEntity) {
        try (Session session = HibernateConfiguration.getSession()){
            Transaction transaction = session.beginTransaction();
            Event eventEntityToUpdate = getById(eventEntity.getId());
            eventEntityToUpdate.setFile(eventEntity.getFile());
            eventEntityToUpdate.setUser(eventEntity.getUser());
            session.merge(eventEntityToUpdate);
            transaction.commit();
        }
        return eventEntity;
    }

    @Override
    public void remove(Integer id) {
        try (Session session = HibernateConfiguration.getSession()){
            Transaction transaction = session.beginTransaction();
            Event eventEntityToRemove = getById(id);
            session.remove(eventEntityToRemove);
            transaction.commit();
        }
    }
}
