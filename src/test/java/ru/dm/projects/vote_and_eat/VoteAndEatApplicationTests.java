package ru.dm.projects.vote_and_eat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class VoteAndEatApplicationTests {
    @Autowired
    ApplicationContext context;

    @Test
    void contextLoads() {
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
    }

}
