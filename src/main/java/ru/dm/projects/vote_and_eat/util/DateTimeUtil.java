package ru.dm.projects.vote_and_eat.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Component
public class DateTimeUtil {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    private static Clock CLOCK = Clock.systemDefaultZone();
    @Value("${datetime.startOfVote}")
    private LocalTime startOfVote;

    @Value("${datetime.endOfVote}")
    private LocalTime endOfVote;

    @Value("${datetime.startAppDate}")
    private String startAppDate;

    @Value("${datetime.endAppDate}")
    private String endAppDate;

    public static LocalDate getDate(String date) {
        return LocalDate.parse(date);
    }

    public static LocalDate today() {
        return LocalDate.now(getClock());
    }

    public static LocalTime now() {
        return LocalTime.now(getClock()).truncatedTo(ChronoUnit.MINUTES);
    }

    public static String getStringFromDate(LocalDate ld) {
        return ld.format(DATE_FORMATTER);
    }

    public static LocalTime getTime(String time) {
        return LocalTime.parse(time);
    }

    private static Clock getClock() {
        return CLOCK;
    }

    public static void useMockTime(LocalDateTime dateTime) {
        Instant instant = dateTime.atZone(ZoneId.systemDefault()).toInstant();
        CLOCK = Clock.fixed(instant, ZoneId.systemDefault());
    }

    public static void useSystemDefaultClock() {
        CLOCK = Clock.systemDefaultZone();
    }

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

    public LocalDate chekStartDate(LocalDate start) {
        return start == null ? getStartAppDate() : start;
    }

    public LocalDate chekEndDate(LocalDate end) {
        return end == null ? getEndAppDate() : end;
    }

}
