package ru.dm.projects.vote_and_eat.controller.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.dm.projects.vote_and_eat.model.Restaurant;
import ru.dm.projects.vote_and_eat.repository.RestaurantRepository;

import java.net.URI;
import java.util.List;

import static ru.dm.projects.vote_and_eat.controller.user.AbstractUserController.ADMIN_URL;

@RestController
@RequestMapping(value = RestaurantController.RESTAURANT_URL
        , produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {
    static final String RESTAURANT_URL = ADMIN_URL + "/restaurants";

    @Autowired
    private RestaurantRepository repository;

    @GetMapping
    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        Restaurant created = repository.save(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(RESTAURANT_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restaurant restaurant, @PathVariable int id) {
        Assert.notNull(restaurant, "restaurant must not be null");
        //chek id=bean.id
        repository.save(restaurant);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        repository.deleteById(id);
    }

}
