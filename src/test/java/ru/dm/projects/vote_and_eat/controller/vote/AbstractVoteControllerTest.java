package ru.dm.projects.vote_and_eat.controller.vote;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dm.projects.vote_and_eat.controller.AbstractControllerTest;
import ru.dm.projects.vote_and_eat.model.Vote;
import ru.dm.projects.vote_and_eat.service.UserService;
import ru.dm.projects.vote_and_eat.service.VoteService;

import static ru.dm.projects.vote_and_eat.test_data.DateTimeTestData.goodTestTimeToVote;
import static ru.dm.projects.vote_and_eat.test_data.VoteTestData.getTestUser;
import static ru.dm.projects.vote_and_eat.test_data.VoteTestData.newVotes;
import static ru.dm.projects.vote_and_eat.util.DateTimeUtil.useMockTime;

public class AbstractVoteControllerTest extends AbstractControllerTest {
    @Autowired
    VoteService voteService;
    @Autowired
    UserService userService;

    void populateVote() {
        useMockTime(goodTestTimeToVote);
        userService.create(getTestUser());
        for (Vote v : newVotes) {
            if (v.getId() != null) {
                v.setId(null);
            }
            voteService.createOrUpdate(v);
        }

    }


}
