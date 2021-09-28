package ru.dm.projects.vote_and_eat.util;

import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import ru.dm.projects.vote_and_eat.model.User;
import ru.dm.projects.vote_and_eat.util.json.JsonUtil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUtil {
    public static final String NOT_FOUND = "/404";

    public static <T> ResultMatcher assertMvcResult(List<T> list) {
        return new ResultMatcher() {
            @Override
            public void match(MvcResult mvcResult) throws Exception {
                assertEquals(JsonUtil.writeValue(list), mvcResult.getResponse().getContentAsString());
            }
        };
    }

    public static <T> ResultMatcher assertMvcResult(T t) {
        return new ResultMatcher() {
            @Override
            public void match(MvcResult mvcResult) throws Exception {
                assertEquals(JsonUtil.writeValue(t), mvcResult.getResponse().getContentAsString());
            }
        };
    }

    public static RequestPostProcessor userHttpBasic(User user) {
        return SecurityMockMvcRequestPostProcessors.httpBasic(user.getEmail(), user.getPassword());
    }

    public static <T> void assertEntity(T actual, T expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
