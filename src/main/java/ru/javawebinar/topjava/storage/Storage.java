package ru.javawebinar.topjava.storage;

import java.util.List;

public interface Storage<T, K> {

    T create(T meal);

    T read(K id);

    T update(T meal);

    void delete(K id);

    List<T> getAll();
}
