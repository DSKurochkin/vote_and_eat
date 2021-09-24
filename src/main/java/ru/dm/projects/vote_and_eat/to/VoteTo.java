package ru.dm.projects.vote_and_eat.to;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(value = "Vote transfer object", description = "Object sent from the client's side to make a vote")
public class VoteTo {
    @ApiModelProperty(notes = "restaurant id for which the vote was cast. NotNull")
    @NotNull
    private Long restaurant_id;

    public VoteTo() {
    }

    public VoteTo(Long restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public Long getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(Long restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

}
