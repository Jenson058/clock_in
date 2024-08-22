package com.jenson.service.handler;

import com.jenson.service.common.basic.Result;
import com.jenson.service.common.exception.PasswordNotPassException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class EntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = { PasswordNotPassException.class })
    public Result<Object> handleAnyException(PasswordNotPassException ex) {
        ex.printStackTrace();
        return Result.err(ex.getCode(),ex.getMsg());
    }

    @ResponseBody
    @ExceptionHandler(value = { Exception.class })
    public Result<Object> handleAnyException(Exception ex) {
        ex.printStackTrace();
        return Result.err(ex.getMessage());
    }
}
