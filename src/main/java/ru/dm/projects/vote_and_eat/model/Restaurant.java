package ru.dm.projects.vote_and_eat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    @ApiModelProperty(notes = "dishes served in the restaurant")
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Dish> dishes;

    public Restaurant() {
    }

    public Restaurant(Long id, String name) {
        super(id, name);
    }

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dishes=" + dishes +
                '}';
    }
}