package ru.dm.projects.vote_and_eat.controller.dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.dm.projects.vote_and_eat.model.Dish;
import ru.dm.projects.vote_and_eat.model.Restaurant;
import ru.dm.projects.vote_and_eat.service.RestaurantService;
import ru.dm.projects.vote_and_eat.to.DishTo;
import ru.dm.projects.vote_and_eat.util.DateTimeUtil;
import ru.dm.projects.vote_and_eat.util.DishUtil;

import java.net.URI;
import java.util.List;

import static ru.dm.projects.vote_and_eat.controller.dish.AbstractDishController.DISH_URL;
import static ru.dm.projects.vote_and_eat.controller.user.AbstractUserController.ADMIN_URL;
import static ru.dm.projects.vote_and_eat.util.DishUtil.createNewFromTo;

@RestController
@RequestMapping(value = ADMIN_URL + DISH_URL)
public class AdminDishController extends AbstractDishController {
    @Autowired
    RestaurantService restaurantService;

    @Autowired
    DateTimeUtil dateTimeUtil;

    @GetMapping
    List<Dish> getAll() {
        return dishService.getAll();
    }


    @GetMapping("/{id}")
    public Dish get(@PathVariable Long id) throws Exception {
        return dishService.get(id);
    }



    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@RequestBody DishTo dishTo) throws Exception {
        Assert.notNull(dishTo, "dish must not be null");
        DishUtil.checkPossibilityOfAction(dishTo, dateTimeUtil.getStartOfVote());
        Dish dish=createNewFromTo(dishTo, restaurantService.get(dishTo.getRestaurant_id()));
        Dish created = dishService.createOrUpdate(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(DISH_URL + "{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody DishTo dishTo, @PathVariable int id) throws Exception {
        //DishUtil.isAdmissibleTimeToChange(dish);
        //check id=bean.id
        Dish dish = dishService.get(dishTo.getId());
        dishService.createOrUpdate(DishUtil.updateFromTo(dish, dishTo));
    }

    @DeleteMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws Exception {
        dishService.delete(id);
    }


}
