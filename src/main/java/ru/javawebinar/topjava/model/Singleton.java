package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

public class Singleton {
    private static Singleton instance;
    private final Storage storage;

    private Singleton() {
        storage = new MapStorage();
        storage.create(new Meal(UUID.randomUUID().toString(), LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        storage.create(new Meal(UUID.randomUUID().toString(), LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        storage.create(new Meal(UUID.randomUUID().toString(), LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        storage.create(new Meal(UUID.randomUUID().toString(), LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        storage.create(new Meal(UUID.randomUUID().toString(), LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        storage.create(new Meal(UUID.randomUUID().toString(), LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        storage.create(new Meal(UUID.randomUUID().toString(), LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    public Storage getStorage() {
        return storage;
    }

    public static Singleton getInstance(){
        if (instance == null) instance = new Singleton();
        return instance;
    }
}
