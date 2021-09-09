package ru.dm.projects.vote_and_eat.controller.vote;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dm.projects.vote_and_eat.controller.AbstractControllerTest;
import ru.dm.projects.vote_and_eat.service.UserService;
import ru.dm.projects.vote_and_eat.service.VoteService;

import static ru.dm.projects.vote_and_eat.test_data.DateTimeTestData.goodTestTimeToVote;
import static ru.dm.projects.vote_and_eat.test_data.VoteTestData.newVotes;
import static ru.dm.projects.vote_and_eat.test_data.VoteTestData.testUser;
import static ru.dm.projects.vote_and_eat.util.DateTimeUtil.useMockTime;

public class AbstractVoteControllerTest extends AbstractControllerTest {
    @Autowired
    VoteService voteService;
    @Autowired
    UserService userService;

    void populateVote() {
        useMockTime(goodTestTimeToVote);
        userService.createOrUpdate(testUser);
        newVotes.forEach(v -> voteService.createOrUpdate(v));
    }
}
