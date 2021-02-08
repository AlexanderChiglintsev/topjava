package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Singleton;
import ru.javawebinar.topjava.model.Storage;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Singleton.getInstance().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        switch (action) {
            case "view":
                getListMeals(request, response);
                break;
            case "delete":
                storage.delete(request.getParameter("id"));
                getListMeals(request, response);
                break;
            case "add":
                storage.create(new Meal(
                        UUID.randomUUID().toString(),
                        LocalDateTime.parse(request.getParameter("date")),
                        request.getParameter("description"),
                        Integer.parseInt(request.getParameter("calories"))

                ));
                getListMeals(request, response);
                break;
        }

    }

    private void getListMeals(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("list", MealsUtil.filteredByStreams(
                storage.getAll(),
                LocalTime.of(0, 0), LocalTime.of(23, 59), 2000)
        );
        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }
}
