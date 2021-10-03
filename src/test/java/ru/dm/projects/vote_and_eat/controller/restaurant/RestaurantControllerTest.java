package ru.dm.projects.vote_and_eat.controller.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.dm.projects.vote_and_eat.controller.AbstractControllerTest;
import ru.dm.projects.vote_and_eat.model.Restaurant;
import ru.dm.projects.vote_and_eat.service.RestaurantService;
import ru.dm.projects.vote_and_eat.util.exception.ErrorType;
import ru.dm.projects.vote_and_eat.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.dm.projects.vote_and_eat.controller.restaurant.RestaurantController.RESTAURANT_URL;
import static ru.dm.projects.vote_and_eat.test_data.RestaurantTestData.*;
import static ru.dm.projects.vote_and_eat.test_data.UserTestData.admin;
import static ru.dm.projects.vote_and_eat.test_data.UserTestData.user1;
import static ru.dm.projects.vote_and_eat.util.TestUtil.*;
import static ru.dm.projects.vote_and_eat.util.json.JsonUtil.readFromJson;
import static ru.dm.projects.vote_and_eat.util.json.JsonUtil.writeValue;


public class RestaurantControllerTest extends AbstractControllerTest {

    @Autowired
    private RestaurantService restaurantService;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(RESTAURANT_URL + "/" + FIRST_RESTAURANT_ID)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertMvcResult(restaurant1));
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(RESTAURANT_URL + NOT_FOUND)
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(RESTAURANT_URL)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertMvcResult(restaurants));
    }


    @Test
    void create() throws Exception {
        Restaurant newRestaurant = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(RESTAURANT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(newRestaurant))
                .with(userHttpBasic(admin)));
        Restaurant created = readFromJson(action, Restaurant.class);
        Long newId = created.getId();
        newRestaurant.setId(newId);
        assertRestaurant(newRestaurant, created);
        assertRestaurant(newRestaurant, restaurantService.get(newId));
    }

    @Test
    void update() throws Exception {
        Restaurant updated = restaurantService.get(FIRST_RESTAURANT_ID);
        updated.setName("ChangedName");
        perform(MockMvcRequestBuilders.put(RESTAURANT_URL + "/" + FIRST_RESTAURANT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin))
                .content(writeValue(updated)))
                .andExpect(status().isNoContent());
        assertRestaurant(restaurantService.get(FIRST_RESTAURANT_ID), updated);

    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(RESTAURANT_URL + "/" + FIRST_RESTAURANT_ID)
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> restaurantService.get(FIRST_RESTAURANT_ID));

    }

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(RESTAURANT_URL + "/" + FIRST_RESTAURANT_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getForbidden() throws Exception {
        perform(MockMvcRequestBuilders.get(RESTAURANT_URL + "/" + FIRST_RESTAURANT_ID)
                .with(userHttpBasic(user1)))
                .andExpect(status().isForbidden());
    }


    @Test
    void createInvalid() throws Exception {
        Restaurant invalid = new Restaurant(null, "Y");
        perform(MockMvcRequestBuilders.post(RESTAURANT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(invalid))
                .with(userHttpBasic(admin)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(ErrorType.VALIDATION_ERROR));
    }

    @Test
    void updateInvalid() throws Exception {
        Restaurant invalid = new Restaurant(null, "");
        perform((MockMvcRequestBuilders.put(RESTAURANT_URL + "/" + FIRST_RESTAURANT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(invalid))
                .with(userHttpBasic(admin))))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(ErrorType.VALIDATION_ERROR));
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void createDuplicateName() throws Exception {
        Restaurant duplicate = new Restaurant(null, restaurant1.getName());
        perform(MockMvcRequestBuilders.post(RESTAURANT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(duplicate))
                .with(userHttpBasic(admin)))
                .andExpect(status().isConflict())
                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
                .andExpect(checkDetailMessage("exception.restaurant.duplicateName"));

    }

}
