package mconst.rpg.catalog.controllers;

import mconst.rpg.catalog.models.exceptions.ExceptionBody;
import mconst.rpg.catalog.models.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = { NotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionBody onNotFoundException(NotFoundException exception) {
        return exception.getBody();
    }
}
