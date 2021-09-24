package ru.dm.projects.vote_and_eat.controller.vote;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.dm.projects.vote_and_eat.controller.user.AbstractUserController.ADMIN_URL;
import static ru.dm.projects.vote_and_eat.controller.vote.AbstractVoteController.VOTE_URL;
import static ru.dm.projects.vote_and_eat.test_data.UserTestData.admin;
import static ru.dm.projects.vote_and_eat.test_data.UserTestData.user1;
import static ru.dm.projects.vote_and_eat.test_data.VoteTestData.*;
import static ru.dm.projects.vote_and_eat.util.DateTimeUtil.getStringFromDate;
import static ru.dm.projects.vote_and_eat.util.DateTimeUtil.today;
import static ru.dm.projects.vote_and_eat.util.TestUtil.*;

public class AdminVoteControllerTest extends AbstractVoteControllerTest {

    private final String ADMIN_VOTE_URL = ADMIN_URL + VOTE_URL;

    @Test
    void getBetween() throws Exception {
        populateVote();
        String date = getStringFromDate(today());
        perform(MockMvcRequestBuilders.get(ADMIN_VOTE_URL + "/filter?" + "start=" + date + "&end=" + date)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertMvcResult(newVotes));

    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(ADMIN_VOTE_URL + NOT_FOUND)
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(ADMIN_VOTE_URL + "/" + FIRST_VOTE_ID)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertMvcResult(vote1));

    }

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(ADMIN_VOTE_URL + "/" + FIRST_VOTE_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getForbidden() throws Exception {
        perform(MockMvcRequestBuilders.get(ADMIN_VOTE_URL + "/" + FIRST_VOTE_ID)
                .with(userHttpBasic(user1)))
                .andExpect(status().isForbidden());
    }

}
