package ru.dm.projects.vote_and_eat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dm.projects.vote_and_eat.entity.Vote;
import ru.dm.projects.vote_and_eat.repository.VoteRepository;

import java.util.List;

@RestController
public class VoteController {
    @Autowired
    private VoteRepository repository;
    @GetMapping("/votes")
    List<Vote> getAll(){
        return repository.findAll();
    }
}
