package ru.dm.projects.vote_and_eat.controller.vote;

import io.swagger.annotations.ApiOperation;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import ru.dm.projects.vote_and_eat.model.Restaurant;
import ru.dm.projects.vote_and_eat.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static ru.dm.projects.vote_and_eat.controller.user.AbstractUserController.ADMIN_URL;
import static ru.dm.projects.vote_and_eat.controller.user.AbstractUserController.AUTH_URL;

@RestController
@RequestMapping(value = AdminVoteController.ADMIN_VOTE_URL)
public class AdminVoteController extends AbstractVoteController {

    static final String ADMIN_VOTE_URL = AUTH_URL + ADMIN_URL + VOTE_URL;

    @ApiOperation(value = "get votes between given dates", response = Iterable.class)
    @GetMapping("/filter")
    public List<Vote> getBetween(@Nullable @RequestParam LocalDate start,
                                 @Nullable @RequestParam LocalDate end) {
        log.info("vote all votes between dates {} and {}", start, end);
        return voteService.getBetween(dateTimeUtil.checkStartDate(start), dateTimeUtil.checkEndDate(end));
    }

    @ApiOperation(value = "get rating of restaurants for an interval as map, key - restaurant, value count of votes", response = Map.class)
    @GetMapping("/rating")
    public Map<Restaurant, Integer> getRatingForAnInterval(@Nullable @RequestParam LocalDate start,
                                                           @Nullable @RequestParam LocalDate end) {
        log.info("getRatingForAnInterval - {} and {}", start, end);
        return voteService.getRating(start, end);
    }

    @ApiOperation(value = "get vote by id", response = Vote.class)
    @GetMapping("/{id}")
    public Vote get(@PathVariable Long id) throws Exception {
        log.info("get vote with id ={}", id);
        return voteService.get(id);
    }

}
