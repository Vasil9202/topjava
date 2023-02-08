package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class MealTo {
    private  AtomicInteger id = new AtomicInteger();
    private static AtomicInteger idCount = new AtomicInteger();
    private final LocalDateTime dateTime;


    private final String description;

    private final int calories;

    private final boolean excess;

    public MealTo(LocalDateTime dateTime, String description, int calories, boolean excess) {
        this.id = idCount;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
        idCount.incrementAndGet();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public AtomicInteger getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExcess() {
        return excess;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + excess +
                '}';
    }
}
