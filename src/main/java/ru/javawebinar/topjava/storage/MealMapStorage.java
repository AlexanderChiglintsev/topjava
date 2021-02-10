package ru.javawebinar.topjava.storage;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.UserServlet;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static org.slf4j.LoggerFactory.getLogger;

public class MealMapStorage implements MealStorage {
    private static final Logger log = getLogger(UserServlet.class);
    private final Map<String, Meal> meals;

    public MealMapStorage() {
        meals = new ConcurrentHashMap<>();
        String uuid1 = UUID.randomUUID().toString();
        meals.put(uuid1, new Meal(uuid1, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        String uuid2 = UUID.randomUUID().toString();
        meals.put(uuid2, new Meal(uuid2, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        String uuid3 = UUID.randomUUID().toString();
        meals.put(uuid3, new Meal(uuid3, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        String uuid4 = UUID.randomUUID().toString();
        meals.put(uuid4, new Meal(uuid4, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        String uuid5 = UUID.randomUUID().toString();
        meals.put(uuid5, new Meal(uuid5, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        String uuid6 = UUID.randomUUID().toString();
        meals.put(uuid6, new Meal(uuid6, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        String uuid7 = UUID.randomUUID().toString();
        meals.put(uuid7, new Meal(uuid7, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    @Override
    public Meal create(Meal meal) {
        if (meal.getId().equals("new")) {
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
