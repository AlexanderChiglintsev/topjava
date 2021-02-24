package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int NOT_FOUND = 10;
    public static final int FIRST_MEAL = START_SEQ+2;

    public static final Meal meal11 = new Meal(FIRST_MEAL, LocalDateTime.of(2021, Month.FEBRUARY, 10, 10, 0), "Завтрак", 500);
    public static final Meal meal12 = new Meal(START_SEQ+3, LocalDateTime.of(2021, Month.FEBRUARY, 10, 13, 0), "Обед", 1000);
    public static final Meal meal13 = new Meal(START_SEQ+4, LocalDateTime.of(2021, Month.FEBRUARY, 10, 20, 0), "Ужин", 500);
    public static final Meal meal14 = new Meal(START_SEQ+5, LocalDateTime.of(2021, Month.FEBRUARY, 11, 0, 0), "Граница", 100);
    public static final Meal meal15 = new Meal(START_SEQ+6, LocalDateTime.of(2021, Month.FEBRUARY, 11, 10, 0), "Завтрак", 500);
    public static final Meal meal16 = new Meal(START_SEQ+7, LocalDateTime.of(2021, Month.FEBRUARY, 11, 13, 0), "Обед", 1000);
    public static final Meal meal17 = new Meal(START_SEQ+8, LocalDateTime.of(2021, Month.FEBRUARY, 11, 20, 0), "Ужин", 410);

    public static final Meal meal21 = new Meal(START_SEQ+9, LocalDateTime.of(2020, Month.FEBRUARY, 10, 10, 0), "Завтрак", 500);
    public static final Meal meal22 = new Meal(START_SEQ+10, LocalDateTime.of(2020, Month.FEBRUARY, 10, 13, 0), "Обед", 1000);
    public static final Meal meal23 = new Meal(START_SEQ+11, LocalDateTime.of(2020, Month.FEBRUARY, 10, 20, 0), "Ужин", 500);
    public static final Meal meal24 = new Meal(START_SEQ+12, LocalDateTime.of(2020, Month.FEBRUARY, 11, 0, 0), "Граница", 100);
    public static final Meal meal25 = new Meal(START_SEQ+13, LocalDateTime.of(2020, Month.FEBRUARY, 11, 10, 0), "Завтрак", 500);
    public static final Meal meal26 = new Meal(START_SEQ+14, LocalDateTime.of(2020, Month.FEBRUARY, 11, 13, 0), "Обед", 1000);
    public static final Meal meal27 = new Meal(START_SEQ+15, LocalDateTime.of(2020, Month.FEBRUARY, 11, 20, 0), "Ужин", 410);

    public static Meal getNew() {
        return new Meal(LocalDateTime.of(2021, Month.JANUARY, 31, 23, 0), "Test eat", 777);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(meal11);
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