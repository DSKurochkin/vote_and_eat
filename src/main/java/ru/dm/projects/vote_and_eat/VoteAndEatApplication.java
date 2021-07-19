package ru.dm.projects.vote_and_eat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.dm.projects.vote_and_eat.repository.DishRepository;

@SpringBootApplication
public class VoteAndEatApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoteAndEatApplication.class, args);

        //        ApplicationContext ctx = SpringApplication.run(VoteAndEatApplication.class, args);
//        DishRepository repository=ctx.getBean(DishRepository.class);
//        repository.findAll().forEach(System.out::println);

    }

}
