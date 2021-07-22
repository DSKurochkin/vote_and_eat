package ru.dm.projects.vote_and_eat.controller;

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
@RestController
@RequestMapping(value =RestaurantController.REST_RESTAURANT_URL
        , produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {
    static final String REST_RESTAURANT_URL = "/restaurants";

    @Autowired
    private RestaurantRepository repository;

    @GetMapping
    List<Restaurant> getAll() {
        return repository.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        Restaurant created = repository.save(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_RESTAURANT_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
   @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
   @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(Restaurant restaurant, @PathVariable int id){
       Assert.notNull(restaurant, "restaurant must not be null");
        repository.save(restaurant);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public  void delete( @PathVariable int id){

    }

}
