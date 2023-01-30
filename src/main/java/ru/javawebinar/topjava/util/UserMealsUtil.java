package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExcess> listExcess = new ArrayList<>();

        Map<Integer,Integer> map = new HashMap<>();
        for(UserMeal meal : meals){
            int dayOfMonth = meal.getDateTime().toLocalDate().getDayOfYear();
            if(map.containsKey(dayOfMonth))
            map.put(dayOfMonth,map.get(dayOfMonth) + meal.getCalories());
            else
                map.put(dayOfMonth,meal.getCalories());
        }
        for(Map.Entry<Integer,Integer> entry : map.entrySet())
            if(entry.getValue() > caloriesPerDay){
        for(UserMeal meal : meals){
            int dayOfMonth = meal.getDateTime().toLocalDate().getDayOfYear();
            LocalTime mealTime = meal.getDateTime().toLocalTime();
            if(dayOfMonth == entry.getKey() && TimeUtil.isBetweenHalfOpen(mealTime,startTime,endTime)){
                listExcess.add(new UserMealWithExcess(meal.getDateTime(), meal.getDescription(),meal.getCalories(),true));
            }
        }
            }

        return listExcess;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<Integer, Integer> map = meals.stream()
                .collect(Collectors.groupingBy(meal -> meal.getDateTime().toLocalDate().getDayOfYear(),
                        Collectors.summingInt(UserMeal::getCalories)));
        return meals.stream()
                .filter(meal -> map.get(meal.getDateTime().toLocalDate().getDayOfYear()) > caloriesPerDay)
                .filter(meal -> TimeUtil.isBetweenHalfOpen(meal.getDateTime().toLocalTime(), startTime, endTime))
                .map(meal -> new UserMealWithExcess(meal.getDateTime(), meal.getDescription(), meal.getCalories(), true))
                .collect(Collectors.toList());
    }
}
