package ru.dm.projects.vote_and_eat.controller.vote;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.dm.projects.vote_and_eat.model.Vote;
import ru.dm.projects.vote_and_eat.util.VoteUtil;

import java.util.List;

import static ru.dm.projects.vote_and_eat.controller.vote.AbstractVoteController.VOTE_URL;
import static ru.dm.projects.vote_and_eat.util.VoteUtil.*;

@RestController
@RequestMapping(value = VOTE_URL)
public class UserVoteController extends AbstractVoteController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void toVote(@RequestBody  Vote vote) {
        checkVoteTime(vote);
        repository.save(vote);

    }

}
