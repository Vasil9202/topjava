package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.InMemoryImplementation;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.OurCrudInterface;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    OurCrudInterface dao = new InMemoryImplementation();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MealTo> mealTo = MealsUtil.filteredByStreams(((InMemoryImplementation) dao).getListMeal(), LocalTime.MIN, LocalTime.MAX, 2000);
        String idString = request.getParameter("id");
        String idDelete = request.getParameter("idDelete");
        String idAddMeal = request.getParameter("idAddMeal");
        if (idString != null) {
            int id = Integer.parseInt(idString);
            MealTo meal = mealTo.get(id);
            request.setAttribute("meal", meal);
            request.setAttribute("id", id);
            request.getRequestDispatcher("/mealEdit.jsp").forward(request, response);
            return;
        } else if (idDelete != null) {
            int id = Integer.parseInt(idDelete);
            dao.deleteMealById(id);
            mealTo = MealsUtil.filteredByStreams(((InMemoryImplementation) dao).getListMeal(), LocalTime.MIN, LocalTime.MAX, 2000);
        } else if (idAddMeal != null) {
            request.getRequestDispatcher("/mealEdit.jsp").forward(request, response);
            return;
        }
        request.setAttribute("meals", mealTo);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String date = request.getParameter("dateTime");
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        String stringId = request.getParameter("id");
        LocalDateTime dateTime = null;
        if (date != null || date.isEmpty()) {
            dateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        }
        if (stringId == null || stringId.isEmpty()) {
            dao.addMeal(dateTime, description, calories);
        } else {
            int id = Integer.parseInt(stringId);
            dao.updateMealById(id, dateTime, description, calories);
        }
        response.sendRedirect("index.html");
    }
}

