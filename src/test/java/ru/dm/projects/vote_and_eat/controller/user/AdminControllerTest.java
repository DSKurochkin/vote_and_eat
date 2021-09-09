package ru.dm.projects.vote_and_eat.controller.user;

import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.dm.projects.vote_and_eat.model.Role;
import ru.dm.projects.vote_and_eat.model.User;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.dm.projects.vote_and_eat.controller.user.AbstractUserController.ADMIN_URL;
import static ru.dm.projects.vote_and_eat.test_data.DishTestData.FIRST_DISH_ID;
import static ru.dm.projects.vote_and_eat.test_data.UserTestData.*;
import static ru.dm.projects.vote_and_eat.util.TestUtil.*;
import static ru.dm.projects.vote_and_eat.util.json.JsonUtil.readFromJson;

public class AdminControllerTest extends AbstractUserControllerTest {
    private final String ADMIN_USER_URL = ADMIN_URL + "/users";

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(ADMIN_USER_URL + "/" + FIRST_USER_ID)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertMvcResult(user1));
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(ADMIN_URL + NOT_FOUND)
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }


    @Test
    void create() throws Exception {
        User newUser = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(ADMIN_USER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithPassword(newUser, "testuser@vote.com"))
                .with(userHttpBasic(admin)));
        User db = readFromJson(action, User.class);
        Long newId = db.getId();
        db.setPassword(newUser.getPassword());
        newUser.setId(newId);
        assertUser(newUser, userService.get(newId));
        assertUser(db, userService.get(newId));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(ADMIN_USER_URL + "/" + FIRST_USER_ID)
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> userService.get(FIRST_USER_ID));

    }

    @Test
    void update() throws Exception {
        User updated = new User(FIRST_USER_ID, "Testuser", "testuser@vote.com", "testuser@vote.com", Role.USER);
        perform(MockMvcRequestBuilders.put(ADMIN_USER_URL + "/" + FIRST_USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin))
                .content(jsonWithPassword(updated, "testuser@vote.com")))
                .andExpect(status().isNoContent());
        assertUser(userService.get(FIRST_USER_ID), updated);

    }

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(ADMIN_URL + "/" + FIRST_DISH_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getForbidden() throws Exception {
        perform(MockMvcRequestBuilders.get(ADMIN_URL + "/" + FIRST_DISH_ID)
                .with(userHttpBasic(user1)))
                .andExpect(status().isForbidden());
    }
}
