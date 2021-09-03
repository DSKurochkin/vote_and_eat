package ru.dm.projects.vote_and_eat.controller.dish;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dm.projects.vote_and_eat.to.DishTo;

import java.util.Map;

@RestController
public class UserDishController extends AbstractDishController {

    @GetMapping("DISH_URL")
    Map<String, DishTo> dishesForToday() {
        return dishService.todayMenu();
    }

}
