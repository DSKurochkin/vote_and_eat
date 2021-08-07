package ru.dm.projects.vote_and_eat.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.dm.projects.vote_and_eat.model.Restaurant;
import ru.dm.projects.vote_and_eat.model.Vote;
import ru.dm.projects.vote_and_eat.repository.VoteRepository;
import ru.dm.projects.vote_and_eat.util.VoteUtil;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.dm.projects.vote_and_eat.util.ValidationUtil.notFoundMessage;

@Service
public class VoteService {
    @Autowired
    VoteRepository repository;

    public Vote get(int id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(notFoundMessage(id)));
    }

    public List<Vote> getAll() {
        return repository.findAll();
    }

    public Vote createOrUpdate(Vote vote) {
        Assert.notNull(vote, "vote must not be null");
        return repository.save(vote);
    }

    public void delete(int id) throws Exception {
        repository.delete(get(id));
    }

    public List<Vote> getBetween(LocalDate start, LocalDate end) {
        Map<String, LocalDate> dateMap = VoteUtil.getExternal(start, end);
        return repository.getBetween(dateMap.get("start"), dateMap.get("end"));
    }

    public List<Vote> getByUsersEmail(String email) {
        return repository.getByUser(email);
    }

    public Map<Restaurant, Integer> resultFromToday() {
        //only today!!
        Map<Restaurant, Integer> result = new HashMap<>();
        List<Vote> votes = repository.getAll();
        for (Vote v : votes) {
            Restaurant restaurant = v.getRestaurant();
            if (result.containsKey(restaurant)) {
                result.put(v.getRestaurant(), result.get(restaurant) + 1);
            }
            result.put(restaurant, 1);
        }
        return result;

    }
}
