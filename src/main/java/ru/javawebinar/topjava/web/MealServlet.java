package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MapStorage;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Storage;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    Storage storage;

    @Override
    public void init() throws ServletException {
        storage = new MapStorage();
        storage.create(new Meal(UUID.randomUUID().toString(), LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        storage.create(new Meal(UUID.randomUUID().toString(), LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        storage.create(new Meal(UUID.randomUUID().toString(), LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        storage.create(new Meal(UUID.randomUUID().toString(), LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        storage.create(new Meal(UUID.randomUUID().toString(), LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        storage.create(new Meal(UUID.randomUUID().toString(), LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        storage.create(new Meal(UUID.randomUUID().toString(), LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        System.out.println(storage.getAll());
        request.setAttribute("list", MealsUtil.filteredByStreams(
                storage.getAll(),
                LocalTime.of(0, 0), LocalTime.of(23, 59), 2000)
        );
        log.debug("Redirect to meals.jsp");
        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }
}
