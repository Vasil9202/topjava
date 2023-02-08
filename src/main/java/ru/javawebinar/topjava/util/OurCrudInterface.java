package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;

public interface OurCrudInterface {
    void updateMealById(Integer id, LocalDateTime localDateTime, String description, Integer calories);

    void deleteMealById(int id);

    void addMeal(LocalDateTime localDateTime, String description, Integer calories);
}
