package ru.dm.projects.vote_and_eat.controller.restaurant;

import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.dm.projects.vote_and_eat.controller.AbstractControllerTest;
import ru.dm.projects.vote_and_eat.model.Restaurant;
import ru.dm.projects.vote_and_eat.service.RestaurantService;
import ru.dm.projects.vote_and_eat.util.json.JsonUtil;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.dm.projects.vote_and_eat.controller.user.AbstractUserController.ADMIN_URL;
import static ru.dm.projects.vote_and_eat.test_data.RestaurantTestData.*;
import static ru.dm.projects.vote_and_eat.util.TestUtil.assertMvcResult;
import static ru.dm.projects.vote_and_eat.util.json.JsonUtil.readFromJson;
//import static ru.dm.projects.vote_and_eat.util.TestUtil.assertMvcResult;

public class RestaurantControllerTest extends AbstractControllerTest {
    private final String RESTAURANT_URL = ADMIN_URL + "/restaurants";

    @Autowired
    private RestaurantService service;


    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(RESTAURANT_URL +"/"+ FIRST_RESTAURANT_ID))
//                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertMvcResult(restaurant1));
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(RESTAURANT_URL))
//                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertMvcResult(restaurants));
    }
    //assertEquals(content(), writeValue(restaurants))

    @Test
    void create() throws Exception {
        Restaurant newRestaurant= getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(RESTAURANT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newRestaurant)));
//                .with(userHttpBasic(user)));

        Restaurant created = readFromJson(action, Restaurant.class);
        Long newId = created.getId();
        newRestaurant.setId(newId);
        assertRestaurant(newRestaurant, created );
        assertRestaurant(newRestaurant, service.get(newId));
    }

    @Test
    void update() throws Exception {
        Restaurant updated= service.get(FIRST_RESTAURANT_ID);
        updated.setName("ChangedName");
        perform(MockMvcRequestBuilders.put(RESTAURANT_URL + "/"+FIRST_RESTAURANT_ID)
                .contentType(MediaType.APPLICATION_JSON)
//                .with(userHttpBasic(admin))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());
        assertRestaurant(service.get(FIRST_RESTAURANT_ID), updated);

    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(RESTAURANT_URL + "/"+FIRST_RESTAURANT_ID))
//                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> service.get(FIRST_RESTAURANT_ID));

    }


    ////////////////

    @Test
    void unAdminAuth(){


    }

    @Test
    void createWithInvalid(){}
//after ExceptHandler
//    @Test
//    void getNotFound() throws Exception {
//        perform(MockMvcRequestBuilders.get(RESTAURANT_URL + NOT_FOUND))
////                .with(userHttpBasic(admin)))
//                .andDo(print())
//                .andExpect(status().isUnprocessableEntity());
//    }
}
