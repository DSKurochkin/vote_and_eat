package ru.dm.projects.vote_and_eat.controller.dish;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dm.projects.vote_and_eat.model.Dish;

import java.time.LocalDate;
import java.util.List;

@RestController
public class UserDishController extends AbstractDishController {

    @GetMapping("DISH_URL")
    List<Dish> dishesForToday() {
        return repository.getForToday(LocalDate.now());
    }


}
