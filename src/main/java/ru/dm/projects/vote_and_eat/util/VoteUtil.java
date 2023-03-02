package ru.dm.projects.vote_and_eat.util;

import ru.dm.projects.vote_and_eat.model.Vote;
import ru.dm.projects.vote_and_eat.to.RestaurantTo;
import ru.dm.projects.vote_and_eat.util.exception.UnsupportedTimeOperationException;

import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public static Map<RestaurantTo, Integer> getRatingOfRestaurants(List<List<String>> response) {
        return response.stream().collect(Collectors
                .toMap(l -> new RestaurantTo(Long.parseLong(l.get(0)), l.get(1))
                        , l -> Integer.parseInt(l.get(2))
                        , (a, b) -> b
                        , LinkedHashMap::new));
    }

}
