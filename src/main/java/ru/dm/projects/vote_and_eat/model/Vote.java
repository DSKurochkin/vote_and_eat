package ru.dm.projects.vote_and_eat.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "votes")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Vote.class)
public class Vote extends AbstractBaseEntity {

    @ApiModelProperty(notes = "voting date, cannot be in the past")
    @Column(name = "date", nullable = false)
    @NotNull
    @FutureOrPresent
    private LocalDate date;

    @ApiModelProperty(notes = "voting time, cannot be in the past")
    @NotNull
    @FutureOrPresent
    @Column(name = "time", nullable = false)
    private LocalTime time;

    @ApiModelProperty(notes = "The restaurant for which the vote was cast")
    @JoinColumn(name = "restaurant_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Restaurant restaurant;

    @ApiModelProperty(notes = "Voted user ")
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;


    public Vote() {
    }

    public Vote(Long id, LocalDate date, LocalTime time, Restaurant restaurant, User user) {
        super(id);
        this.date = date;
        this.time = time;
        this.restaurant = restaurant;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
