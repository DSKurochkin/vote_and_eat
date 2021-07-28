package ru.dm.projects.vote_and_eat.controller.dish;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dm.projects.vote_and_eat.repository.DishRepository;

public abstract class AbstractDishController {
    static final String DISH_URL = "/dishes";
    @Autowired
    DishRepository repository;

}
