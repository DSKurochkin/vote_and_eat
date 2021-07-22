package ru.dm.projects.vote_and_eat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dm.projects.vote_and_eat.model.Vote;
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


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void toVote(Vote vote){
        //logic 11.00<>
        repository.save(vote);
    }
}
