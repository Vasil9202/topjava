package ru.javawebinar.topjava.repository.inmemory;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepository implements MealRepository {
    private static final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private static final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(this::save);
    }

    @Override
    public synchronized Meal save(Meal meal) {
        if(!Objects.equals(meal.getUserId(), SecurityUtil.authUserId()))
            return null;
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public  boolean delete(int id) {
        return SecurityUtil.authUserId() == repository.get(id).getUserId() && repository.remove(id) != null;
    }

    @Override
    public Meal get(int id) {
        return SecurityUtil.authUserId() == repository.get(id).getUserId() ? repository.get(id) : null;
    }

    @Override
    public Collection<Meal> getAll() {
        return repository.values().stream().sorted(Comparator.comparing(Meal::getDate).reversed())
                .filter(meal -> meal.getUserId().equals(SecurityUtil.authUserId())).collect(Collectors.toList());
    }
}

