package com.jareer.lms.app.handlers;

import com.jareer.lms.app.dtos.AppErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<AppErrorDTO> handleUnknownExceptions(Exception e, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(
                new AppErrorDTO(
                        request.getRequestURI(),
                        e.getMessage(),
                        null,
                        400
                )
        );
    }

}
