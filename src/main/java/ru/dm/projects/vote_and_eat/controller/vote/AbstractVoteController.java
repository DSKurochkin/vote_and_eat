package ru.dm.projects.vote_and_eat.controller.vote;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dm.projects.vote_and_eat.repository.VoteRepository;

public class AbstractVoteController {
    static final String VOTE_URL = "/votes";
    @Autowired
    VoteRepository repository;
}
