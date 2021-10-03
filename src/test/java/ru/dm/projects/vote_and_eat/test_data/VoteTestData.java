package ru.dm.projects.vote_and_eat.test_data;

import ru.dm.projects.vote_and_eat.model.Role;
import ru.dm.projects.vote_and_eat.model.User;
import ru.dm.projects.vote_and_eat.model.Vote;
import ru.dm.projects.vote_and_eat.to.RestaurantTo;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.dm.projects.vote_and_eat.model.AbstractBaseEntity.START_SEQ;
import static ru.dm.projects.vote_and_eat.test_data.RestaurantTestData.restaurant1;
import static ru.dm.projects.vote_and_eat.test_data.RestaurantTestData.restaurant2;
import static ru.dm.projects.vote_and_eat.test_data.UserTestData.*;
import static ru.dm.projects.vote_and_eat.util.DateTimeUtil.*;


public class VoteTestData {
    public static final long FIRST_VOTE_ID = START_SEQ + 14;

    public static final Vote vote1
            = new Vote(FIRST_VOTE_ID, getDate("2021-09-01"), getTime("09:00:00"), restaurant1, user1);
    public static final Vote vote2
            = new Vote(FIRST_VOTE_ID + 1, getDate("2021-09-01"), getTime("07:59:00"), restaurant1, user3);
    public static final Vote vote3
            = new Vote(FIRST_VOTE_ID + 2, getDate("2021-09-02"), getTime("07:30:00"), restaurant2, user1);
    public static final Vote vote4
            = new Vote(FIRST_VOTE_ID + 2, getDate("2021-09-02"), getTime("07:30:00"), restaurant2, user1);
    public static final Vote vote5
            = new Vote(FIRST_VOTE_ID + 3, getDate("2021-09-02"), getTime("08:30:00"), restaurant1, user2);
    public static final Vote vote6
            = new Vote(FIRST_VOTE_ID + 4, getDate("2021-09-02"), getTime("08:20:00"), restaurant1, user3);
    public static final User lateVoteUser = new User(FIRST_USER_ID + 3, "lateVoteUser", "lateVoteUser@vote.com", "testUser@vote.com", Role.USER);
    private static final User testUser = new User(null, "testUser", "testUser@vote.com", "testUser@vote.com", Role.USER);
    public static Vote todayVote1 = new Vote(null, today(), now().plusMinutes(1), restaurant1, user1);
    public static Vote todayVote2 = new Vote(null, today(), now().plusMinutes(2), restaurant2, user2);
    public static Vote todayVote3 = new Vote(null, today(), now().plusMinutes(3), restaurant1, user3);
    public static List<Vote> newVotes = List.of(todayVote1, todayVote2, todayVote3);
    public static List<Vote> votes = List.of(vote1, vote2, vote3, vote4);

    public static RestaurantTo restaurantTo1 = new RestaurantTo(restaurant1);
    public static RestaurantTo restaurantTo2 = new RestaurantTo(restaurant2);


    public static <T> void assertUser(T actual, T expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("votes").isEqualTo(expected);
    }

    public static <T> void assertVote(T actual, T expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("restaurant", "user").isEqualTo(expected);
    }

    public static User getTestUser() {
        if (testUser.getId() != null) {
            testUser.setId(null);
        }
        return testUser;
    }

}
