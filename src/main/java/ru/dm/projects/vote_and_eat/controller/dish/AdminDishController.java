package ru.dm.projects.vote_and_eat.controller.dish;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.dm.projects.vote_and_eat.model.Dish;
import ru.dm.projects.vote_and_eat.security.SecurityUtil;
import ru.dm.projects.vote_and_eat.to.DishTo;
import ru.dm.projects.vote_and_eat.util.DishUtil;

import java.net.URI;
import java.util.List;

import static ru.dm.projects.vote_and_eat.controller.dish.AbstractDishController.DISH_URL;
import static ru.dm.projects.vote_and_eat.controller.user.AbstractUserController.ADMIN_URL;

@RestController
@RequestMapping(value = ADMIN_URL + DISH_URL)
public class AdminDishController extends AbstractDishController {
    ///
    @GetMapping
    List<Dish> getAll() {
        System.out.println("Id is = " + SecurityUtil.authUserId());
        return service.getAll();
    }


    @GetMapping("/{id}")
    public Dish get(@PathVariable Long id) throws Exception {
        return service.get(id);
    }

//

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@RequestBody Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        DishUtil.isAdmissibleTimeToChange(dish);
        Dish created = service.createOrUpdate(dish);
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
        Dish dish = service.get(dishTo.getId());
        service.createOrUpdate(DishUtil.updateFromTo(dish, dishTo));
    }

    @DeleteMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws Exception {
        service.delete(id);
    }


}
