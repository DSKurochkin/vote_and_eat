package ru.dm.projects.vote_and_eat.controller.user;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.dm.projects.vote_and_eat.model.Role;
import ru.dm.projects.vote_and_eat.model.User;
import ru.dm.projects.vote_and_eat.util.exception.ErrorType;
import ru.dm.projects.vote_and_eat.util.exception.NotFoundException;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.dm.projects.vote_and_eat.controller.user.AdminController.ADMIN_USERS_URL;
import static ru.dm.projects.vote_and_eat.test_data.UserTestData.*;
import static ru.dm.projects.vote_and_eat.util.TestUtil.*;
import static ru.dm.projects.vote_and_eat.util.json.JsonUtil.readFromJson;

public class AdminControllerTest extends AbstractUserControllerTest {


    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(ADMIN_USERS_URL)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertMvcResult(users));
    }


    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(ADMIN_USERS_URL + NOT_FOUND)
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }


    @Test
    void create() throws Exception {
        User newUser = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(ADMIN_USERS_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithPassword(newUser, "testuser@vote.com"))
                .with(userHttpBasic(admin)));
        User db = readFromJson(action, User.class);
        Long newId = db.getId();
        db.setPassword(newUser.getPassword());
        newUser.setId(newId);
        assertEntity(newUser, userService.get(newId));
        assertEntity(db, userService.get(newId));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(ADMIN_USERS_URL + "/" + FIRST_USER_ID)
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> userService.get(FIRST_USER_ID));

    }

    @Test
    void update() throws Exception {
        User updated = new User(FIRST_USER_ID, "Testuser", "testuser@vote.com", "testuser@vote.com", Role.USER);
        perform(MockMvcRequestBuilders.put(ADMIN_USERS_URL + "/" + FIRST_USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin))
                .content(jsonWithPassword(updated, "testuser@vote.com")))
                .andExpect(status().isNoContent());
        assertEntity(userService.get(FIRST_USER_ID), updated);

    }

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(ADMIN_USERS_URL + "/" + FIRST_USER_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getForbidden() throws Exception {
        perform(MockMvcRequestBuilders.get(ADMIN_USERS_URL + "/" + FIRST_USER_ID)
                .with(userHttpBasic(user1)))
                .andExpect(status().isForbidden());
    }

    @Test
    void createInvalid() throws Exception {
        User invalid = new User(null, "TestName", "invalid email", "password", true, Set.of(Role.USER));
        perform(MockMvcRequestBuilders.post(ADMIN_USERS_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithPassword(invalid, "password"))
                .with(userHttpBasic(admin)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(ErrorType.VALIDATION_ERROR));
    }

    @Test
    void updateInvalid() throws Exception {
        User invalid = new User(FIRST_USER_ID, "Y", "test@test.test", "x", true, Set.of(Role.USER));
        perform((MockMvcRequestBuilders.put(ADMIN_USERS_URL + "/" + FIRST_USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithPassword(invalid, "x"))
                .with(userHttpBasic(admin))))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(ErrorType.VALIDATION_ERROR));
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void createDuplicateEmail() throws Exception {
        User duplicate = new User(null, "TestName", "user1@vote.com", "password", true, Set.of(Role.USER));
        perform(MockMvcRequestBuilders.post(ADMIN_USERS_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithPassword(duplicate, "password"))
                .with(userHttpBasic(admin)))
                .andExpect(status().isConflict())
                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
                .andExpect(checkDetailMessage("exception.user.duplicateEmail"));

    }
}
