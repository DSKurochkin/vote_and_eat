package ru.dm.projects.vote_and_eat.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.dm.projects.vote_and_eat.model.Restaurant;
import ru.dm.projects.vote_and_eat.repository.RestaurantRepository;

import java.util.List;

import static ru.dm.projects.vote_and_eat.util.ValidationUtil.notFoundMessage;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository repository;

    public Restaurant get(int id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(notFoundMessage(id)));
    }

    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    public Restaurant createOrUpdate(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    public void delete(int id) throws Exception {
        repository.delete(get(id));
    }
}
