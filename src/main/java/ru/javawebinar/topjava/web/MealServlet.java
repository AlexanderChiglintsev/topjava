package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    private ConfigurableApplicationContext appCtx;
    private MealRestController mealController;

    @Override
    public void init() {
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        mealController = appCtx.getBean(MealRestController.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");

        Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories"))
        );

        log.info(meal.isNew() ? "Create {}" : "Update {}", meal);
        if (id.isEmpty()) {
            mealController.create(meal);
        } else {
            mealController.update(meal, meal.getId());
        }
        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                log.info("Delete {}", id);
                mealController.delete(id);
                response.sendRedirect("meals");
                break;
            case "create":
            case "update":
                final Meal meal = "create".equals(action) ?
                        new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        mealController.get(getId(request));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
                break;
            case "filtered":
                log.info("getAllFiltered");
                String dBegin = request.getParameter("dateBegin");
                String dEnd = request.getParameter("dateEnd");
                String tBegin = request.getParameter("timeBegin");
                String tEnd = request.getParameter("timeEnd");
                LocalDate dateBegin = dBegin.isEmpty() ? null : LocalDate.parse(request.getParameter("dateBegin"));
                LocalDate dateEnd = dEnd.isEmpty() ? null : LocalDate.parse(request.getParameter("dateEnd"));
                LocalTime timeBegin = tBegin.isEmpty() ? null : LocalTime.parse(request.getParameter("timeBegin"));
                LocalTime timeEnd = tEnd.isEmpty() ? null : LocalTime.parse(request.getParameter("timeEnd"));
                request.setAttribute("meals",
                        mealController.getAllFiltered(dateBegin, timeBegin, dateEnd, timeEnd));
                request.setAttribute("userid", SecurityUtil.authUserId());
                request.setAttribute("dateBegin", dBegin);
                request.setAttribute("dateEnd", dEnd);
                request.setAttribute("timeBegin", tBegin);
                request.setAttribute("timeEnd", tEnd);
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
            case "all":
            default:
                log.info("getAll");
                request.setAttribute("meals",
                        mealController.getAll());
                request.setAttribute("userid", SecurityUtil.authUserId());
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

    @Override
    public void destroy() {
        appCtx.close();
        super.destroy();
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
