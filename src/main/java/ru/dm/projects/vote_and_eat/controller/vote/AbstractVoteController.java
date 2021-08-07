package ru.dm.projects.vote_and_eat.controller.vote;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dm.projects.vote_and_eat.service.VoteService;

public class AbstractVoteController {
    static final String VOTE_URL = "/votes";
    @Autowired
    VoteService service;
}
