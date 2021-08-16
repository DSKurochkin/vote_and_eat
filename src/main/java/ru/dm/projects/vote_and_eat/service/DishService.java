package ru.dm.projects.vote_and_eat.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.dm.projects.vote_and_eat.model.Dish;
import ru.dm.projects.vote_and_eat.repository.DishRepository;
import ru.dm.projects.vote_and_eat.util.DateTimeUtil;

import java.util.List;

import static ru.dm.projects.vote_and_eat.util.ValidationUtil.notFoundMessage;

@Service
public class DishService {
    @Autowired
    DishRepository repository;

    public Dish get(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(notFoundMessage(id)));
    }

    public List<Dish> getAll() {
        return repository.findAll();
    }

    public Dish createOrUpdate(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        return repository.save(dish);
    }

    public void delete(Long id) throws Exception {
        repository.delete(get(id));
    }

    public List<Dish> getForToday() {
        return repository.getForToday(DateTimeUtil.getToday());
    }
}
