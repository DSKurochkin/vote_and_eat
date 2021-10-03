package ru.dm.projects.vote_and_eat.test_data;

import ru.dm.projects.vote_and_eat.model.Dish;
import ru.dm.projects.vote_and_eat.model.Restaurant;
import ru.dm.projects.vote_and_eat.to.DishTo;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.dm.projects.vote_and_eat.model.AbstractBaseEntity.START_SEQ;
import static ru.dm.projects.vote_and_eat.test_data.RestaurantTestData.*;
import static ru.dm.projects.vote_and_eat.util.DateTimeUtil.getDate;
import static ru.dm.projects.vote_and_eat.util.DateTimeUtil.today;
import static ru.dm.projects.vote_and_eat.util.DishUtil.asTo;

public class DishTestData {
    public static final long FIRST_DISH_ID = START_SEQ + 6;
    public static final Dish dish1
            = new Dish(FIRST_DISH_ID, "A_dish first", getDate("2021-09-01"), 80, restaurant1);
    public static final Dish dish2
            = new Dish(FIRST_DISH_ID + 1, "A_dish second", getDate("2021-09-01"), 70, restaurant1);
    public static final Dish dish3
            = new Dish(FIRST_DISH_ID + 2, "A_kompot", getDate("2021-09-01"), 5, restaurant1);

    public static final Dish dish4
            = new Dish(FIRST_DISH_ID + 3, "B_dish first", getDate("2021-09-01"), 60, restaurant2);
    public static final Dish dish5
            = new Dish(FIRST_DISH_ID + 4, "B_dish second", getDate("2021-09-01"), 50, restaurant2);
    public static final Dish dish6
            = new Dish(FIRST_DISH_ID + 5, "B_ice_cream", getDate("2021-09-01"), 10, restaurant2);
    public static final Dish dish7
            = new Dish(FIRST_DISH_ID + 6, "A_2nd Sep", getDate("2021-09-02"), 98, restaurant2);
    public static final Dish dish8
            = new Dish(FIRST_DISH_ID + 7, "B_2nd Sep", getDate("2021-09-02"), 99, restaurant2);
    private static final DishTo dishTo = new DishTo(null, "Test Dish", today(), 50, restaurant1.id());
    public static DishTo updated = new DishTo(FIRST_DISH_ID, "Test Dish", today(), 10, FIRST_RESTAURANT_ID);
    public static Map<String, List<DishTo>> testMenu = new TreeMap<>();

    static {
        testMenu.put(restaurant1.getName(), List.of(asTo(dish1), asTo(dish2), asTo(dish3)));
        testMenu.put(restaurant2.getName(), List.of(asTo(dish4), asTo(dish5), asTo(dish6)));
    }

    public static Dish getNew(String name) {
        return new Dish(null, name, today(), 33, restaurant1);
    }

    public static <T> void assertDish(T actual, T expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("restaurant").isEqualTo(expected);
    }

    public static Dish createFromTo(DishTo dishTo, Restaurant restaurant) {
        return new Dish(null, dishTo.getName(), dishTo.getDate(), dishTo.getPrice(), restaurant);
    }

    public static DishTo getDishTo() {
        if (dishTo.getId() != null) {
            dishTo.setId(null);
        }
        return dishTo;
    }

}
