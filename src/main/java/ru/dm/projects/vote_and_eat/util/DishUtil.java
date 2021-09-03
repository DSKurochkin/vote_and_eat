package ru.dm.projects.vote_and_eat.util;

import ru.dm.projects.vote_and_eat.model.Dish;
import ru.dm.projects.vote_and_eat.model.Restaurant;
import ru.dm.projects.vote_and_eat.to.DishTo;

import java.time.LocalDate;
import java.time.LocalTime;

import static ru.dm.projects.vote_and_eat.util.DateTimeUtil.getNow;
import static ru.dm.projects.vote_and_eat.util.DateTimeUtil.getToday;

public class DishUtil {

    public static void checkPossibilityOfAction(DishTo dishTo, LocalTime endOfChangeDish) {

        if (dishTo.getDate().isBefore(LocalDate.now())
                || (getNow().isAfter(endOfChangeDish)&&dishTo.getDate().isEqual(getToday()))) {
            throw new RuntimeException("it's to late to update dish");
        }
    }
    public static Dish createNewFromTo(DishTo dishTo, Restaurant restaurant) {
        return new Dish(null, dishTo.getName(), dishTo.getDate(), dishTo.getPrice(), restaurant);
    }
    public static Dish updateFromTo(Dish dish, DishTo dishTo) {
        dish.setName(dishTo.getName());
        dish.setPrice(dishTo.getPrice());
        dish.setDate(dishTo.getDate());
        return dish;
    }

    public static DishTo asTo(Dish dish){
        return new DishTo(dish.getId(), dish.getName(), dish.getDate(), dish.getPrice(), dish.getRestaurant().id());
    }
}
