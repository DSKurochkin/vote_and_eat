package ru.dm.projects.vote_and_eat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.dm.projects.vote_and_eat.model.Restaurant;

import java.util.Set;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query("SELECT r.id FROM Restaurant r")
    Set<Long> getAllIds();
}
