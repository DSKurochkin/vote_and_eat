package ru.dm.projects.vote_and_eat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dm.projects.vote_and_eat.entity.Restaurant;
import ru.dm.projects.vote_and_eat.entity.User;
import ru.dm.projects.vote_and_eat.repository.RestaurantRepository;

import java.util.List;
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantRepository repository;

    @GetMapping("/restaurants")
    List<Restaurant> getAll(){
        return repository.findAll();
    }
}
