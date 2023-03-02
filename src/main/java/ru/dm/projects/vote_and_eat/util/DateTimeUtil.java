package ru.dm.projects.vote_and_eat.util;

import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Component
public class DateTimeUtil {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    private static Clock CLOCK = Clock.systemDefaultZone();
    //    @Value("${datetime.startOfVote}")
    private final LocalTime startOfVote = getTime("07:00");

    //    @Value("${datetime.endOfVote}")
    private final LocalTime endOfVote = getTime("11:00");

    //    @Value("${datetime.startAppDate}")
    private final LocalDate startAppDate = getDate("2021-01-01");

    //    @Value("${datetime.endAppDate}")
    private final LocalDate endAppDate = getDate("3021-01-01");

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
        return startAppDate;
    }

    public LocalDate getEndAppDate() {
        return endAppDate;
    }

    public LocalDate checkStartDate(LocalDate start) {
        return start == null ? getStartAppDate() : start;
    }

    public LocalDate checkEndDate(LocalDate end) {
        return end == null ? getEndAppDate() : end;
    }

}
