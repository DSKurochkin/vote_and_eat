package ru.dm.projects.vote_and_eat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.dm.projects.vote_and_eat.model.Restaurant;
import ru.dm.projects.vote_and_eat.repository.RestaurantRepository;
import ru.dm.projects.vote_and_eat.util.exception.NotFoundException;

import java.util.List;
import java.util.Set;

import static ru.dm.projects.vote_and_eat.util.ValidationUtil.*;

@Transactional(readOnly = true)
@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository repository;

    public Restaurant get(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(notFoundMessage(id, "Restaurant")));
    }

    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    @Transactional
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNew(restaurant);
        return repository.save(restaurant);
    }

    @Transactional
    public Restaurant update(Restaurant restaurant, long id) {
        Assert.notNull(restaurant, "restaurant must not be null");
        assureIdConsistent(restaurant, id);
        return repository.save(restaurant);
    }

    @Transactional
    public void delete(Long id) throws Exception {
        repository.delete(get(id));
    }

    public Set<Long> getIds() {
        return repository.getAllIds();
    }

}
