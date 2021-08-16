package ru.dm.projects.vote_and_eat.util;

import org.springframework.lang.Nullable;
import ru.dm.projects.vote_and_eat.model.Restaurant;
import ru.dm.projects.vote_and_eat.model.Vote;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static ru.dm.projects.vote_and_eat.util.DateTimeUtil.*;

public class VoteUtil {

    public static void checkVoteTime(Vote vote) {
        if (vote.getTime().isAfter(getEndOfVote())) {
            throw new RuntimeException("it's to late to vote, try again tomorrow");
        }
        if (!vote.getDate().equals(getToday())) {
            throw new RuntimeException("unsupported date of vote");
        }
    }

    private static LocalTime getEndOfVote() {
        return getTime("11:00:00");
    }

    public static Map<String, LocalDate> getExternal(@Nullable LocalDate start, @Nullable LocalDate end) {
        Map<String, LocalDate> map = new HashMap<>();
        if (start == null) {
            start = getDate("2021-01-01");
        }
        if (end == null) {
            end = getDate("3021-01-01");
        }
        map.put("start", start);
        map.put("end", end);
        return map;
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
