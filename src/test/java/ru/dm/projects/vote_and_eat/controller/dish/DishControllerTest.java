package ru.dm.projects.vote_and_eat.controller.dish;

import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.dm.projects.vote_and_eat.controller.AbstractControllerTest;
import ru.dm.projects.vote_and_eat.to.DishTo;
import ru.dm.projects.vote_and_eat.util.DishUtil;
import ru.dm.projects.vote_and_eat.util.json.JsonUtil;
import ru.dm.projects.vote_and_eat.model.Dish;
import ru.dm.projects.vote_and_eat.service.DishService;
import ru.dm.projects.vote_and_eat.test_data.DishTestData;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.dm.projects.vote_and_eat.util.json.JsonUtil.readFromJson;
import static ru.dm.projects.vote_and_eat.test_data.DishTestData.*;
import static ru.dm.projects.vote_and_eat.controller.user.AbstractUserController.ADMIN_URL;
import static ru.dm.projects.vote_and_eat.util.TestUtil.assertMvcResult;

public class DishControllerTest extends AbstractControllerTest {
    private final String ADMIN_DISH_URL = ADMIN_URL + "/dishes";
    private final String USER_DISH_URL = "/dishes";

    @Autowired
    private DishService service;


    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(ADMIN_DISH_URL +"/"+ FIRST_DISH_ID))
//                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertMvcResult(dish1));
    }


    @Test
    void create() throws Exception {
        Dish newDish= DishTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(ADMIN_DISH_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish)));
//                .with(userHttpBasic(user)));

        Dish created = readFromJson(action, Dish.class);
        Long newId = created.getId();
        newDish.setId(newId);
        assertDish(newDish, created );
        assertDish(newDish, service.get(newId));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(ADMIN_DISH_URL + "/"+FIRST_DISH_ID))
//                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> service.get(FIRST_DISH_ID));

    }
    @Test
    void update() throws Exception {
        DishTo updated= new DishTo(FIRST_DISH_ID,  "Test Dish",LocalDate.now(), 10);
        perform(MockMvcRequestBuilders.put(ADMIN_DISH_URL + "/"+FIRST_DISH_ID)
                .contentType(MediaType.APPLICATION_JSON)
//                .with(userHttpBasic(admin))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());
        assertDish(service.get(FIRST_DISH_ID), DishUtil.updateFromTo(dish1, updated));

    }

    void dishesForToday(){

    }
}
