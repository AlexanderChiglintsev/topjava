package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(id, SecurityUtil.authUserId());
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(meal, SecurityUtil.authUserId());
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id, SecurityUtil.authUserId());
    }

    public void update(Meal meal, int id) {
        log.info("update {} with id={}", meal, id);
        meal.setUserId(SecurityUtil.authUserId());
        assureIdConsistent(meal, id);
        service.update(meal, SecurityUtil.authUserId());
    }


    public List<MealTo> getAll() {
        log.info("getAll");
        return service.getAll(SecurityUtil.authUserId());
    }

    public List<MealTo> getAllFiltered(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        log.info("getAllFiltered");
        return service.getAllFiltered(startDate, startTime, endDate, endTime, SecurityUtil.authUserId());
    }

    public List<MealTo> getAllFiltered(LocalTime startTime, LocalTime endTime) {
        log.info("getAllFiltered");
        return service.getAllFiltered(LocalDate.MIN, startTime, LocalDate.MAX, endTime, SecurityUtil.authUserId());
    }

    public List<MealTo> getAllFiltered() {
        log.info("getAllFiltered");
        return service.getAll(SecurityUtil.authUserId());
    }
}