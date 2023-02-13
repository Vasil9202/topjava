package ru.javawebinar.topjava.repository.inmemory;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMealRepository implements MealRepository {
    private static final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private static final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(this::save);
    }

    @Override
    public synchronized Meal save(Meal meal) {
        if(!Objects.equals(meal.getUserId(), meal.getId()))
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
        return id == repository.get(id).getUserId() && repository.remove(id) != null;
    }

    @Override
    public Meal get(int id) {
        return id == repository.get(id).getUserId() ? repository.get(id) : null;
    }

    @Override
    public Collection<Meal> getAll() {
        return repository.values();
    }
}

