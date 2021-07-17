package ru.dm.projects.vote_and_eat.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "dishes")
public class Dish extends AbstractEntity {

    @Column(name = "price", nullable = false)
    @NotNull
    private int price;

    @Column(name="date")
    LocalDate date;


    @NotNull
    @JoinColumn(name = "restaurant_id")
    @ManyToOne
    private Restaurant restaurant;
}
