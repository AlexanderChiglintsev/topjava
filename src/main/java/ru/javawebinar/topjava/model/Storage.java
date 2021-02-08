package ru.javawebinar.topjava.model;

import java.util.List;

public interface Storage {

    void create(Meal meal);

    Meal read(String id);

    void update(Meal meal);

    void delete(String id);

    List<Meal> getAll();
}
