package ru.dm.projects.vote_and_eat.controller.vote;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dm.projects.vote_and_eat.model.Vote;

import java.time.LocalDate;
import java.util.List;

import static ru.dm.projects.vote_and_eat.controller.user.AbstractUserController.ADMIN_URL;
import static ru.dm.projects.vote_and_eat.controller.vote.AbstractVoteController.VOTE_URL;

@RestController
@RequestMapping(value = ADMIN_URL + VOTE_URL)
public class AdminVoteController extends AbstractVoteController {

    @GetMapping("/between")
    public List<Vote> showBetween(LocalDate start, LocalDate end) {
        if (start == null) {
            start = LocalDate.parse("2021-01-01");
        }
        if (end == null) {
            end = LocalDate.parse("3021-01-01");
        }
        return repository.findAllByDateBetween(start, end);
    }

    public List<Vote> showByUser(String email) {
        return repository.getByUser(email);
    }

}
