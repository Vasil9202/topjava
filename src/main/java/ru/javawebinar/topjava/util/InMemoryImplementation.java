package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InMemoryImplementation implements OurCrudInterface{
    private List<Meal> listMeal;

    public InMemoryImplementation() {
        listMeal = new ArrayList<>(Arrays.asList(
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        ));

    }

    @Override
    public void updateMealById(Integer id, LocalDateTime localDateTime, String description, Integer calories) {
            if (id == null) return;
            if (localDateTime != null)
                listMeal.get(id).setDateTime(localDateTime);
            if (description != null)
                listMeal.get(id).setDescription(description);
            if (calories != null)
                listMeal.get(id).setCalories(calories);
        }

    public List<Meal> getListMeal() {
        return listMeal;
    }

    @Override
    public void deleteMealById(int id) {
            listMeal.remove(id);
    }

    @Override
    public void addMeal(LocalDateTime localDateTime, String description, Integer calories) {
        listMeal.add(new Meal(localDateTime,description,calories));
    }
}
