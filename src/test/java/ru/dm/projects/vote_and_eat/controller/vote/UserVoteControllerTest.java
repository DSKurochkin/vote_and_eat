package ru.dm.projects.vote_and_eat.controller.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.dm.projects.vote_and_eat.controller.AbstractControllerTest;
import ru.dm.projects.vote_and_eat.model.Vote;
import ru.dm.projects.vote_and_eat.service.UserService;
import ru.dm.projects.vote_and_eat.service.VoteService;
import ru.dm.projects.vote_and_eat.to.VoteTo;
import ru.dm.projects.vote_and_eat.util.DateTimeUtil;
import ru.dm.projects.vote_and_eat.util.json.JsonUtil;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.dm.projects.vote_and_eat.test_data.RestaurantTestData.assertRestaurant;
import static ru.dm.projects.vote_and_eat.test_data.UserTestData.setPassToActual;
import static ru.dm.projects.vote_and_eat.test_data.VoteTestData.*;
import static ru.dm.projects.vote_and_eat.util.TestUtil.assertMvcResult;
import static ru.dm.projects.vote_and_eat.util.json.JsonUtil.readFromJson;

public class UserVoteControllerTest extends AbstractControllerTest {
    @Autowired
    VoteService voteService;
    @Autowired
    UserService userService;

    @Test
    void toVote() throws Exception {
        VoteTo voteTo = new VoteTo(1L, 1L);
        ResultActions action = perform(MockMvcRequestBuilders.post(AbstractVoteController.VOTE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(voteTo)));
//                .with(userHttpBasic(user)));
        if (DateTimeUtil.getNow().isBefore(DateTimeUtil.getTime("11:00"))) {
            Vote created = readFromJson(action, Vote.class);
            Vote fromDb = voteService.get(created.getId());
            assertVote(created, fromDb);
            assertRestaurant(created.getRestaurant(), fromDb.getRestaurant());
            setPassToActual(created.getUser(), fromDb.getUser());
            assertUser(created.getUser(), fromDb.getUser());
        }

    }


    @Test
    void getResult() throws Exception {
        userService.createOrUpdate(testUser);
        userService.createOrUpdate(lateVoteUser);
        newVotes.forEach(v-> voteService.createOrUpdate(v));
        perform(MockMvcRequestBuilders.get(AbstractVoteController.VOTE_URL+"/result"))
//                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertMvcResult(resultMap));
    }

//    @Test
//    void toVoteAfterPermittedTime() throws Exception {
//        Vote newVote = lateVote;
//        ResultActions action = perform(MockMvcRequestBuilders.post(AbstractVoteController.VOTE_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(JsonUtil.writeValue(newVote)));
////                .with(userHttpBasic(user)));
//
//        //expected Exception!!
//    }


}

