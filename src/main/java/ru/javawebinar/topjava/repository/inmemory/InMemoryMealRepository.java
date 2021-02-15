package ru.javawebinar.topjava.repository.inmemory;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(meal, meal.getUserId()));
    }

    @Override
    public Meal save(Meal meal, Integer userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userId);
            repository.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        if (get(meal.getId(), userId) != null) {
            return repository.put(meal.getId(), meal);
        }
        return null;
    }

    @Override
    public boolean delete(int id, Integer userId) {
        if (get(id, userId) != null) {
            return repository.remove(id) != null;
        }
        return false;
    }

    @Override
    public Meal get(int id, Integer userId) {
        Meal meal = repository.get(id);
        if ((meal != null) && (meal.getUserId().equals(userId))) {
            return meal;
        }
        return null;
    }

    @Override
    public Collection<Meal> getAll(Integer userId) {
        return getAllFiltered(LocalDate.MIN, LocalTime.MIN, LocalDate.MAX, LocalTime.MAX, userId);
    }

    @Override
    public Collection<Meal> getAllFiltered(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, Integer userId) {
        return repository.values().stream()
                .filter(meal -> meal.getUserId().equals(userId))
                .filter(meal -> DateTimeUtil.isBetweenHalfOpen(meal.getDate(), startDate, endDate))
                .filter(meal -> DateTimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endTime))
                .sorted(Comparator.comparing(Meal::getDate).reversed())
                .collect(Collectors.toList());
    }
}

