package ru.dm.projects.vote_and_eat.test_data;

import ru.dm.projects.vote_and_eat.model.Restaurant;
import ru.dm.projects.vote_and_eat.model.Role;
import ru.dm.projects.vote_and_eat.model.User;
import ru.dm.projects.vote_and_eat.model.Vote;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.dm.projects.vote_and_eat.test_data.RestaurantTestData.restaurant1;
import static ru.dm.projects.vote_and_eat.test_data.RestaurantTestData.restaurant2;
import static ru.dm.projects.vote_and_eat.test_data.UserTestData.*;
import static ru.dm.projects.vote_and_eat.util.DateTimeUtil.*;


public class VoteTestData {
    public static final Long FIRST_VOTE_ID = 1L;

    public static final Vote vote1
            = new Vote(FIRST_VOTE_ID, getDate("2021-09-01"), getTime("09:00:00"), restaurant1, user1);
    public static final Vote vote2
            = new Vote(FIRST_VOTE_ID + 1, getDate("2021-09-01"), getTime("08:00:00"), restaurant2, user2);
    public static final User testUser = new User(FIRST_USER_ID + 2, "testUser", "testUser@vote.com", "testUser@vote.com", Role.USER);
    public static final User lateVoteUser = new User(FIRST_USER_ID + 3, "lateVoteUser", "lateVoteUser@vote.com", "testUser@vote.com", Role.USER);
    public static final Map<Integer, Restaurant> resultMap = new LinkedHashMap<>();
    public static List<Vote> votes = List.of(vote1, vote2);
    public static Vote todayVote1 = new Vote(null, today(), getTime("09:00:00"), restaurant1, user1);
    public static Vote todayVote2 = new Vote(null, today(), getTime("08:00:00"), restaurant2, user2);
    public static Vote todayVote3 = new Vote(null, today(), getTime("10:00:00"), restaurant1, testUser);
    public static Vote lateVote = new Vote(null, today(), getTime("12:00:00"), restaurant2, lateVoteUser);
    public static List<Vote> newVotes = List.of(todayVote1, todayVote2, todayVote3);

    static {
        resultMap.put(2, restaurant1);
        resultMap.put(1, restaurant2);
    }

    public static Vote getNew() {
        return todayVote1;
    }

    public static <T> void assertUser(T actual, T expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("votes").isEqualTo(expected);
    }

    public static <T> void assertVote(T actual, T expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("restaurant", "user").isEqualTo(expected);
    }


}