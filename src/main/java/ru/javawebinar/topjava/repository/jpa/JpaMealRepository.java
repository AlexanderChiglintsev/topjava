package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepository implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            User ref = em.getReference(User.class, userId);
            meal.setUser(ref);
            em.persist(meal);
        } else {
            //Alternative way
            /*Meal updatedMeal = em.find(Meal.class, meal.getId());
            return updatedMeal.getUser().getId() == userId ? em.merge(meal) : null;*/

            Query query = em.createNamedQuery(Meal.UPDATE)
                    .setParameter("dt", meal.getDateTime())
                    .setParameter("description", meal.getDescription())
                    .setParameter("calories", meal.getCalories())
                    .setParameter("mealId", meal.getId())
                    .setParameter("userId", userId);
            return query.executeUpdate() != 0 ? meal : null;
        }
        return meal;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        Query query = em.createNamedQuery(Meal.DELETE)
                .setParameter("mealId", id)
                .setParameter("userId", userId);
        return query.executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal m = em.find(Meal.class, id);
        if (m != null && m.getUser().getId() == userId) {
            return m;
        }
        return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class).setParameter("userId", userId).getResultList();
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return em.createNamedQuery(Meal.ALL_FILTERED, Meal.class)
                .setParameter("userId", userId)
                .setParameter("startDate", startDateTime)
                .setParameter("endDate", endDateTime)
                .getResultList();
    }
}