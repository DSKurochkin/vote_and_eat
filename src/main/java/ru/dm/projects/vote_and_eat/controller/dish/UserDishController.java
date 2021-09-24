package ru.dm.projects.vote_and_eat.controller.dish;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dm.projects.vote_and_eat.to.DishTo;

import java.util.List;
import java.util.Map;

import static ru.dm.projects.vote_and_eat.security.SecurityUtil.get;

@RestController
public class UserDishController extends AbstractDishController {

    @ApiOperation(value = "get dish menu for today as Map: key - restaurant name, value - list od DishTo", response = Map.class)
    @GetMapping(DISH_URL)
    Map<String, List<DishTo>> dishesForToday() {
        log.info("get dish menu for today by user with id={}", get().getId());
        return dishService.todayMenu();
    }

}
