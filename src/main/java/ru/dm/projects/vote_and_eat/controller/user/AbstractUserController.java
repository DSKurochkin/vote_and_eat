package ru.dm.projects.vote_and_eat.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dm.projects.vote_and_eat.service.UserService;

public class AbstractUserController {

    public static final String ADMIN_URL = "/admin";


    @Autowired
    UserService service;


}
