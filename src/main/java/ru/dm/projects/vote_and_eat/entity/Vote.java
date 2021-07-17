package ru.dm.projects.vote_and_eat.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name="votes")
public class Vote {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 long id;

@Column(name="date",nullable = false, columnDefinition = "timestamp default now()")
 private LocalDate date;

 @Column(name="time",nullable = false, columnDefinition = "timestamp default now()")
 private LocalTime time;

@JoinColumn(name = "restaurant_id")
@ManyToOne
private Restaurant restaurant;


@OneToOne(mappedBy = "user")
private User user;


 public Vote(){ }


}
