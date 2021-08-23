package ru.dm.projects.vote_and_eat.controller.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.dm.projects.vote_and_eat.model.Restaurant;
import ru.dm.projects.vote_and_eat.model.Vote;
import ru.dm.projects.vote_and_eat.service.RestaurantService;
import ru.dm.projects.vote_and_eat.service.UserService;
import ru.dm.projects.vote_and_eat.to.VoteTo;
import ru.dm.projects.vote_and_eat.util.DateTimeUtil;

import java.net.URI;
import java.util.Map;

import static ru.dm.projects.vote_and_eat.controller.vote.AbstractVoteController.VOTE_URL;
import static ru.dm.projects.vote_and_eat.security.SecurityUtil.get;
import static ru.dm.projects.vote_and_eat.util.UserUtil.createNewFromTo;
import static ru.dm.projects.vote_and_eat.util.VoteUtil.checkVoteTime;

@RestController
@RequestMapping(value = VOTE_URL)
public class UserVoteController extends AbstractVoteController {

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> toVote(@RequestBody VoteTo voteTo) throws Exception {
        Vote created = new Vote(null
                , DateTimeUtil.getToday()
                , DateTimeUtil.getNow()
                , restaurantService.get(voteTo.getRestaurant_id())
                , createNewFromTo(get().getUserTo()));
        checkVoteTime(created);
        created = service.createOrUpdate(created);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(VOTE_URL + "{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);

    }

    @GetMapping("/result")
    public Map<Integer, Restaurant> getResult() {
        Map<Integer, Restaurant> result = service.resultFromToday();
        if (result.isEmpty()) {
            throw new RuntimeException("??? Today no result");
        }
        return result;
    }

}
