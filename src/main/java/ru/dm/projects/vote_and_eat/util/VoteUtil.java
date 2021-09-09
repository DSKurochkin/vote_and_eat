package ru.dm.projects.vote_and_eat.util;

import ru.dm.projects.vote_and_eat.model.Restaurant;
import ru.dm.projects.vote_and_eat.model.Vote;
import ru.dm.projects.vote_and_eat.util.exception.UnsupportedTimeOperationException;

import java.time.LocalTime;
import java.util.*;

import static ru.dm.projects.vote_and_eat.util.DateTimeUtil.today;

public class VoteUtil {

    public static void checkVoteTime(Vote vote, LocalTime getEndOfVote) {
        if (vote.getTime().isAfter(getEndOfVote)) {
            throw new UnsupportedTimeOperationException("it's to late to vote, try again tomorrow");
        }
        if (!vote.getDate().equals(today())) {
            throw new UnsupportedTimeOperationException("unsupported date of vote");
        }
    }

    public static Map<Integer, Restaurant> getRatingOfRestaurants(List<Vote> votes) {
        Map<Restaurant, Integer> result = new HashMap<>();
        votes.forEach(v -> result.merge(v.getRestaurant(), 1, Integer::sum));
        return swapMap(result);
    }

    private static Map<Integer, Restaurant> swapMap(Map<Restaurant, Integer> sourceMap) {
        Map<Integer, Restaurant> resultMap = new TreeMap<>(Collections.reverseOrder());
        sourceMap.forEach((k, v) -> resultMap.put(v, k));
        return resultMap;
    }

}
