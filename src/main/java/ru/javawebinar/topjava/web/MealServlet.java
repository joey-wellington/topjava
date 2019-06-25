package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.InMemoryMealRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.CALORIES_PER_DAY;
import static ru.javawebinar.topjava.util.MealsUtil.getWithExcess;
import static ru.javawebinar.topjava.util.TimeUtil.parseLocalDateTime;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private static final String LIST_MEALS = "/meals.jsp";
    private static final String ADD_OR_EDIT = "/meal.jsp";

    private InMemoryMealRepository mealRepository = new InMemoryMealRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("inside doGet meals");

        String forward_path = "";
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        if (action.equalsIgnoreCase("add")) {
            log.debug("add meal");

            forward_path = ADD_OR_EDIT;

        } else if (action.equalsIgnoreCase("delete")) {
            log.debug("delete meal");

            int id = Integer.parseInt(request.getParameter("id"));
            mealRepository.delete(id);
            response.sendRedirect("meals");
            return;

        } else if (action.equalsIgnoreCase("edit")) {
            log.debug("edit meal");

            forward_path = ADD_OR_EDIT;
            int id = Integer.parseInt(request.getParameter("id"));
            Meal meal = mealRepository.getById(id);
            request.setAttribute("meal", meal);

        } else {
            log.debug("list meals");

            forward_path = LIST_MEALS;
            request.setAttribute("meals", getWithExcess(mealRepository.getAll(), CALORIES_PER_DAY));
        }

        request.getRequestDispatcher(forward_path).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal();
        meal.setDescription(request.getParameter("description"));
        try {
            meal.setCalories(Integer.parseInt(request.getParameter("calories")));
            LocalDateTime dateTime = parseLocalDateTime(request.getParameter("dateTime"), "yyyy-MM-dd HH:mm");
            meal.setDateTime(dateTime);

            String id = request.getParameter("id");
            if (id == null || id.isEmpty()) {
                mealRepository.add(meal);
            } else {
                meal.setId(Integer.parseInt(id));
                mealRepository.update(meal);
            }

            request.setAttribute("meals", getWithExcess(mealRepository.getAll(), CALORIES_PER_DAY));
            request.getRequestDispatcher(LIST_MEALS).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
