package ru.dm.projects.vote_and_eat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dm.projects.vote_and_eat.entity.Dish;

public interface DishRepository extends JpaRepository<Dish, Integer> {
    Dish findByName(String name);
}
