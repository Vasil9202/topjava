package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import javax.jws.WebService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.filteredByStreams;

@WebService(wsdlLocation = "/topjava/meals")
public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    private List<Meal> listMeal = Arrays.asList(
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
    );

    public void deleteMealById(int id) {
        listMeal.remove(id);
    }

    public void editMealById(Integer id, LocalDateTime localDateTime, String description, Integer calories) {
        if (id == null) return;
        if (localDateTime != null)
            listMeal.get(id).setDateTime(localDateTime);
        if (description != null)
            listMeal.get(id).setDescription(description);
        if (calories != null)
            listMeal.get(id).setCalories(calories);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.debug("redirect to users");
        request.setAttribute("meals", listMeal);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
        response.sendRedirect("meals.jsp");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);
        LocalDateTime dateTime = null;
        //if(request.getParameter("dateTime") != null){
        //dateTime = LocalDateTime.parse(request.getParameter("dateTime"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));}
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        editMealById(id, dateTime, description, calories);
        response.sendRedirect("index.html");

    }
}

