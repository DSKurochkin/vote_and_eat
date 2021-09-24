package ru.dm.projects.vote_and_eat.controller.dish;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.dm.projects.vote_and_eat.model.Dish;
import ru.dm.projects.vote_and_eat.service.RestaurantService;
import ru.dm.projects.vote_and_eat.to.DishTo;
import ru.dm.projects.vote_and_eat.util.DateTimeUtil;
import ru.dm.projects.vote_and_eat.util.DishUtil;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @ApiOperation(value = "get all dishes", response = Iterable.class)
    @GetMapping
    List<Dish> getAll() {
        log.info("get all dishes");
        return dishService.getAll();
    }

    @ApiOperation(value = "get dish by id", response = Dish.class)
    @GetMapping("/{id}")
    public Dish get(@PathVariable Long id) throws Exception {
        log.info("get dish with id ={}", id);
        return dishService.get(id);
    }

    @ApiOperation(value = "get dishes by RestaurantName between given dates", response = Iterable.class)
    @GetMapping("/byRestaurantName")
    public List<Dish> getByRestaurantName(@RequestParam String name,
                                          @Nullable @RequestParam LocalDate start,
                                          @Nullable @RequestParam LocalDate end) throws Exception {
        log.info("get all dishes by restaurant name {} between dates {} and {}", name, start, end);
        return dishService.getByRestaurantName(name, dateTimeUtil.checkStartDate(start), dateTimeUtil.checkEndDate(end));
    }

    @ApiOperation(value = "create dish", response = Dish.class)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@Valid @RequestBody DishTo dishTo) throws Exception {
        log.info("create dish {}", dishTo);
        Assert.notNull(dishTo, "dish must not be null");
        DishUtil.checkPossibilityOfAction(dishTo, dateTimeUtil.getStartOfVote());
        Dish dish = createNewFromTo(dishTo, restaurantService.get(dishTo.getRestaurant_id()));
        Dish created = dishService.create(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(DISH_URL + "{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @ApiOperation(value = "update dish")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody DishTo dishTo, @PathVariable long id) throws Exception {
        log.info("update dish {}", dishTo);
        DishUtil.checkPossibilityOfAction(dishTo, dateTimeUtil.getStartOfVote());
        Dish dish = dishService.get(dishTo.getId());
        dishService.update(DishUtil.updateFromTo(dish, dishTo), id);
    }

    @ApiOperation(value = "delete dish by id")
    @DeleteMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws Exception {
        log.info("delete dish with id={}", id);
        dishService.delete(id);
    }

}
