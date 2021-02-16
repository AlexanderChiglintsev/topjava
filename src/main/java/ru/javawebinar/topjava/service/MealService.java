package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final MealRepository repository;
    private final UserService userService;

    public MealService(MealRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public Meal create(Meal meal, Integer userId) {
        return repository.save(meal, userId);
    }

    public void delete(int id, Integer userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public Meal get(int id, Integer userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public void update(Meal meal, Integer userId) {
        checkNotFoundWithId(repository.save(meal, userId), meal.getId());
    }

    public List<MealTo> getAll(Integer userId) {
        return MealsUtil.getFilteredTos(
                repository.getAll(userId),
                userService.get(userId).getCaloriesPerDay(),
                LocalTime.MIN,
                LocalTime.MAX);
    }

    public List<MealTo> getAllFiltered(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, Integer userId) {
        return MealsUtil.getFilteredTos(
                repository.getAllFiltered(startDate, endDate, userId),
                userService.get(userId).getCaloriesPerDay(),
                startTime,
                endTime);
    }
}