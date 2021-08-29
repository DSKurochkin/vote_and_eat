package ru.dm.projects.vote_and_eat.controller.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.dm.projects.vote_and_eat.controller.AbstractControllerTest;
import ru.dm.projects.vote_and_eat.model.User;
import ru.dm.projects.vote_and_eat.service.UserService;
import ru.dm.projects.vote_and_eat.to.UserTo;
import ru.dm.projects.vote_and_eat.util.json.JsonUtil;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.dm.projects.vote_and_eat.test_data.UserTestData.*;
import static ru.dm.projects.vote_and_eat.controller.user.ProfileController.PROFILE_URL;
import static ru.dm.projects.vote_and_eat.util.TestUtil.userHttpBasic;
import static ru.dm.projects.vote_and_eat.util.UserUtil.updateFromTo;
import static ru.dm.projects.vote_and_eat.util.json.JsonUtil.readFromJson;
import static ru.dm.projects.vote_and_eat.util.UserUtil.createNewFromTo;

public class ProfileControllerTest extends AbstractControllerTest {

    @Autowired
    private UserService service;

    @Test
    void create() throws Exception {
        UserTo newTo=getNewTo();
        User newUser= createNewFromTo(newTo);
        ResultActions action = perform(MockMvcRequestBuilders.post(PROFILE_URL+"/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newTo)))
                .andDo(print())
                .andExpect(status().isCreated());

        User created = readFromJson(action, User.class);
        Long newId = created.getId();
        newUser.setId(newId);
        assertUser(newUser, service.get(newId));
    }

    @Test
    void update() throws Exception {
        UserTo toForUpdate= new UserTo(FIRST_USER_ID,  "Testuser", "testuser@vote.com","testuser@vote.com");
        perform(MockMvcRequestBuilders.put(PROFILE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(user1))
                .content(JsonUtil.writeValue(toForUpdate)))
                .andExpect(status().isNoContent());
        assertUser(service.get(FIRST_USER_ID), updateFromTo(new User(user1), toForUpdate));

    }
}
