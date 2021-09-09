package ru.dm.projects.vote_and_eat.controller.user;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.dm.projects.vote_and_eat.model.User;
import ru.dm.projects.vote_and_eat.to.UserTo;
import ru.dm.projects.vote_and_eat.util.json.JsonUtil;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.dm.projects.vote_and_eat.controller.user.AbstractUserController.ADMIN_URL;
import static ru.dm.projects.vote_and_eat.controller.user.ProfileController.PROFILE_URL;
import static ru.dm.projects.vote_and_eat.test_data.UserTestData.*;
import static ru.dm.projects.vote_and_eat.util.TestUtil.userHttpBasic;
import static ru.dm.projects.vote_and_eat.util.UserUtil.createNewFromTo;
import static ru.dm.projects.vote_and_eat.util.UserUtil.updateFromTo;
import static ru.dm.projects.vote_and_eat.util.json.JsonUtil.readFromJson;

public class ProfileControllerTest extends AbstractUserControllerTest {


    @Test
    void create() throws Exception {
        UserTo newTo = getNewTo();
        User newUser = createNewFromTo(newTo);
        ResultActions action = perform(MockMvcRequestBuilders.post(PROFILE_URL + "/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newTo)))
                .andDo(print())
                .andExpect(status().isCreated());

        User created = readFromJson(action, User.class);
        Long newId = created.getId();
        newUser.setId(newId);
        assertUser(newUser, userService.get(newId));
    }

    @Test
    void update() throws Exception {
        UserTo toForUpdate = new UserTo(FIRST_USER_ID, "Testuser", "testuser@vote.com", "testuser@vote.com");
        perform(MockMvcRequestBuilders.put(PROFILE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(user1))
                .content(JsonUtil.writeValue(toForUpdate)))
                .andExpect(status().isNoContent());
        assertUser(userService.get(FIRST_USER_ID), updateFromTo(new User(user1), toForUpdate));

    }

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(ADMIN_URL))
                .andExpect(status().isUnauthorized());
    }
}
