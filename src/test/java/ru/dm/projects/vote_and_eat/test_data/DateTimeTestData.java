package ru.dm.projects.vote_and_eat.test_data;

import java.time.LocalDateTime;

import static ru.dm.projects.vote_and_eat.util.DateTimeUtil.getDate;
import static ru.dm.projects.vote_and_eat.util.DateTimeUtil.today;

public class DateTimeTestData {
    public static final LocalDateTime goodTestTimeToChangeDish = today().atTime(5, 0);
    public static final LocalDateTime goodTestTimeToVote = today().atTime(9, 0);
    public static final LocalDateTime goodTestTimeToLookResult = today().atTime(12, 0);
    public static final LocalDateTime lateTestTime = today().atTime(23, 59);
    public static final LocalDateTime dbTestDateTime = getDate("2021-09-01").atTime(12, 0);
}
