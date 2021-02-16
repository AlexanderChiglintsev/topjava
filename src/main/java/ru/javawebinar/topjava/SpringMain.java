package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalTime;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management (ARM)
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            System.out.println("Users:");
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ADMIN));
            adminUserController.create(new User(null, "Ivan", "test1@test.tt", "3", Role.USER));
            adminUserController.create(new User(null, "Alex", "test2@test.tt", "3", Role.ADMIN));
            adminUserController.create(new User(null, "John", "test3@test.tt", "3", Role.USER));
            adminUserController.create(new User(null, "Elisabeth", "test4@test.tt", "3", Role.ADMIN));
            adminUserController.create(new User(null, "Lawrence", "test5@test.tt", "3", Role.USER));
            adminUserController.getAll().forEach(System.out::println);
            MealRestController mealController = appCtx.getBean(MealRestController.class);
            System.out.println("Meals:");
            mealController.getAll().forEach(System.out::println);
            System.out.println("Filtered meals:");
            mealController.getAllFiltered(LocalTime.of(9, 0), LocalTime.of(14, 0)).forEach(System.out::println);
            System.out.println("Edit meals:");
            Meal oldMeal = mealController.get(2);
            Meal newMeal = new Meal(oldMeal.getId(), oldMeal.getUserId(), oldMeal.getDateTime(), oldMeal.getDescription(), 1200);
            mealController.update(newMeal, 2);
            System.out.println("Filtered meals after update:");
            mealController.getAllFiltered(LocalTime.of(9, 0), LocalTime.of(14, 0)).forEach(System.out::println);
            System.out.println("Get meal of another user:");
            mealController.get(5);
        }
    }
}
