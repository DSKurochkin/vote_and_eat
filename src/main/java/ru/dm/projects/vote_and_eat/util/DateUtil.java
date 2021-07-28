package ru.dm.projects.vote_and_eat.util;

import ru.dm.projects.vote_and_eat.model.Vote;

import java.time.LocalTime;

public class DateUtil {

    public static boolean isPermittedVote(Vote vote) {
        if (vote.getTime().isBefore(LocalTime.parse("11:00"))) {
            return true;
        }
        throw new RuntimeException("??????????");
    }
}
