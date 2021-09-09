package ru.dm.projects.vote_and_eat.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dm.projects.vote_and_eat.controller.AbstractControllerTest;
import ru.dm.projects.vote_and_eat.service.UserService;

public class AbstractUserControllerTest extends AbstractControllerTest {
    @Autowired
    UserService userService;
}
