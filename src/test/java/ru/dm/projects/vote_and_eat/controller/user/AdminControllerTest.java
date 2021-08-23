package ru.dm.projects.vote_and_eat.controller.user;

import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.dm.projects.vote_and_eat.controller.AbstractControllerTest;
import ru.dm.projects.vote_and_eat.model.Role;
import ru.dm.projects.vote_and_eat.model.User;
import ru.dm.projects.vote_and_eat.service.UserService;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.dm.projects.vote_and_eat.controller.user.AbstractUserController.ADMIN_URL;
import static ru.dm.projects.vote_and_eat.test_data.UserTestData.*;
import static ru.dm.projects.vote_and_eat.util.TestUtil.assertMvcResult;
import static ru.dm.projects.vote_and_eat.util.json.JsonUtil.readFromJson;
import static ru.dm.projects.vote_and_eat.test_data.UserTestData.jsonWithPassword;

public class AdminControllerTest extends AbstractControllerTest {
    private final String ADMIN_USER_URL = ADMIN_URL + "/users";

    @Autowired
    private UserService service;


    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(ADMIN_USER_URL +"/"+ FIRST_USER_ID))
//                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertMvcResult(user1));
    }


    @Test
    void create() throws Exception {
        User newUser= getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(ADMIN_USER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithPassword(newUser, "testuser@vote.com")));
//                .with(userHttpBasic(user)));
//        Long newId = readFromJson(action, User.class).getId();
//        newUser.setId(newId);
//        assertUser(newUser, service.get(newId));

        User db = readFromJson(action, User.class);
        Long newId = db.getId();
        db.setPassword(newUser.getPassword());
        newUser.setId(newId);
        assertUser(newUser, service.get(newId));
        assertUser(db, service.get(newId));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(ADMIN_USER_URL + "/" + FIRST_USER_ID))
//                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> service.get(FIRST_USER_ID));

    }
    @Test
    void update() throws Exception {
        User updated= new User(FIRST_USER_ID,  "Testuser", "testuser@vote.com","testuser@vote.com", Role.USER);
        perform(MockMvcRequestBuilders.put(ADMIN_USER_URL + "/"+FIRST_USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
//                .with(userHttpBasic(admin))
                .content(jsonWithPassword(updated, "testuser@vote.com")))
                .andExpect(status().isNoContent());
        assertUser(service.get(FIRST_USER_ID), updated);

    }
}
