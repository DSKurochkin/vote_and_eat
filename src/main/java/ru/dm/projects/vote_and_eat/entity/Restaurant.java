package ru.dm.projects.vote_and_eat.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractEntity {


    @OneToMany(mappedBy = "restaurant")
    private Set<Dish> dishes;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}