package ru.dm.projects.vote_and_eat.controller.vote;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import ru.dm.projects.vote_and_eat.model.Vote;

import java.time.LocalDate;
import java.util.List;

import static ru.dm.projects.vote_and_eat.controller.user.AbstractUserController.ADMIN_URL;
import static ru.dm.projects.vote_and_eat.controller.vote.AbstractVoteController.VOTE_URL;

@RestController
@RequestMapping(value = ADMIN_URL + VOTE_URL)
public class AdminVoteController extends AbstractVoteController {

    @GetMapping
    public List<Vote> getAll() {
        return voteService.getAll();
    }

    @GetMapping("/filter")
    public List<Vote> getBetween(@Nullable @RequestParam LocalDate start,
                                 @Nullable @RequestParam LocalDate end) {
        return voteService.getBetween(dateTimeUtil.chekStartDate(start), dateTimeUtil.chekEndDate(end));
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable Long id) throws Exception {
        return voteService.get(id);
    }

    @GetMapping("/by")
    public List<Vote> getByUser(@RequestParam String email) {
        return voteService.getByUsersEmail(email);
    }


}
