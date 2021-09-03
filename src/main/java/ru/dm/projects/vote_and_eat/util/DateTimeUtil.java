package ru.dm.projects.vote_and_eat.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
@Component
public class DateTimeUtil {
    @Value("${datetime.startOfVote}")
    private LocalTime startOfVote;

    @Value("${datetime.endOfVote}")
    private LocalTime endOfVote;

    @Value("${datetime.startAppDate}")
    private String startAppDate;

    @Value("${datetime.endAppDate}")
    private String endAppDate;

    public LocalTime getStartOfVote() {
        return startOfVote;
    }

    public LocalTime getEndOfVote() {
        return endOfVote;
    }

    public LocalDate getStartAppDate() {
        return getDate(startAppDate);
    }

    public LocalDate getEndAppDate() {
        return getDate(endAppDate);
    }

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

    public LocalDate chekStartDate(LocalDate start){
        return start==null?getStartAppDate():start;
    }

    public LocalDate chekEndDate(LocalDate end){
        return end==null?getEndAppDate():end;
    }

}
