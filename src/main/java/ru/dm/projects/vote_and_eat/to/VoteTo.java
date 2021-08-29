package ru.dm.projects.vote_and_eat.to;

public class VoteTo {

    private Long restaurant_id;

    public VoteTo() {
    }

    public VoteTo(Long restaurant_id, Long user_id) {
        this.restaurant_id = restaurant_id;
           }

    public Long getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(Long restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

}
