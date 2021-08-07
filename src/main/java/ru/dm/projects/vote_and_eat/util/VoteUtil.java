package ru.dm.projects.vote_and_eat.util;

import org.springframework.lang.Nullable;
import ru.dm.projects.vote_and_eat.model.Vote;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class VoteUtil {

    public static void checkVoteTime(Vote vote) {
        if (vote.getTime().isAfter(getEndOfVote())) {
            throw new RuntimeException("??????????");
        }
    }

    public static void setExtremeValues(LocalDate start, LocalDate end) {

    }

    private static LocalTime getEndOfVote() {
        return LocalTime.parse("11:00:00");
    }

    public static Map<String, LocalDate> getExternal(@Nullable LocalDate start, @Nullable LocalDate end) {
        Map<String, LocalDate> map = new HashMap<>();
        if (start == null) {
            start = LocalDate.parse("2021-01-01");
        }
        if (end == null) {
            end = LocalDate.parse("3021-01-01");
        }
        map.put("start", start);
        map.put("end", end);
        return map;
    }

}
