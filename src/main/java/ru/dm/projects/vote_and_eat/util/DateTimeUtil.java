package ru.dm.projects.vote_and_eat.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {
    private static final DateTimeFormatter tf = DateTimeFormatter.ISO_LOCAL_TIME;

    public static LocalDate getDate(String date) {
        return LocalDate.parse(date);
    }

    public static LocalDate getToday() {
        return LocalDate.now();
    }

    public static LocalTime getNow() {
        return LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
    }

    public static LocalTime getTime(String time) {
        return LocalTime.parse(time);
    }

    public static void main(String[] args) {
        System.out.println(getNow());
        //.format(DateTimeFormatter.ofPattern("hh:mm:ss")
    }
}
