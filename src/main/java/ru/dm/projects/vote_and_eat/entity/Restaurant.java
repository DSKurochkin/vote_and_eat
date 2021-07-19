package ru.dm.projects.vote_and_eat.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractEntity {


    @OneToMany(mappedBy = "restaurant")
//    @JsonBackReference
    @JsonIgnore
    private Set<Dish> dishes;


    //uni or be directional??
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    @NotNull
//    @JsonBackReference
    @JsonIgnore
    private Admin admin;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    @JsonBackReference
    @JsonIgnore
    private Set<Vote> votes;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }
}