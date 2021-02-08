package ru.javawebinar.topjava.model;

import java.util.Collection;

public interface Storage {

    void create(Meal meal);

    Meal read(String id);

    void update(Meal meal);

    void delete(Meal meal);

    Collection<Meal> getAll();
}
