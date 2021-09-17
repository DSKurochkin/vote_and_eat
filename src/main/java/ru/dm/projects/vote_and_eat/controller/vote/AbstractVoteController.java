package ru.dm.projects.vote_and_eat.controller.vote;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dm.projects.vote_and_eat.service.VoteService;
import ru.dm.projects.vote_and_eat.util.DateTimeUtil;


public class AbstractVoteController {
    static final String VOTE_URL = "/votes";
    @Autowired
    VoteService voteService;

    @Autowired
    DateTimeUtil dateTimeUtil;
}
