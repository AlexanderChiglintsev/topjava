package ru.javawebinar.topjava.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapStorage implements Storage {
    Map<String, Meal> meals = new ConcurrentHashMap<>();

    @Override
    public void create(Meal meal) {
        meals.put(meal.getId(), meal);
    }

    @Override
    public Meal read(String id) {
        return meals.get(id);
    }

    @Override
    public void update(Meal meal) {
        meals.put(meal.getId(), meal);
    }

    @Override
    public void delete(String id) {
        meals.remove(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(meals.values());
    }
}
