package ru.javawebinar.topjava.storage;

import java.util.List;

public interface Storage<T, K> {

    T create(T t);

    T read(K id);

    T update(T t);

    void delete(K id);

    List<T> getAll();
}
