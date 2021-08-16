package ru.dm.projects.vote_and_eat.controller.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.dm.projects.vote_and_eat.controller.AbstractControllerTest;
import ru.dm.projects.vote_and_eat.model.Vote;
import ru.dm.projects.vote_and_eat.service.VoteService;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.dm.projects.vote_and_eat.controller.user.AbstractUserController.ADMIN_URL;
import static ru.dm.projects.vote_and_eat.controller.vote.AbstractVoteController.VOTE_URL;
import static ru.dm.projects.vote_and_eat.test_data.UserTestData.user1;
import static ru.dm.projects.vote_and_eat.test_data.VoteTestData.*;
import static ru.dm.projects.vote_and_eat.util.TestUtil.assertMvcResult;

public class AdminVoteControllerTest extends AbstractControllerTest {

    private final String ADMIN_VOTE_URL = ADMIN_URL + VOTE_URL;

    @Autowired
    private VoteService service;
    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(ADMIN_VOTE_URL))
//            .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertMvcResult(votes));
    }
    @Test
    void getBetween() throws Exception  {
//        perform(MockMvcRequestBuilders.get(ADMIN_VOTE_URL+"/filter")
//                .param()

    }
    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(ADMIN_VOTE_URL + "/" + FIRST_VOTE_ID))
//                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertMvcResult(vote1));

    }
    @Test
    void getByUserEmail() throws Exception {
        Vote testVoteOfUser1 = getNew();
        service.createOrUpdate(testVoteOfUser1);
        perform(MockMvcRequestBuilders.get(ADMIN_VOTE_URL + "/by?email=" + user1.getEmail()))
//                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertMvcResult(List.of(vote1, testVoteOfUser1)));

    }

}
