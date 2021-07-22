package ru.dm.projects.vote_and_eat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.dm.projects.vote_and_eat.repository.AdminRepository;

@RestController
public class AdminController {
    @Autowired
    private AdminRepository repository;



}
