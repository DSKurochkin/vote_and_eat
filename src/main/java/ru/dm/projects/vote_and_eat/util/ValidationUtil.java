package ru.dm.projects.vote_and_eat.util;

import ru.dm.projects.vote_and_eat.HasId;
import ru.dm.projects.vote_and_eat.util.exception.IllegalRequestDataException;

public class ValidationUtil {
    public static String notFoundMessage(Long i) {
        return "Not found entity with id = " + i;
    }

    public static void assureIdConsistent(HasId bean, long id) {
        if (bean.getId() != id) {
            throw new IllegalRequestDataException(bean + " must be with id=" + id);
        }
    }
}
