package ru.dm.projects.vote_and_eat.controller.dish;

import javassist.NotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.dm.projects.vote_and_eat.controller.AbstractControllerTest;
import ru.dm.projects.vote_and_eat.model.Dish;
import ru.dm.projects.vote_and_eat.service.DishService;
import ru.dm.projects.vote_and_eat.util.DishUtil;
import ru.dm.projects.vote_and_eat.util.json.JsonUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.dm.projects.vote_and_eat.controller.dish.AbstractDishController.DISH_URL;
import static ru.dm.projects.vote_and_eat.controller.user.AbstractUserController.ADMIN_URL;
import static ru.dm.projects.vote_and_eat.test_data.DateTimeTestData.*;
import static ru.dm.projects.vote_and_eat.test_data.DishTestData.*;
import static ru.dm.projects.vote_and_eat.test_data.RestaurantTestData.restaurant1;
import static ru.dm.projects.vote_and_eat.test_data.UserTestData.admin;
import static ru.dm.projects.vote_and_eat.test_data.UserTestData.user1;
import static ru.dm.projects.vote_and_eat.util.DateTimeUtil.useMockTime;
import static ru.dm.projects.vote_and_eat.util.DateTimeUtil.useSystemDefaultClock;
import static ru.dm.projects.vote_and_eat.util.TestUtil.*;
import static ru.dm.projects.vote_and_eat.util.json.JsonUtil.readFromJson;

public class DishControllerTest extends AbstractControllerTest {
    private final String ADMIN_DISH_URL = ADMIN_URL + "/dishes";
    private final String USER_DISH_URL = "/dishes";

    @Autowired
    private DishService dishService;

    @AfterEach
    void trowOffClock() {
        useSystemDefaultClock();
    }


    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(ADMIN_DISH_URL + "/" + FIRST_DISH_ID + 1)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertMvcResult(dish2));
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(ADMIN_DISH_URL + NOT_FOUND)
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void create() throws Exception {
        useMockTime(goodTestTimeToChangeDish);
        ResultActions action = perform(MockMvcRequestBuilders.post(ADMIN_DISH_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(dishTo))
                .with(userHttpBasic(admin)));

        Dish created = readFromJson(action, Dish.class);
        Long newId = created.getId();
        Dish newDish = createFromTo(dishTo, restaurant1);
        newDish.setId(newId);
        assertDish(newDish, created);
        assertDish(newDish, dishService.get(newId));
    }

    @Test
    void update() throws Exception {
        useMockTime(goodTestTimeToChangeDish);
        perform(MockMvcRequestBuilders.put(ADMIN_DISH_URL + "/" + FIRST_DISH_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());
        assertDish(dishService.get(FIRST_DISH_ID), DishUtil.updateFromTo(dish1, updated));

    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(ADMIN_DISH_URL + "/" + FIRST_DISH_ID)
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> dishService.get(FIRST_DISH_ID));
    }

    @Test
    void getByRestaurantName() throws Exception {
        perform(MockMvcRequestBuilders.get(ADMIN_DISH_URL
                + "/byRestaurantName"
                + "?name=Restaurant A&start=2021-09-01&end=2021-09-01")
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertMvcResult(List.of(dish1, dish2, dish3)));
    }

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(ADMIN_DISH_URL + "/" + FIRST_DISH_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getForbidden() throws Exception {
        perform(MockMvcRequestBuilders.get(ADMIN_DISH_URL + "/" + FIRST_DISH_ID)
                .with(userHttpBasic(user1)))
                .andExpect(status().isForbidden());
    }

    @Test
    void createInUnsupportedDate() throws Exception {
        useMockTime(badTestDate);
        perform(MockMvcRequestBuilders.post(ADMIN_DISH_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin))
                .content(JsonUtil.writeValue(dishTo)))
                .andExpect(status().isUnprocessableEntity());


    }

    @Test
    void updateInUnsupportedTime() throws Exception {
        useMockTime(lateTestTime);
        perform(MockMvcRequestBuilders.put(ADMIN_DISH_URL + "/" + FIRST_DISH_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void dishesForToday() throws Exception {
        useMockTime(dbTestDateTime);
        ResultActions actions = perform(MockMvcRequestBuilders.get(DISH_URL)
                .with(userHttpBasic(user1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertMvcResult(testMenu));
    }
}
