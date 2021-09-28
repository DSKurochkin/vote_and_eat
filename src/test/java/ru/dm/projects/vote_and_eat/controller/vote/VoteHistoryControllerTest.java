package ru.dm.projects.vote_and_eat.controller.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.dm.projects.vote_and_eat.service.RestaurantService;
import ru.dm.projects.vote_and_eat.to.RestaurantTo;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.dm.projects.vote_and_eat.controller.vote.VoteHistoryController.HISTORY_URL;
import static ru.dm.projects.vote_and_eat.test_data.RestaurantTestData.restaurant2;
import static ru.dm.projects.vote_and_eat.test_data.UserTestData.admin;
import static ru.dm.projects.vote_and_eat.test_data.UserTestData.user1;
import static ru.dm.projects.vote_and_eat.test_data.VoteTestData.*;
import static ru.dm.projects.vote_and_eat.util.DateTimeUtil.getStringFromDate;
import static ru.dm.projects.vote_and_eat.util.DateTimeUtil.today;
import static ru.dm.projects.vote_and_eat.util.TestUtil.*;

public class VoteHistoryControllerTest extends AbstractVoteControllerTest {

    @Autowired
    private RestaurantService restaurantService;

    @Test
    void getBetween() throws Exception {
        populateVote();
        String date = getStringFromDate(today());
        perform(MockMvcRequestBuilders.get(HISTORY_URL + "/filter?" + "start=" + date + "&end=" + date)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertMvcResult(newVotes));

    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(HISTORY_URL + NOT_FOUND)
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(HISTORY_URL + "/" + FIRST_VOTE_ID)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertMvcResult(vote1));

    }

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(HISTORY_URL + "/" + FIRST_VOTE_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getForbidden() throws Exception {
        perform(MockMvcRequestBuilders.get(HISTORY_URL + "/" + FIRST_VOTE_ID)
                .with(userHttpBasic(user1)))
                .andExpect(status().isForbidden());
    }

    @Test
    void getRatingBetweenDatesWithDeletedRestaurant() throws Exception {
        restaurantService.delete(restaurant2.getId());
        restaurantTo2.setExist(false);
        Map<RestaurantTo, Integer> map = new LinkedHashMap<>();
        map.put(restaurantTo1, 4);
        map.put(restaurantTo2, 2);
        perform(MockMvcRequestBuilders.get(HISTORY_URL + "/rating?start=2021-09-01&end=2021-09-02")
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertMvcResult(map));
    }

}
