package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int NOT_FOUND = 10;
    public static final int FIRST_USER_MEAL_ID = START_SEQ + 2;

    public static final Meal user_meal1 = new Meal(FIRST_USER_MEAL_ID, LocalDateTime.of(2021, Month.FEBRUARY, 10, 10, 0), "Завтрак", 500);
    public static final Meal user_meal2 = new Meal(START_SEQ + 3, LocalDateTime.of(2021, Month.FEBRUARY, 10, 13, 0), "Обед", 1000);
    public static final Meal user_meal3 = new Meal(START_SEQ + 4, LocalDateTime.of(2021, Month.FEBRUARY, 10, 20, 0), "Ужин", 500);
    public static final Meal user_meal4 = new Meal(START_SEQ + 5, LocalDateTime.of(2021, Month.FEBRUARY, 11, 0, 0), "Граница", 100);
    public static final Meal user_meal5 = new Meal(START_SEQ + 6, LocalDateTime.of(2021, Month.FEBRUARY, 11, 10, 0), "Завтрак", 500);
    public static final Meal user_meal6 = new Meal(START_SEQ + 7, LocalDateTime.of(2021, Month.FEBRUARY, 11, 13, 0), "Обед", 1000);
    public static final Meal user_meal7 = new Meal(START_SEQ + 8, LocalDateTime.of(2021, Month.FEBRUARY, 11, 20, 0), "Ужин", 410);
    public static final Meal user_meal8 = new Meal(START_SEQ + 9, LocalDateTime.of(2021, Month.FEBRUARY, 12, 10, 0), "Завтрак", 500);
    public static final Meal user_meal9 = new Meal(START_SEQ + 10, LocalDateTime.of(2021, Month.FEBRUARY, 12, 13, 0), "Обед", 1000);
    public static final Meal user_meal10 = new Meal(START_SEQ + 11, LocalDateTime.of(2021, Month.FEBRUARY, 12, 20, 0), "Ужин", 410);

    public static final Meal admin_meal1 = new Meal(START_SEQ + 12, LocalDateTime.of(2020, Month.FEBRUARY, 10, 10, 0), "Завтрак", 500);
    public static final Meal admin_meal2 = new Meal(START_SEQ + 13, LocalDateTime.of(2020, Month.FEBRUARY, 10, 13, 0), "Обед", 1000);
    public static final Meal admin_meal3 = new Meal(START_SEQ + 14, LocalDateTime.of(2020, Month.FEBRUARY, 10, 20, 0), "Ужин", 500);
    public static final Meal admin_meal4 = new Meal(START_SEQ + 15, LocalDateTime.of(2020, Month.FEBRUARY, 11, 0, 0), "Граница", 100);
    public static final Meal admin_meal5 = new Meal(START_SEQ + 16, LocalDateTime.of(2020, Month.FEBRUARY, 11, 10, 0), "Завтрак", 500);
    public static final Meal admin_meal6 = new Meal(START_SEQ + 17, LocalDateTime.of(2020, Month.FEBRUARY, 11, 13, 0), "Обед", 1000);
    public static final Meal admin_meal7 = new Meal(START_SEQ + 18, LocalDateTime.of(2020, Month.FEBRUARY, 11, 20, 0), "Ужин", 410);

    public static Meal getNew() {
        return new Meal(LocalDateTime.of(2021, Month.JANUARY, 31, 23, 0), "Test eat", 777);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(user_meal1);
        updated.setDateTime(LocalDateTime.of(2021, Month.JANUARY, 1, 23, 0));
        updated.setDescription("Test description");
        updated.setCalories(999);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}