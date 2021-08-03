package ru.dm.projects.vote_and_eat.util;

import ru.dm.projects.vote_and_eat.model.Dish;

import java.time.LocalDate;
import java.time.LocalTime;

public class DishUtil {

    public static void isAdmissibleTimeToChange (Dish dish){

        if (!dish.getDate().equals(LocalDate.now())
                &&(LocalTime.now().isAfter(LocalTime.parse("08:00:00")))){
            throw new RuntimeException("?????");
        }
    }
}
