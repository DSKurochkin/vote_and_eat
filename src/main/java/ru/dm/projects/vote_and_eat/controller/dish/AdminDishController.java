package ru.dm.projects.vote_and_eat.controller.dish;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.dm.projects.vote_and_eat.model.Dish;
import ru.dm.projects.vote_and_eat.model.Restaurant;
import ru.dm.projects.vote_and_eat.util.DishUtil;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.dm.projects.vote_and_eat.controller.dish.AbstractDishController.DISH_URL;
import static ru.dm.projects.vote_and_eat.controller.user.AbstractUserController.ADMIN_URL;

@RestController
@RequestMapping(value = ADMIN_URL + DISH_URL)
public class AdminDishController extends AbstractDishController {
    ///
    @GetMapping
    List<Dish> getAll() {
        return repository.findAll();
    }
//

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@RequestBody Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        DishUtil.isAdmissibleTimeToChange(dish);
        Dish created = repository.save(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(DISH_URL + "{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish, @PathVariable int id) {
        Assert.notNull(dish, "dish must not be null");
        DishUtil.isAdmissibleTimeToChange(dish);
        //check id=bean.id
        repository.save(dish);
    }

    @DeleteMapping(value = {"/{id}"})
    public void delete(@PathVariable int id) {
        repository.deleteById(id);
    }


}
