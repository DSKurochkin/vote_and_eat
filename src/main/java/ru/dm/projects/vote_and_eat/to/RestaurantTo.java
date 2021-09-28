package ru.dm.projects.vote_and_eat.to;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ru.dm.projects.vote_and_eat.model.Restaurant;

@ApiModel(value = "Restaurant transfer object"
        , description = "Object sent from the client's in the reports on the number of votes for the restaurant ")
public class RestaurantTo extends AbstractTo {
    @ApiModelProperty(notes = "restaurant's name.")
    private String restaurantName;
    @ApiModelProperty(notes = "true - restaurant exist now, false - was deleted")
    private boolean exist;

    public RestaurantTo(Long id, String restaurantName) {
        super(id);
        this.restaurantName = restaurantName;
        this.exist = true;
    }

    public RestaurantTo(Long id, String restaurantName, boolean isExist) {
        new RestaurantTo(id, restaurantName);
        this.exist = true;
    }

    public RestaurantTo(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.restaurantName = restaurant.getName();
        this.exist = true;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", restaurantName='" + restaurantName + '\'' +
                ", exist=" + exist +
                '}';
    }
}
