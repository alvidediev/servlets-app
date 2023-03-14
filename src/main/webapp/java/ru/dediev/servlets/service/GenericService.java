package ru.dediev.servlets.service;

import java.util.List;

public interface GenericService <T, ID>{
    T save(T t);

    T getById(ID id);

    List<T> getAll();

    T update(T t);

    void remove(ID id);
}
