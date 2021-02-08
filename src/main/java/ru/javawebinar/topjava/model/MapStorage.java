package ru.javawebinar.topjava.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage implements Storage {
    Map<String, Meal> meals = new HashMap<>();

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
    public void delete(Meal meal) {
        meals.remove(meal.getId());
    }

    @Override
    public List<Meal> getAll() {
        return (List<Meal>) meals.values();
    }
}
