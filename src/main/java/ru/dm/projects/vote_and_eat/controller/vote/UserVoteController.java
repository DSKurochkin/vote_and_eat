package ru.dm.projects.vote_and_eat.controller.vote;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dm.projects.vote_and_eat.model.Vote;
import ru.dm.projects.vote_and_eat.util.DateUtil;

import java.util.List;

import static ru.dm.projects.vote_and_eat.controller.vote.AbstractVoteController.VOTE_URL;

@RestController
@RequestMapping(value = VOTE_URL)
public class UserVoteController extends AbstractVoteController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void toVote(Vote vote) {
        if (DateUtil.isPermittedVote(vote)) {
            repository.save(vote);
        }
    }

    @GetMapping
    public List<Vote> all() {
        return repository.findAll();
    }
}
