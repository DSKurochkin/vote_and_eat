package ru.dm.projects.vote_and_eat.to;

import java.time.LocalDate;

public class DishTo extends AbstractTo {

    private String name;

    private LocalDate date;

    private Integer price;

    private Long restaurant_id;


    public DishTo() {

    }

    public DishTo(Long id, String name, LocalDate date, Integer price, Long restaurant_id) {
        super(id);
        this.name=name;
        this.date = date;
        this.price = price;
        this.restaurant_id=restaurant_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(Long restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    @Override
    public String toString() {
        return "DishTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
