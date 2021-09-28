package ru.dm.projects.vote_and_eat.controller.vote;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.dm.projects.vote_and_eat.model.Vote;
import ru.dm.projects.vote_and_eat.service.RestaurantService;
import ru.dm.projects.vote_and_eat.to.RestaurantTo;
import ru.dm.projects.vote_and_eat.to.VoteTo;
import ru.dm.projects.vote_and_eat.util.exception.NotFoundException;
import ru.dm.projects.vote_and_eat.util.exception.UnsupportedTimeOperationException;

import javax.validation.Valid;
import java.net.URI;
import java.util.Map;

import static ru.dm.projects.vote_and_eat.controller.user.AbstractUserController.AUTH_URL;
import static ru.dm.projects.vote_and_eat.controller.vote.UserVoteController.USER_VOTE_URL;
import static ru.dm.projects.vote_and_eat.security.SecurityUtil.get;
import static ru.dm.projects.vote_and_eat.util.DateTimeUtil.now;
import static ru.dm.projects.vote_and_eat.util.DateTimeUtil.today;
import static ru.dm.projects.vote_and_eat.util.VoteUtil.checkVoteTime;

@RestController
@RequestMapping(value = USER_VOTE_URL)
public class UserVoteController extends AbstractVoteController {

    static final String USER_VOTE_URL = AUTH_URL + VOTE_URL;

    @Autowired
    RestaurantService restaurantService;

    @ApiOperation(value = "create vote by user", response = Vote.class)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> toVote(@Valid @RequestBody VoteTo voteTo) throws Exception {
        log.info("vote by user with id={}", get().getId());
        Vote vote = new Vote(voteService.getIdIfTodayVoteExist(get().getId())
                , today()
                , now()
                , voteTo.getRestaurant_id()
                , restaurantService.get(voteTo.getRestaurant_id()).getName()
                , get().getId()
                , get().getUserTo().getEmail());
        checkVoteTime(vote, dateTimeUtil.getEndOfVote());
        vote = voteService.createOrUpdate(vote);
        URI uriOfResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(VOTE_URL + "{id}")
                .buildAndExpand(vote.getId()).toUri();
        return ResponseEntity.created(uriOfResource).body(vote);

    }

    @ApiOperation(value = "get rating of restaurants for current day as map" +
            ", key - restaurantTo, value - count of votes, group by count"
            , response = Map.class)
    @GetMapping("/result")
    public Map<RestaurantTo, Integer> getResult() {
        log.info("get result for today by user with id={}", get().getId());
        if (now().isBefore(dateTimeUtil.getEndOfVote())) {
            throw new UnsupportedTimeOperationException("The vote is still in progress. Try after " + dateTimeUtil.getEndOfVote() + " o'clock");
        }
        Map<RestaurantTo, Integer> result = voteService.getRating(today(), today());
        if (result.isEmpty()) {
            throw new NotFoundException("Nobody voted today");
        }
        return result;

    }
}
