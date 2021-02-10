package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.MealMapStorage;
import ru.javawebinar.topjava.storage.Storage;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    private Storage<Meal, String> storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = new MealMapStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        action = (action != null ? action : "");
        switch (action) {
            case "add":
                log.debug("Action = add");
                Meal newMeal = new Meal(
                        "new",
                        LocalDateTime.parse(request.getParameter("date")),
                        request.getParameter("description"),
                        Integer.parseInt(request.getParameter("calories"))
                );
                if (storage.create(newMeal) == null) log.error("Can't add new meal!");
                response.sendRedirect("meals");
                return;
            case "update":
                log.debug("Action = update");
                Meal updatedMeal = new Meal(
                        request.getParameter("id"),
                        LocalDateTime.parse(request.getParameter("date")),
                        request.getParameter("description"),
                        Integer.parseInt(request.getParameter("calories"))
                );
                if (storage.update(updatedMeal) == null) log.error("Can't update meal!");
                response.sendRedirect("meals");
                return;
            default:
                log.debug("Not correct action, Forward to list of meal (meals.jsp)");
                getMealsList(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        action = (action != null ? action : "");
        switch (action) {
            case "delete":
                log.debug("Action = delete");
                storage.delete(request.getParameter("id"));
                response.sendRedirect("meals");
                return;
            case "edit":
                log.debug("Action = edit");
                request.setAttribute("meal", storage.read(request.getParameter("id")));
                request.getRequestDispatcher("editMeal.jsp").forward(request, response);
                return;
            case "addform":
                log.debug("Action = addform");
                request.getRequestDispatcher("addMeal.jsp").forward(request, response);
                return;
            default:
                request.setAttribute("list", MealsUtil.filteredByStreams(
                        storage.getAll(),
                        LocalTime.MIN, LocalTime.MAX, MealsUtil.LIMIT_CALORIES)
                );
                log.debug("Forward to list of meals (meals.jsp)");
                getMealsList(request, response);
        }
    }

    private void getMealsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("list", MealsUtil.filteredByStreams(
                storage.getAll(),
                LocalTime.MIN, LocalTime.MAX, MealsUtil.LIMIT_CALORIES)
        );
        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }
}
