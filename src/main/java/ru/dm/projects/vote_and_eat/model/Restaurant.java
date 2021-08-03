package ru.dm.projects.vote_and_eat.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "restaurants")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Restaurant extends AbstractEntity {


    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    @JsonBackReference
//    @JsonManagedReference
//    @JsonIgnore
    private Set<Dish> dishes;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    @JsonBackReference
    @JsonIgnore
    private Set<Vote> votes;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    public Set<Dish> getDishes() {
        return dishes;
    }

    public  Restaurant(){}

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }
}