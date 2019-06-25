package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealRepository {

    public List<Meal> getAll();

    public void add(Meal meal);

    public void delete(int id);

    public void update(Meal meal);

    public Meal getById(int id);
}
