package ru.dm.projects.vote_and_eat.util;

import ru.dm.projects.vote_and_eat.model.Vote;
import ru.dm.projects.vote_and_eat.to.RestaurantTo;
import ru.dm.projects.vote_and_eat.util.exception.UnsupportedTimeOperationException;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static ru.dm.projects.vote_and_eat.util.DateTimeUtil.today;

public class VoteUtil {

    public static void checkVoteTime(Vote vote, LocalTime getStartOfVote, LocalTime getEndOfVote) {
        if (vote.getTime().isAfter(getEndOfVote)) {
            throw new UnsupportedTimeOperationException("it's to late to vote, try again tomorrow");
        }
        if (vote.getTime().isBefore(getStartOfVote)) {
            throw new UnsupportedTimeOperationException("Voting has not started yet");
        }
        if (!vote.getDate().equals(today())) {
            throw new UnsupportedTimeOperationException("Unsupported date of vote");
        }
    }

    public static Map<RestaurantTo, Integer> getRatingOfRestaurants(List<Vote> votes) {
        Map<Long, Integer> result = new HashMap<>();
        votes.forEach(v -> result.merge(v.getRestaurantId(), 1, Integer::sum));
        return result.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(i -> new RestaurantTo(i.getKey(), getRestaurantNameById(i.getKey(), votes)), Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

    }

    private static String getRestaurantNameById(Long id, List<Vote> votes) {
        return votes.stream().filter((v) -> v.getRestaurantId().equals(id)).collect(Collectors.toList()).get(0).getRestaurantName();
    }


}
