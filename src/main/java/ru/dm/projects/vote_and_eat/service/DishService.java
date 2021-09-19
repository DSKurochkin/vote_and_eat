package ru.dm.projects.vote_and_eat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.dm.projects.vote_and_eat.model.Dish;
import ru.dm.projects.vote_and_eat.repository.DishRepository;
import ru.dm.projects.vote_and_eat.to.DishTo;
import ru.dm.projects.vote_and_eat.util.DishUtil;
import ru.dm.projects.vote_and_eat.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static ru.dm.projects.vote_and_eat.util.DateTimeUtil.today;
import static ru.dm.projects.vote_and_eat.util.ValidationUtil.*;

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

    public Dish create(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        checkNew(dish);
        return repository.save(dish);
    }

    public Dish update(Dish dish, long id) {
        Assert.notNull(dish, "dish must not be null");
        assureIdConsistent(dish, id);
        return repository.save(dish);
    }

    public void delete(Long id) throws Exception {
        repository.delete(get(id));
    }

    public Map<String, List<DishTo>> todayMenu() {
        Map<String, List<DishTo>> result = new TreeMap<>();
        for (Dish dish : repository.getForToday(today())) {
            String restaurantName = dish.getRestaurant().getName();
            if (!result.containsKey(restaurantName)) {
                result.put(restaurantName, new ArrayList<DishTo>());
            }
            result.get(restaurantName).add(DishUtil.asTo(dish));
        }
        return result;
    }

    public List<Dish> getByRestaurantName(String name, LocalDate start, LocalDate end) {
        return repository.getByRestaurantName(name, start, end);
    }
}
