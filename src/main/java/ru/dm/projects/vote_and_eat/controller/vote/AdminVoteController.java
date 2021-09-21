package ru.dm.projects.vote_and_eat.controller.vote;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import ru.dm.projects.vote_and_eat.model.Restaurant;
import ru.dm.projects.vote_and_eat.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static ru.dm.projects.vote_and_eat.controller.user.AbstractUserController.ADMIN_URL;
import static ru.dm.projects.vote_and_eat.controller.vote.AbstractVoteController.VOTE_URL;

@RestController
@RequestMapping(value = ADMIN_URL + VOTE_URL)
public class AdminVoteController extends AbstractVoteController {

    @GetMapping
    public List<Vote> getAll() {
        log.info("get all votes");
        return voteService.getAll();
    }

    @GetMapping("/filter")
    public List<Vote> getBetween(@Nullable @RequestParam LocalDate start,
                                 @Nullable @RequestParam LocalDate end) {
        log.info("vote all votes between dates {} and {}", start, end);
        return voteService.getBetween(dateTimeUtil.chekStartDate(start), dateTimeUtil.chekEndDate(end));
    }

    @GetMapping("/rating")
    public Map<Integer, Restaurant> getRatingByInterval(@Nullable @RequestParam LocalDate start,
                                                        @Nullable @RequestParam LocalDate end) {
        log.info("getRatingByInterval - {} and {}", start, end);
        return voteService.getRating(start, end);
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable Long id) throws Exception {
        log.info("get vote with id ={}", id);
        return voteService.get(id);
    }

}
