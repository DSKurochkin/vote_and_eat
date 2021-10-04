package ru.dm.projects.vote_and_eat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.dm.projects.vote_and_eat.model.Vote;
import ru.dm.projects.vote_and_eat.repository.VoteRepository;
import ru.dm.projects.vote_and_eat.to.RestaurantTo;
import ru.dm.projects.vote_and_eat.util.VoteUtil;
import ru.dm.projects.vote_and_eat.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static ru.dm.projects.vote_and_eat.util.DateTimeUtil.today;
import static ru.dm.projects.vote_and_eat.util.ValidationUtil.notFoundMessage;

@Transactional
@Service
public class VoteService {
    @Autowired
    RestaurantService restaurantService;
    @Autowired
    private VoteRepository repository;

    public Vote get(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(notFoundMessage(id, "Vote")));
    }

    public List<Vote> getAll() {
        return repository.findAll();
    }

    @Transactional
    public Vote createOrUpdate(Vote vote) {
        Assert.notNull(vote, "vote must not be null");
        return repository.save(vote);
    }

    @Transactional
    public void delete(Long id) throws Exception {
        repository.delete(get(id));
    }

    public List<Vote> getBetween(LocalDate start, LocalDate end) {
        return repository.getBetween(start, end);
    }

    public Map<RestaurantTo, Integer> getRating(LocalDate start, LocalDate end) {
        return VoteUtil.getRatingOfRestaurants(repository.getBetween(start, end));
    }

    public Long getIdIfTodayVoteExist(Long id) {
        Vote vote = repository.getIfTodayVoteExist(id, today());
        return vote == null ? null : vote.getId();
    }

    public void checkForExist(Set<RestaurantTo> tos) throws Exception {
        Set<Long> existIds = restaurantService.getIds();
        for (RestaurantTo to : tos) {
            if (!existIds.contains(to.getId())) {
                to.setExist(false);
            }
        }

    }

}
