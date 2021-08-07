package ru.dm.projects.vote_and_eat.util;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import ru.dm.projects.vote_and_eat.controller.JsonUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUtil {
    public static final Integer NOT_FOUND =404;

    public static <T> ResultMatcher assertMvcResult(List<T> list){
        return new ResultMatcher() {
            @Override
            public void match(MvcResult mvcResult) throws Exception {
                assertEquals(mvcResult.getResponse().getContentAsString(), JsonUtil.writeValue(list));
            }
        };
    }
    public static <T> ResultMatcher assertMvcResult(T t){
        return new ResultMatcher() {
            @Override
            public void match(MvcResult mvcResult) throws Exception {
                assertEquals(mvcResult.getResponse().getContentAsString(), JsonUtil.writeValue(t));
            }
        };
    }
}
