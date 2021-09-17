package ru.dm.projects.vote_and_eat.util;

import ru.dm.projects.vote_and_eat.model.Dish;
import ru.dm.projects.vote_and_eat.model.Restaurant;
import ru.dm.projects.vote_and_eat.to.DishTo;
import ru.dm.projects.vote_and_eat.util.exception.UnsupportedTimeOperationException;

import java.time.LocalTime;

import static ru.dm.projects.vote_and_eat.util.DateTimeUtil.now;
import static ru.dm.projects.vote_and_eat.util.DateTimeUtil.today;

public class DishUtil {

    public static void checkPossibilityOfAction(DishTo dishTo, LocalTime endOfChangeDish) {
        String message;
        if (now().isAfter(endOfChangeDish) && dishTo.getDate().isEqual(today())) {
            throw new UnsupportedTimeOperationException("now is unsupported time for change dish");
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

    public static DishTo asTo(Dish dish) {
        return new DishTo(dish.getId(), dish.getName(), dish.getDate(), dish.getPrice(), dish.getRestaurant().id());
    }
}
