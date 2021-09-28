package ru.dm.projects.vote_and_eat.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "votes")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Vote.class)
public class Vote extends AbstractBaseEntity {

    @ApiModelProperty(notes = "voting date")
    @Column(name = "date")
    private LocalDate date;

    @ApiModelProperty(notes = "voting time")
    @Column(name = "time")
    private LocalTime time;

    @ApiModelProperty(notes = "Restaurant's id")
    @Column(name = "restaurant_id")
    private Long restaurantId;

    @ApiModelProperty(notes = "Restaurant's name")
    @Column(name = "restaurant_name")
    private String restaurantName;

    @ApiModelProperty(notes = "User's id")
    @Column(name = "user_id")
    private Long userId;

    @ApiModelProperty(notes = "User's email")
    @Column(name = "user_email")
    private String userEmail;

    public Vote() {
    }

    public Vote(Long id, LocalDate date, LocalTime time, Long restaurantId, String restaurantName, Long userId, String userEmail) {
        super(id);
        this.date = date;
        this.time = time;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.userId = userId;
        this.userEmail = userEmail;
    }

    public Vote(Long id, LocalDate date, LocalTime time, Restaurant restaurant, User user) {
        super(id);
        this.date = date;
        this.time = time;
        this.restaurantId = restaurant.getId();
        this.restaurantName = restaurant.getName();
        this.userId = user.getId();
        this.userEmail = user.getEmail();
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

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "date=" + date +
                ", time=" + time +
                ", restaurantId=" + restaurantId +
                ", restaurantName='" + restaurantName + '\'' +
                ", userId=" + userId +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
