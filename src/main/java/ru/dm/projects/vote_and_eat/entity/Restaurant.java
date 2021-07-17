package ru.dm.projects.vote_and_eat.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractEntity {


    @OneToMany(mappedBy = "restaurant")
    private Set<Dish> dishes;


    //uni or be directional??
    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;
    @OneToMany(mappedBy = "user")
    private Set<Vote> votes;

    @OneToMany(mappedBy = "restaurant")
    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }
}