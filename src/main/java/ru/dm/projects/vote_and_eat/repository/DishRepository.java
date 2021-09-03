package ru.dm.projects.vote_and_eat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.dm.projects.vote_and_eat.model.Dish;

import java.time.LocalDate;
import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {
    Dish findByName(String name);

    @Query("SELECT d FROM Dish d WHERE d.date=?1")
    List<Dish> getForToday(LocalDate date);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.name=?1 and d.date>=?2 and d.date<=?3")
    List<Dish> getByRestaurantName(String name, LocalDate start, LocalDate end);
}
