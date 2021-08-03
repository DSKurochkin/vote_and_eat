package ru.dm.projects.vote_and_eat.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "date", nullable = false, columnDefinition = "timestamp default now()")
    private LocalDate date;

    @Column(name = "time", nullable = false, columnDefinition = "timestamp default now()")
    private LocalTime time;

    @JoinColumn(name = "restaurant_id")
    @ManyToOne(fetch = FetchType.EAGER)
//    @JsonManagedReference
    @JsonIgnore
    private Restaurant restaurant;


    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonBackReference
    @JsonIgnore
    private User user;


    public Vote() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public void setAdmin(User user) {
        this.user = user;
    }
}
