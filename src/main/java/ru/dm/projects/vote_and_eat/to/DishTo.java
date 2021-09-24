package ru.dm.projects.vote_and_eat.to;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@ApiModel(value = "Dish transfer object", description = "Object sent from the client's side to create or update dish")
public class DishTo extends AbstractTo {

    @ApiModelProperty(notes = "dish name. may be between 2 to 50 characters and notblank")
    @NotBlank()
    @Size(min = 2, max = 50)
    private String name;

    @ApiModelProperty(notes = "date when the dish will be available for order")
    @NotNull
    @FutureOrPresent(message = "dish can only be the current or the latest date")
    private LocalDate date;

    @ApiModelProperty(notes = "price per dish. min value = 1, max = 100")
    @NotNull
    @Range(min = 1, max = 100)
    private Integer price;

    @ApiModelProperty(notes = "id of restaurant serving the dish. NotNull")
    @NotNull
    private Long restaurant_id;


    public DishTo() {

    }

    public DishTo(Long id, String name, LocalDate date, Integer price, Long restaurant_id) {
        super(id);
        this.name = name;
        this.date = date;
        this.price = price;
        this.restaurant_id = restaurant_id;
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
