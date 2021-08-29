package ru.dm.projects.vote_and_eat.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.dm.projects.vote_and_eat.model.Restaurant;
import ru.dm.projects.vote_and_eat.model.Vote;
import ru.dm.projects.vote_and_eat.repository.VoteRepository;
import ru.dm.projects.vote_and_eat.util.VoteUtil;
import ru.dm.projects.vote_and_eat.util.json.JsonUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static ru.dm.projects.vote_and_eat.util.ValidationUtil.notFoundMessage;

@Service
public class VoteService {
    @Autowired
    VoteRepository repository;

    public Vote get(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(notFoundMessage(id)));
    }

    public List<Vote> getAll() {
        return repository.findAll();
    }

    public Vote createOrUpdate(Vote vote) {
        Assert.notNull(vote, "vote must not be null");
        return repository.save(vote);
    }

    public void delete(Long id) throws Exception {
        repository.delete(get(id));
    }

    public List<Vote> getBetween(LocalDate start, LocalDate end, LocalDate min, LocalDate  max) {
        Map<String, LocalDate> dateMap = VoteUtil.getExternal(start, end, min, max);
        return repository.getBetween(dateMap.get("start"), dateMap.get("end"));
    }

    public List<Vote> getByUsersEmail(String email) {
        return repository.getByUser(email);
    }

    public Map<Integer, Restaurant> resultFromToday() {
        return VoteUtil.getRatingOfRestaurants(repository.getForToday(LocalDate.now()));
    }

}
