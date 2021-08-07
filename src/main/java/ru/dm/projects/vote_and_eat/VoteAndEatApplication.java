package ru.dm.projects.vote_and_eat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.dm.projects.vote_and_eat.model.Restaurant;
import ru.dm.projects.vote_and_eat.repository.DishRepository;
import ru.dm.projects.vote_and_eat.repository.RestaurantRepository;
import ru.dm.projects.vote_and_eat.repository.VoteRepository;

@SpringBootApplication
public class VoteAndEatApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoteAndEatApplication.class, args);

    }

}
