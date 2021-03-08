package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMealRepository implements MealRepository {

    private final CrudMealRepository crudRepository;
    private final CrudUserRepository userCrudRepo;

    public DataJpaMealRepository(CrudMealRepository crudRepository, CrudUserRepository userCrudRepo) {
        this.crudRepository = crudRepository;
        this.userCrudRepo = userCrudRepo;
    }

    @Override
    public Meal save(Meal meal, int userId) {
        meal.setUser(userCrudRepo.getOne(userId));
        if (meal.isNew()) {
            return crudRepository.save(meal);
        } else {
            return get(meal.id(), userId) != null ? crudRepository.save(meal) : null;
        }
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudRepository.delete(id, userId) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal = crudRepository.findById(id).orElse(null);
        return (meal != null && meal.getUser().getId() == userId) ? meal : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudRepository.getAll(userId);
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return crudRepository.getAllFiltered(
                userId,
                startDateTime,
                endDateTime
        );
    }
}
