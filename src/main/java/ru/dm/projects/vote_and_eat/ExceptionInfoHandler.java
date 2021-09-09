package ru.dm.projects.vote_and_eat;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.dm.projects.vote_and_eat.util.ValidationUtil;
import ru.dm.projects.vote_and_eat.util.exception.ErrorInfo;
import ru.dm.projects.vote_and_eat.util.exception.ErrorType;
import ru.dm.projects.vote_and_eat.util.exception.IllegalRequestDataException;
import ru.dm.projects.vote_and_eat.util.exception.NotFoundException;

import javax.servlet.http.HttpServletRequest;

import static ru.dm.projects.vote_and_eat.util.exception.ErrorType.*;


@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE + 5)
public class ExceptionInfoHandler {
//    private static Logger log = LoggerFactory.getLogger(ExceptionInfoHandler.class);

    //    https://stackoverflow.com/questions/538870/should-private-helper-methods-be-static-if-they-can-be-static
    private static ErrorInfo logAndGetErrorInfo(HttpServletRequest req, Exception e, boolean logException, ErrorType errorType) {
        Throwable rootCause = ValidationUtil.getRootCause(e);
//        if (logException) {
//            log.error(errorType + " at request " + req.getRequestURL(), rootCause);
//        } else {
//            log.warn("{} at request  {}: {}", errorType, req.getRequestURL(), rootCause.toString());
//        }
        return new ErrorInfo(req.getRequestURL(), errorType, rootCause.toString());
    }

    private static ErrorInfo bindErrorInfo(HttpServletRequest req, String details) {
        return new ErrorInfo(req.getRequestURL()
                , VALIDATION_ERROR
                , details);
    }

    private static ErrorInfo bindErrorInfo(HttpServletRequest req, BindException e) {
        return bindErrorInfo(req, ValidationUtil.getMessageFromBindingResult(e.getBindingResult()));
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(NotFoundException.class)
    public ErrorInfo handleError(HttpServletRequest req, NotFoundException e) {
        return logAndGetErrorInfo(req, e, false, DATA_NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorInfo conflict(HttpServletRequest req, DataIntegrityViolationException e) {
        String mes = ValidationUtil.getRootCause(e).getMessage().toLowerCase();
        if (mes.contains("users_unique_email_idx")) {
            return bindErrorInfo(req, "User with this email already exists");
        } else if (mes.contains("meals_unique_user_datetime_idx")) {
            return bindErrorInfo(req, "You already have meal with this date/time");
        }
        return logAndGetErrorInfo(req, e, true, DATA_ERROR);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)  // 422
    @ExceptionHandler({IllegalRequestDataException.class, MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
    public ErrorInfo illegalRequestDataError(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, false, VALIDATION_ERROR);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)  // 422
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ErrorInfo handleBindException(HttpServletRequest req, BindException e) {
        return bindErrorInfo(req, e);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorInfo handleError(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, true, APP_ERROR);
    }
}