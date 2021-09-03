package ru.dm.projects.vote_and_eat.test_data;

import ru.dm.projects.vote_and_eat.model.Dish;
import ru.dm.projects.vote_and_eat.model.Restaurant;
import ru.dm.projects.vote_and_eat.to.DishTo;

import static ru.dm.projects.vote_and_eat.test_data.RestaurantTestData.*;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class DishTestData {
    public static final Long FIRST_DISH_ID =1L;
    public static final Dish dish1
            = new Dish(FIRST_DISH_ID, "A_dish first", LocalDate.parse("2021-07-16"),80, restaurant1);
    public static final Dish dish2
            = new Dish(FIRST_DISH_ID+1, "A_dish second", LocalDate.parse("2021-07-17"),70, restaurant1);
    public static final Dish dish3
            = new Dish(FIRST_DISH_ID+2, "A_kompot", LocalDate.parse("2021-07-16"),5, restaurant1);

    public static final Dish dish4
            = new Dish(FIRST_DISH_ID+3, "B_dish first", LocalDate.parse("2021-07-17"),60, restaurant2);
    public static final Dish dish5
            = new Dish(FIRST_DISH_ID+4, "B_dish second", LocalDate.parse("2021-07-16"),50, restaurant2);
    public static final Dish dish6
            = new Dish(FIRST_DISH_ID+5, "B_ice_cream", LocalDate.parse("2021-07-16"),10, restaurant2);


    public static Dish getNew(){
        return new Dish(null, "New Test Dish", LocalDate.parse("2022-07-18"), 33, restaurant1);
    }

    public static <T>  void assertDish(T actual, T expected){
        assertThat(actual).usingRecursiveComparison().ignoringFields("restaurant").isEqualTo(expected);
    }

    public static Dish createFromTo(DishTo dishTo, Restaurant restaurant){
        return new Dish(null, dishTo.getName(), dishTo.getDate(), dishTo. getPrice(), restaurant);

    }


}
