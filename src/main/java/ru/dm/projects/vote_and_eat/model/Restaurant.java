package ru.dm.projects.vote_and_eat.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurants")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Restaurant extends AbstractEntity {


    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    @JsonBackReference
//    @JsonManagedReference
//    @JsonIgnore
    private List<Dish> dishes;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    @JsonBackReference
    @JsonIgnore
    private List<Vote> votes;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    public List<Dish> getDishes() {
        return dishes;
    }

    //    public Restaurant(Restaurant restaurant)
    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public void addVoteToRestaurant(Vote vote) {
        if (votes == null) {
            votes = new ArrayList<>();
        }
        votes.add(vote);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dishes=" + dishes +
                ", votes=" + votes +
                '}';
    }
}