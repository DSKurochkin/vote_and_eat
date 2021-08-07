package ru.dm.projects.vote_and_eat.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import ru.dm.projects.vote_and_eat.model.Restaurant;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class JsonUtil {
    public static String getContent(MvcResult result) throws UnsupportedEncodingException {
        return result.getResponse().getContentAsString();
    }

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> String writeValue(T obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write to JSON:\n'" + obj + "'", e);
        }
    }

    public static <T> String writeValue(List<T> list) {
        try {
            return mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write array to JSON:\n'" + list + "'", e);
        }
    }

//    public static String writeValue(List<Restaurant> list) {
//        try {
//            return mapper.writeValueAsString(list);
//        } catch (JsonProcessingException e) {
//            throw new IllegalStateException("Invalid write array to JSON:\n'" + list + "'", e);
//        }
//    }

    public static <T> T readFromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid read from JSON:\n'" + json + "'", e);
        }
    }
    public static <T> T readFromJson(ResultActions action, Class<T> clazz) throws UnsupportedEncodingException {
        try {
            return mapper.readValue(getContent(action.andReturn()), clazz);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid read from JSON:\n'" + action.toString() + "'", e);
        }
    }
}
