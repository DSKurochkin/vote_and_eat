package ru.dm.projects.vote_and_eat.controller;

import ru.dm.projects.vote_and_eat.model.Restaurant;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {

    public static final Integer FIRST_RESTAURANT_ID=1;
    public static final Restaurant restaurant1 = new Restaurant(FIRST_RESTAURANT_ID, "Kurskiy");
    public static final Restaurant restaurant2 = new Restaurant(FIRST_RESTAURANT_ID+1, "Tsarskiy");
    public static final Restaurant restaurant3 = new Restaurant(FIRST_RESTAURANT_ID+2, "Krusty Krab");

    public static List<Restaurant> restaurants= List.of(restaurant1,restaurant2,restaurant3);

    public static Restaurant getNew(){
        return new Restaurant(null, "New Test Restaurant");
    }

    public static <T>  void assertRestaurant(T actual, T expected){
        assertThat(actual).usingRecursiveComparison().ignoringFields("dishes", "votes").isEqualTo(expected);
    }


}
