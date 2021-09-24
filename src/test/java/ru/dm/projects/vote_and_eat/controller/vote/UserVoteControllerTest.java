package ru.dm.projects.vote_and_eat.controller.vote;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.dm.projects.vote_and_eat.model.Vote;
import ru.dm.projects.vote_and_eat.to.VoteTo;
import ru.dm.projects.vote_and_eat.util.json.JsonUtil;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.dm.projects.vote_and_eat.controller.vote.UserVoteController.USER_VOTE_URL;
import static ru.dm.projects.vote_and_eat.test_data.DateTimeTestData.*;
import static ru.dm.projects.vote_and_eat.test_data.RestaurantTestData.assertRestaurant;
import static ru.dm.projects.vote_and_eat.test_data.UserTestData.setPassToActual;
import static ru.dm.projects.vote_and_eat.test_data.UserTestData.user1;
import static ru.dm.projects.vote_and_eat.test_data.VoteTestData.*;
import static ru.dm.projects.vote_and_eat.util.DateTimeUtil.useMockTime;
import static ru.dm.projects.vote_and_eat.util.DateTimeUtil.useSystemDefaultClock;
import static ru.dm.projects.vote_and_eat.util.TestUtil.assertMvcResult;
import static ru.dm.projects.vote_and_eat.util.TestUtil.userHttpBasic;
import static ru.dm.projects.vote_and_eat.util.exception.ErrorType.OPERATION_TIME_ERROR;
import static ru.dm.projects.vote_and_eat.util.json.JsonUtil.readFromJson;

public class UserVoteControllerTest extends AbstractVoteControllerTest {

    @AfterEach
    void trowOffClock() {
        useSystemDefaultClock();
    }


    @Test
    void toVote() throws Exception {
        useMockTime(goodTestTimeToVote);
        VoteTo voteTo = new VoteTo(1L);
        ResultActions action = perform(MockMvcRequestBuilders.post(USER_VOTE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(voteTo))
                .with(userHttpBasic(user1)));
        Vote created = readFromJson(action, Vote.class);
        Vote fromDb = voteService.get(created.getId());
        assertVote(created, fromDb);
        assertRestaurant(created.getRestaurant(), fromDb.getRestaurant());
        setPassToActual(created.getUser(), fromDb.getUser());
        assertUser(created.getUser(), fromDb.getUser());
    }

    @Test
    void toVoteUnAuth() throws Exception {
        VoteTo voteTo = new VoteTo(2L);
        perform(MockMvcRequestBuilders.post(USER_VOTE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(voteTo)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void voteAfterPermittedTime() throws Exception {
        useMockTime(lateTestTime);
        VoteTo newVote = new VoteTo(1L);
        perform(MockMvcRequestBuilders.post(USER_VOTE_URL)
                .with(userHttpBasic(user1))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVote)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(OPERATION_TIME_ERROR));
    }

    @Test
    void getResult() throws Exception {
        populateVote();
        useMockTime(goodTestTimeToLookResult);
        perform(MockMvcRequestBuilders.get(USER_VOTE_URL + "/result")
                .with(userHttpBasic(user1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertMvcResult(resultMap));
    }

    @Test
    void getResultBeforeEndOfVote() throws Exception {
        populateVote();
        perform(MockMvcRequestBuilders.get(USER_VOTE_URL + "/result")
                .with(userHttpBasic(user1)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(OPERATION_TIME_ERROR));
    }

    @Test
    void shouldBeLastVoteWhenUserChangeMind() throws Exception {
        int numberOfVotes = voteService.getAll().size();
        useMockTime(goodTestTimeToVote);
        VoteTo firstVote = new VoteTo(1L);
        perform(MockMvcRequestBuilders.post(USER_VOTE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(firstVote))
                .with(userHttpBasic(user1)));
        LocalDateTime newDateTime = goodTestTimeToVote.plusMinutes(10);
        useMockTime(newDateTime);
        VoteTo changeMind = new VoteTo(2L);
        perform(MockMvcRequestBuilders.post(USER_VOTE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(changeMind))
                .with(userHttpBasic(user1)));
        Assertions.assertEquals(numberOfVotes + 1, voteService.getAll().size());
        Long idOfLastVote = voteService.getAll().get(numberOfVotes).getId();
        Assertions.assertEquals(newDateTime.toLocalTime(), voteService.get(idOfLastVote).getTime());
    }

}

