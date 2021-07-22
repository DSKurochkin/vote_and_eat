package ru.dm.projects.vote_and_eat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dm.projects.vote_and_eat.model.User;
import ru.dm.projects.vote_and_eat.repository.UserRepository;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/users")
    List<User> getAll() {
        return repository.findAll();
    }

}
