package ru.dm.projects.vote_and_eat.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "dishes")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Dish.class)
public class Dish extends AbstractNamedEntity {

    @ApiModelProperty(notes = "date when the dish will be available for order")
    @Column(name = "date", nullable = false)
    LocalDate date;

    @ApiModelProperty(notes = "price per dish")
    @Column(name = "price", nullable = false)
    private int price;

    @ApiModelProperty(notes = "restaurant serving the dish")
    @JoinColumn(name = "restaurant_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(Long id, String name, LocalDate date, int price, Restaurant restaurant) {
        super(id, name);
        this.date = date;
        this.price = price;
        this.restaurant = restaurant;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name=" + name +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
