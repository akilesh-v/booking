package com.booking.room.common.controller;

import com.booking.room.common.exceptions.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.booking.room.common.utils.TagUtils.LOG_TAG;

@RestController
@RequestMapping("booking/api")
public class BaseController {

    private static final Log logger = LogFactory.getLog(BaseController.class);

    public static final String MESSAGE = "Encountered error while processing request";


    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Map<String, String> resourceNotFound(ResourceNotFoundException ex) {
        logger.error(String.format("%s : %s", LOG_TAG, ex.getMessage()));
        return Collections.singletonMap("message", ex.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> validationException(ValidationException ex) {
            logger.error(String.format("%s : Validation exception:", LOG_TAG), ex);
        return Collections.singletonMap("message", ex.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public Map<String, Object> forbiddenException(ForbiddenException ex) {
        logger.error(String.format("%s : Forbidden exception:", LOG_TAG), ex);
        return Collections.singletonMap("message", ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Map<String, Object> runtimeError(Exception ex) {
        logger.fatal(String.format("%s : %s", LOG_TAG, MESSAGE), ex);
        return Collections.singletonMap("message", ex.getMessage());
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public Map<String, Object> dataNotFound(DataNotFoundException ex) {
        logger.error(String.format("%s : Data Not found exception:", LOG_TAG), ex);
        return Collections.singletonMap("message", "Data Not Found");
    }

    @ExceptionHandler(FailedDependencyException.class)
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    @ResponseBody
    public Map<String, Object> failedDependency(FailedDependencyException ex) {
        final String message = "service is down. Please try after some time";
        logger.error(String.format("%s : Failed response from dependent service", LOG_TAG), ex);
        return Collections.singletonMap("message", message);
    }

    @ExceptionHandler(CannotCreateTransactionException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Map<String, Object> sqlException(CannotCreateTransactionException ex, HttpServletResponse response) {
        Throwable throwable = ex.getRootCause();
        if (throwable != null && "Timeout waiting for idle object".equals(throwable.getMessage())) {
            logger.error(String.format("%s : Timeout waiting for idle object", LOG_TAG), ex);
            response.setStatus(520);
            return Collections.singletonMap("error", "We have received too many requests. Please try again after sometime.");
        }
        return runtimeError(ex);
    }

    @ExceptionHandler({InvalidDataException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public Map<String, Object> inValidData(InvalidDataException ex) {
        String errorMessage = ex.getMessage();
        logger.fatal(String.format("%s : %s", LOG_TAG, errorMessage), ex);
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("message", "Encountered error while processing request");
        errorMap.put("error", errorMessage);
        return errorMap;
    }


}
