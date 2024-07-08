package com.example.mongodbtests.config;


import com.example.mongodbtests.exception.MyException;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

@RestController
@ControllerAdvice
@Log4j2
public class CustomSpringRestExceptionHandler extends ResponseEntityExceptionHandler implements ErrorController {

    @ExceptionHandler({MyException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleBadRequestException(MyException ex, WebRequest request) {
        String URI = ((ServletWebRequest) request).getRequest().getRequestURI();
        String ContextPath = ((ServletWebRequest) request).getRequest().getContextPath();
        String API = URI.replace(ContextPath, "");
        return ResponseEntity.of(Optional.of(ex.getMessage()));
    }
}
