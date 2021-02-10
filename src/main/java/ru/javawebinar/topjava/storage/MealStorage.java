package ru.javawebinar.topjava.storage;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static org.slf4j.LoggerFactory.getLogger;

public class MealStorage implements Storage<Meal, String> {
    private static final Logger log = getLogger(MealStorage.class);
    private final Map<String, Meal> meals;

    public MealStorage() {
        meals = new ConcurrentHashMap<>();
        this.create(new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        this.create(new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        this.create(new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        this.create(new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        this.create(new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        this.create(new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        this.create(new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    @Override
    public Meal create(Meal meal) {
        if (meal.getId() == null) {
            Meal newMeal = new Meal(
                    UUID.randomUUID().toString(),
                    meal.getDateTime(),
                    meal.getDescription(),
                    meal.getCalories()
            );
            meals.put(newMeal.getId(), newMeal);
            return newMeal;
        }
        log.error("Isn't new meal!");
        return null;
    }

    @Override
    public Meal read(String id) {
        return meals.get(id);
    }

    @Override
    public Meal update(Meal meal) {
        if (meals.get(meal.getId()) != null) {
            meals.put(meal.getId(), meal);
            return meal;
        }
        log.error("Meal with such ID not exist!");
        return null;
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
