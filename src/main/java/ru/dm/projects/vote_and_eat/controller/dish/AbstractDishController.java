package ru.dm.projects.vote_and_eat.controller.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dm.projects.vote_and_eat.service.DishService;

public abstract class AbstractDishController {
    static final String DISH_URL = "/dishes";
    final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    DishService dishService;

}
