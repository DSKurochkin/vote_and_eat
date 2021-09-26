package ru.dm.projects.vote_and_eat.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dm.projects.vote_and_eat.service.UserService;

public class AbstractUserController {

    public static final String ADMIN_URL = "/admin";
    public static final String AUTH_URL = "/auth";
    static final String USER_URL = "/users";
    final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    UserService userService;


}
