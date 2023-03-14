package ru.dediev.servlets.service.impl;

import ru.dediev.servlets.model.entity.User;
import ru.dediev.servlets.repository.hibernate.HibernateUserRepositoryImpl;
import ru.dediev.servlets.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final HibernateUserRepositoryImpl repository;

    public UserServiceImpl(HibernateUserRepositoryImpl repository) {
        this.repository = repository;
    }

    public UserServiceImpl() {
        this.repository = new HibernateUserRepositoryImpl();
    }

    @Override
    public User save(User userEntity) {
        return repository.save(userEntity);
    }

    @Override
    public User getById(Integer id) {
        return repository.getById(id);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public User update(User userEntity) {
        return repository.update(userEntity);
    }

    @Override
    public void remove(Integer id) {
        repository.remove(id);
    }
}
