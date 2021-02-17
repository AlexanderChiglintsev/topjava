package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, HashMap<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals1.forEach(meal -> save(meal, 1));
        MealsUtil.meals2.forEach(meal -> save(meal, 2));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.computeIfAbsent(userId, k -> new HashMap<>());
            repository.get(userId).put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        if (get(meal.getId(), userId) != null) {
            return repository.get(userId).put(meal.getId(), meal);
        }
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        if (get(id, userId) != null) {
            repository.get(userId).remove(id);
            return true;
        }
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        HashMap<Integer, Meal> userMeals = repository.get(userId);
        return userMeals.get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        if (repository.get(userId) == null) {
            return Collections.emptyList();
        }
        return getAllWithFilter(meal -> true, userId);
    }

    @Override
    public List<Meal> getAllFiltered(LocalDate startDate, LocalDate endDate, int userId) {
        return getAllWithFilter(meal -> DateTimeUtil.isBetween(meal.getDate(), startDate, endDate), userId);
    }

    private List<Meal> getAllWithFilter(Predicate<Meal> filter, int userId) {
        return repository.get(userId).values().stream()
                .filter(filter)
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }
}

