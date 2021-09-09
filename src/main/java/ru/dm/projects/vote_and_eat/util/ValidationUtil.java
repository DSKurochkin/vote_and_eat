package ru.dm.projects.vote_and_eat.util;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.lang.NonNull;
import org.springframework.validation.BindingResult;
import ru.dm.projects.vote_and_eat.HasId;
import ru.dm.projects.vote_and_eat.util.exception.IllegalRequestDataException;

import java.util.stream.Collectors;

public class ValidationUtil {
    public static String notFoundMessage(Long i) {
        return "Not found entity with id = " + i;
    }

    public static void assureIdConsistent(HasId bean, long id) {
        if (bean.getId() != id) {
            throw new IllegalRequestDataException(bean + " must be with id=" + id);
        }
    }

    @NonNull
    public static Throwable getRootCause(@NonNull Throwable t) {
        Throwable rootCause = NestedExceptionUtils.getRootCause(t);
        return rootCause != null ? rootCause : t;
    }

    public static String getMessageFromBindingResult(BindingResult result) {
        return result.getFieldErrors().stream()
                .map(fe -> String.format("[%s] %s", fe.getField(), fe.getDefaultMessage()))
                .sorted()
                .collect(Collectors.joining("<br>"));
    }
}
