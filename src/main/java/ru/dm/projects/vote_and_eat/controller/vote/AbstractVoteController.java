package ru.dm.projects.vote_and_eat.controller.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dm.projects.vote_and_eat.service.VoteService;
import ru.dm.projects.vote_and_eat.util.DateTimeUtil;


public class AbstractVoteController {
    static final String VOTE_URL = "/votes";
    final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    VoteService voteService;
    @Autowired
    DateTimeUtil dateTimeUtil;
}
