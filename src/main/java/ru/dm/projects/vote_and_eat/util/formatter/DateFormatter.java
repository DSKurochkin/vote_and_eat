package ru.dm.projects.vote_and_eat.util.formatter;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import ru.dm.projects.vote_and_eat.util.DateTimeUtil;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class DateFormatter implements Formatter<LocalDate> {
    @Override
    public String print(LocalDate localDate, Locale locale) {
        return localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
    @Override
    public LocalDate parse(String s, Locale locale) throws ParseException {
        return DateTimeUtil.getDate(s);
    }
}
