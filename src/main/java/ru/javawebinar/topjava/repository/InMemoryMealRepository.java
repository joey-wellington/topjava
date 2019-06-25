package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.MealsUtil.generateMeals;
import static java.util.function.Function.identity;

public class InMemoryMealRepository implements MealRepository {
    private Map<Integer, Meal> meals = generateMeals().stream().collect(Collectors.toMap(Meal::getId, identity()));
    private AtomicInteger currentId = new AtomicInteger(meals.size());

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(meals.values());
    }

    @Override
    public void add(Meal meal) {
        int id = currentId.incrementAndGet();
        meal.setId(id);
        meals.put(id, meal);
    }

    @Override
    public void delete(int id) {
        meals.remove(id);
    }

    @Override
    public void update(Meal meal) {
        Meal mealToUpdate = meals.get(meal.getId());
        mealToUpdate.setDateTime(meal.getDateTime());
        mealToUpdate.setDescription(meal.getDescription());
        mealToUpdate.setCalories(meal.getCalories());
        meals.put(meal.getId(), mealToUpdate);
    }

    @Override
    public Meal getById(int id) {
        return meals.get(id);
    }
}
