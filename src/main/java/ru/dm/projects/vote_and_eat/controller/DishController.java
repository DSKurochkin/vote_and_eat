package ru.dm.projects.vote_and_eat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dm.projects.vote_and_eat.model.Dish;
import ru.dm.projects.vote_and_eat.repository.DishRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DishController {
    @Autowired
    private DishRepository repository;

    @GetMapping("/dishesall")
    List<Dish> getAll() {

        List<Dish> list = repository.findAll();
        list.forEach(System.out::println);
        return list;
        // return repository.findAll();
    }

    @GetMapping("/dishes")
    List<Dish> currentVotes() {
        return repository.getCurrent(LocalDate.now());
    }


}
