package ru.dm.projects.vote_and_eat.test_data;

import ru.dm.projects.vote_and_eat.model.Role;
import ru.dm.projects.vote_and_eat.model.User;
import ru.dm.projects.vote_and_eat.to.UserTo;
import ru.dm.projects.vote_and_eat.util.json.JsonUtil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.dm.projects.vote_and_eat.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final long ADMIN_ID = START_SEQ;
    public static final long FIRST_USER_ID = START_SEQ + 1;

    public static final User admin = new User(ADMIN_ID, "Admin", "admin@vote.com", "admin", Role.ADMIN);
    public static final User user1 = new User(FIRST_USER_ID, "User1", "user1@vote.com", "user1", Role.USER);
    public static final User user2 = new User(FIRST_USER_ID + 1, "User2", "user2@vote.com", "user2", Role.USER);
    public static final User user3 = new User(FIRST_USER_ID + 2, "User3", "user3@vote.com", "user3", Role.USER);

    public static List<User> users = List.of(admin, user1, user2, user3);

    public static User getNew() {
        return new User(null, "Testuser", "testuser@vote.com", "testuser@vote.com", Role.USER);
    }

    public static UserTo getNewTo() {
        return new UserTo(null, "Testuser", "testuser@vote.com", "testuser@vote.com");
    }

    public static <User> void assertUser(User actual, User expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("votes").isEqualTo(expected);
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }

    public static void setPassToActual(User actual, User expected) {
        actual.setPassword(expected.getPassword());
    }
}
