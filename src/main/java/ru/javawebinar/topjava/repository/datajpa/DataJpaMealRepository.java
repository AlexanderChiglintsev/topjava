package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class DataJpaMealRepository implements MealRepository {

    private final CrudMealRepository mealCrudRepo;
    private final CrudUserRepository userCrudRepo;

    public DataJpaMealRepository(CrudMealRepository mealCrudRepo, CrudUserRepository userCrudRepo) {
        this.mealCrudRepo = mealCrudRepo;
        this.userCrudRepo = userCrudRepo;
    }

    @Transactional
    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew() || get(meal.id(), userId) != null) {
            meal.setUser(userCrudRepo.getOne(userId));
            return mealCrudRepo.save(meal);
        }
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return mealCrudRepo.delete(id, userId) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        Optional<Meal> optionalMeal = mealCrudRepo.findById(id).filter(m -> m.getUser().getId() == userId);
        return optionalMeal.orElse(null);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return mealCrudRepo.getAll(userId);
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return mealCrudRepo.getAllFiltered(
                userId,
                startDateTime,
                endDateTime
        );
    }
}