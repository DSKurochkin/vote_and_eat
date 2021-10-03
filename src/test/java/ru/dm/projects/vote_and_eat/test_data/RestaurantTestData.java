package ru.dm.projects.vote_and_eat.test_data;

import ru.dm.projects.vote_and_eat.model.Restaurant;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.dm.projects.vote_and_eat.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {

    public static final long FIRST_RESTAURANT_ID = START_SEQ + 4;
    public static final Restaurant restaurant1 = new Restaurant(FIRST_RESTAURANT_ID, "RestaurantA");
    public static final Restaurant restaurant2 = new Restaurant(FIRST_RESTAURANT_ID + 1, "RestaurantB");

    public static List<Restaurant> restaurants = List.of(restaurant1, restaurant2);

    public static Restaurant getNew() {
        return new Restaurant(null, "New Test Restaurant");
    }

    public static <T> void assertRestaurant(T actual, T expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("dishes").isEqualTo(expected);
    }


}
